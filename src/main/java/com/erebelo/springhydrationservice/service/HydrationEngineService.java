package com.erebelo.springhydrationservice.service;

import com.erebelo.springhydrationservice.domain.dto.RecordDto;
import com.erebelo.springhydrationservice.domain.enumeration.RecordTypeEnum;
import com.erebelo.springhydrationservice.domain.model.HydrationStep;

public interface HydrationEngineService {

    String triggerHydration(RecordTypeEnum... recordTypes);

    void fetchAndHydrate(HydrationService<? extends RecordDto> service, HydrationStep step);

}
