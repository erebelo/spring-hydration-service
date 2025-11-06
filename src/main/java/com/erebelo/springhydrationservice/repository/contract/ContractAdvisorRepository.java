package com.erebelo.springhydrationservice.repository.contract;

import com.erebelo.springhydrationservice.domain.model.contract.ContractAdvisor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractAdvisorRepository extends MongoRepository<ContractAdvisor, String> {
}
