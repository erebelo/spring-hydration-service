package com.erebelo.springhydrationservice.service;

import com.erebelo.springhydrationservice.domain.enumeration.HydrationStatus;
import com.erebelo.springhydrationservice.domain.model.HydrationJob;

public interface HydrationJobService {

    HydrationJob getCurrentJob();

    boolean existsInitiatedOrProcessingJob();

    void initNewJob();

    void updateJobStatus(HydrationJob job, HydrationStatus status);

    void cancelStuckJobsAndStepsIfAny();

}
