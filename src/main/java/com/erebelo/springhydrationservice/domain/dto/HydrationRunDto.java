package com.erebelo.springhydrationservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HydrationRunDto {

    @JsonProperty("run_number")
    private Long runNumber;

    @JsonProperty("created_at")
    private Instant createdAt;

}
