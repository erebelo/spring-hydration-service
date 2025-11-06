package com.erebelo.springhydrationservice.mapper;

import com.erebelo.springhydrationservice.domain.dto.contract.ContractAdvisorDto;
import com.erebelo.springhydrationservice.domain.model.contract.ContractAdvisor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring", unmappedTargetPolicy = WARN)
public interface ContractAdvisorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "legacyId", source = "id")
    @Mapping(target = "recordId", ignore = true)
    ContractAdvisor dtoToEntity(ContractAdvisorDto dto);

}
