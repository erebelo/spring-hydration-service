package com.erebelo.springhydrationservice.service.impl;

import com.erebelo.springhydrationservice.domain.dto.HydrationRunDto;
import com.erebelo.springhydrationservice.domain.enumeration.HydrationStatus;
import com.erebelo.springhydrationservice.domain.model.HydrationJob;
import com.erebelo.springhydrationservice.query.HydrationRunQueries;
import com.erebelo.springhydrationservice.repository.HydrationJobRepository;
import com.erebelo.springhydrationservice.service.AthenaService;
import com.erebelo.springhydrationservice.service.HydrationJobService;
import com.erebelo.springhydrationservice.service.HydrationStepService;
import java.time.Instant;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.athena.model.Datum;
import software.amazon.awssdk.services.athena.model.GetQueryResultsResponse;
import software.amazon.awssdk.services.athena.model.Row;

@Service
public class HydrationJobServiceImpl implements HydrationJobService {

    private final AthenaService athenaService;
    private final HydrationStepService hydrationStepService;
    private final HydrationJobRepository repository;
    private final HydrationRunQueries hydrationRunQueries;

    public HydrationJobServiceImpl(@Qualifier("hydrationAthenaService") AthenaService athenaService,
            HydrationStepService hydrationStepService, HydrationJobRepository repository,
            HydrationRunQueries hydrationRunQueries) {
        this.athenaService = athenaService;
        this.hydrationStepService = hydrationStepService;
        this.repository = repository;
        this.hydrationRunQueries = hydrationRunQueries;
    }

    @Getter
    private HydrationJob currentJob;

    @Override
    public boolean existsInitiatedOrProcessingJob() {
        return repository
                .findTopByStatusInOrderByRunNumberDesc(List.of(HydrationStatus.INITIATED, HydrationStatus.PROCESSING))
                .isPresent();
    }

    @Override
    public void initNewJob() {
        Optional<HydrationJob> lastJobRun = repository
                .findTopByStatusInOrderByRunNumberDesc(List.of(HydrationStatus.COMPLETED, HydrationStatus.FAILED));

        Long nextRunNumber = lastJobRun.map(job -> job.getRunNumber() + 1).orElse(1L);
        HydrationRunDto hydrationRun = fetchHydrationRunFromAthena(nextRunNumber);

        if (hydrationRun == null) {
            throw new IllegalStateException("No hydration run found for runNumber=" + nextRunNumber);
        }

        HydrationJob newJob = HydrationJob.builder().runNumber(nextRunNumber).createdAt(hydrationRun.getCreatedAt())
                .startTime(Instant.now()).status(HydrationStatus.INITIATED).build();

        repository.save(newJob);
        this.currentJob = newJob;
    }

    @Override
    public void updateJobStatus(HydrationJob job, HydrationStatus status) {
        job.setStatus(status);

        boolean isTerminal = (status == HydrationStatus.FAILED || status == HydrationStatus.COMPLETED);
        if (isTerminal) {
            job.setEndTime(Instant.now());
        }

        HydrationJob updateJob = repository.save(job);
        this.currentJob = isTerminal ? null : updateJob;
    }

    @Override
    public void cancelStuckJobsAndStepsIfAny() {
        Optional<HydrationJob> lastActiveJob = repository
                .findTopByStatusInOrderByRunNumberDesc(List.of(HydrationStatus.INITIATED, HydrationStatus.PROCESSING));

        if (lastActiveJob.isEmpty()) {
            return;
        }

        Instant now = Instant.now();
        HydrationJob job = lastActiveJob.get();
        job.setStatus(HydrationStatus.CANCELED);
        job.setEndTime(now);

        hydrationStepService.cancelActiveStepsByJobId(job.getId(), now);
        repository.save(job);
        this.currentJob = null;
    }

    private HydrationRunDto fetchHydrationRunFromAthena(Long runNumber) {
        Pair<String, Iterable<GetQueryResultsResponse>> resultsPair = athenaService
                .fetchDataFromAthena(hydrationRunQueries.getHydrationRunsDataQuery(runNumber));

        Iterator<GetQueryResultsResponse> iterator = resultsPair.getRight().iterator();
        if (iterator.hasNext()) {
            List<Row> rows = iterator.next().resultSet().rows();

            if (rows.size() > 1) {
                String[] athenaColumnOrder = rows.getFirst().data().stream().map(Datum::varCharValue)
                        .toArray(String[]::new);
                List<HydrationRunDto> hydrationRuns = athenaService.mapRowsToClass(athenaColumnOrder,
                        rows.subList(1, rows.size()), HydrationRunDto.class);

                return hydrationRuns.getFirst();
            }
        }

        return null;
    }
}
