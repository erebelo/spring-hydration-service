package com.erebelo.springhydrationservice.repository;

import com.erebelo.springhydrationservice.domain.model.HydrationFailedRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HydrationFailedRecordRepository extends MongoRepository<HydrationFailedRecord, String> {
}
