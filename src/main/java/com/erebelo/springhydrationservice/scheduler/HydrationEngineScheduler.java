package com.erebelo.springhydrationservice.scheduler;

import com.erebelo.springhydrationservice.service.HydrationEngineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
 * Scheduled method called automatically by Spring.
 * No-arg as required by @Scheduled.
 * The @ConditionalOnProperty ensures this job runs only when 'hydration.scheduler.enabled=true'.
 */
@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "hydration.scheduler.enabled", havingValue = "true")
public class HydrationEngineScheduler {

    private final HydrationEngineService hydrationEngineService;

    /*
     * Ensures that only one hydration job runs at a time. The scheduled method is
     * triggered according to the cron expression. If a previous job is still
     * running when the next trigger occurs, that execution is skipped, preventing
     * concurrent processing.
     */
    @Scheduled(cron = "${hydration.scheduler.cron:0 0 3 * * MON-FRI}")
    @SchedulerLock(name = "hydrationScheduler", lockAtMostFor = "PT3H", lockAtLeastFor = "${hydration.scheduler.min-lock-time:PT1H}")
    public void scheduledTrigger() {
        log.info("Hydration triggered by scheduler");
        hydrationEngineService.triggerHydration();
    }
}
