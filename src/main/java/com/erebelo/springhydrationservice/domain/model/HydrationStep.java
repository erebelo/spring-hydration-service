package com.erebelo.springhydrationservice.domain.model;

import com.erebelo.springhydrationservice.domain.enumeration.HydrationStatus;
import com.erebelo.springhydrationservice.domain.enumeration.RecordTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("hydration_steps")
public class HydrationStep {

    @Id
    private String id;

    private String jobId;
    private String executionId;
    private RecordTypeEnum domainType;
    private HydrationStatus status;
    private Instant startTime;
    private Instant endTime;

}
