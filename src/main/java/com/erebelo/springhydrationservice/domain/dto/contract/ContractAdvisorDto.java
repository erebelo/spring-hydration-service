package com.erebelo.springhydrationservice.domain.dto.contract;

import com.erebelo.springhydrationservice.domain.dto.RecordDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContractAdvisorDto extends RecordDto {

    private String id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("license_number")
    private String licenseNumber;

    @JsonProperty("start_date")
    private LocalDate startDate;

    @JsonProperty("end_date")
    private LocalDate endDate;

}
