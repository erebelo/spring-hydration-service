package com.erebelo.springhydrationservice.service.contract;

import com.erebelo.springhydrationservice.domain.dto.contract.ContractFirmDto;
import com.erebelo.springhydrationservice.domain.enumeration.RecordTypeEnum;
import com.erebelo.springhydrationservice.domain.model.HydrationJob;
import com.erebelo.springhydrationservice.mapper.ContractFirmMapper;
import com.erebelo.springhydrationservice.query.ContractQueries;
import com.erebelo.springhydrationservice.repository.contract.ContractFirmRepository;
import com.erebelo.springhydrationservice.service.AbstractHydrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContractFirmService extends AbstractHydrationService<ContractFirmDto> {

    private final ContractQueries contractQueries;
    private final ContractFirmMapper mapper;
    private final ContractFirmRepository repository;

    protected ContractFirmService(ContractQueries contractQueries, ContractFirmMapper mapper,
            ContractFirmRepository repository) {
        super(ContractFirmDto.class);
        this.contractQueries = contractQueries;
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public RecordTypeEnum getRecordType() {
        return RecordTypeEnum.CONTRACT_FIRM;
    }

    @Override
    public String getDeltaQuery() {
        HydrationJob currentJob = this.hydrationJobService.getCurrentJob();
        return contractQueries.getFirmContractsDataQuery(currentJob.getRunNumber());
    }

    @Override
    public ContractFirmDto hydrateDomainData(ContractFirmDto domainData) {
        log.info("Hydrating Contract Firm with recordId={}", domainData.getRecordId());
        repository.save(mapper.dtoToEntity(domainData));
        return domainData;
    }
}
