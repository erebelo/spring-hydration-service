package com.erebelo.springhydrationservice.service.contract;

import com.erebelo.springhydrationservice.domain.dto.contract.ContractAdvisorDto;
import com.erebelo.springhydrationservice.domain.enumeration.RecordTypeEnum;
import com.erebelo.springhydrationservice.domain.model.HydrationJob;
import com.erebelo.springhydrationservice.mapper.ContractAdvisorMapper;
import com.erebelo.springhydrationservice.query.ContractQueries;
import com.erebelo.springhydrationservice.repository.contract.ContractAdvisorRepository;
import com.erebelo.springhydrationservice.service.AbstractHydrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContractAdvisorService extends AbstractHydrationService<ContractAdvisorDto> {

    private final ContractQueries contractQueries;
    private final ContractAdvisorMapper mapper;
    private final ContractAdvisorRepository repository;

    protected ContractAdvisorService(ContractQueries contractQueries, ContractAdvisorMapper mapper,
            ContractAdvisorRepository repository) {
        super(ContractAdvisorDto.class);
        this.contractQueries = contractQueries;
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public RecordTypeEnum getRecordType() {
        return RecordTypeEnum.CONTRACT_ADVISOR;
    }

    @Override
    public String getDeltaQuery() {
        HydrationJob currentJob = this.hydrationJobService.getCurrentJob();
        return contractQueries.getAdvisorContractsDataQuery(currentJob.getRunNumber());
    }

    @Override
    public ContractAdvisorDto hydrateDomainData(ContractAdvisorDto domainData) {
        log.info("Hydrating Contract Advisor with recordId: {}", domainData.getRecordId());
        repository.save(mapper.dtoToEntity(domainData));
        return domainData;
    }
}
