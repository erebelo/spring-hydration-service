package com.erebelo.springhydrationservice.repository;

import com.erebelo.springhydrationservice.domain.enumeration.HydrationStatus;
import com.erebelo.springhydrationservice.domain.model.HydrationJob;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HydrationJobRepository extends MongoRepository<HydrationJob, String> {

    Optional<HydrationJob> findTopByStatusInOrderByRunNumberDesc(Collection<HydrationStatus> statuses);

}
