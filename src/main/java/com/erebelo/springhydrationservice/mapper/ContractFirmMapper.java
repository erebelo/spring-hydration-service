package com.erebelo.springhydrationservice.mapper;

import static org.mapstruct.ReportingPolicy.WARN;

import com.erebelo.springhydrationservice.domain.dto.contract.ContractFirmDto;
import com.erebelo.springhydrationservice.domain.model.contract.ContractFirm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", unmappedTargetPolicy = WARN)
public interface ContractFirmMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "legacyId", source = "id")
    @Mapping(target = "recordId", ignore = true)
    ContractFirm dtoToEntity(ContractFirmDto dto);

}
