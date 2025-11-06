package com.erebelo.springhydrationservice.repository;

import com.erebelo.springhydrationservice.domain.enumeration.HydrationStatus;
import com.erebelo.springhydrationservice.domain.model.HydrationStep;
import java.util.Collection;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HydrationStepRepository extends MongoRepository<HydrationStep, String> {

    List<HydrationStep> findAllByJobIdAndStatusIn(String jobId, Collection<HydrationStatus> statuses);

}
