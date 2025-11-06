package com.erebelo.springhydrationservice.repository.contract;

import com.erebelo.springhydrationservice.domain.model.contract.ContractFirm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractFirmRepository extends MongoRepository<ContractFirm, String> {
}
