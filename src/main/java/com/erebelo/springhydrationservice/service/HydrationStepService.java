package com.erebelo.springhydrationservice.service;

import com.erebelo.springhydrationservice.domain.enumeration.HydrationStatus;
import com.erebelo.springhydrationservice.domain.enumeration.RecordTypeEnum;
import com.erebelo.springhydrationservice.domain.model.HydrationStep;

import java.time.Instant;

public interface HydrationStepService {

    HydrationStep initNewStep(RecordTypeEnum recordType, String jobId);

    void updateStepStatus(HydrationStep step, HydrationStatus status);

    void cancelActiveStepsByJobId(String jobId, Instant now);

}
