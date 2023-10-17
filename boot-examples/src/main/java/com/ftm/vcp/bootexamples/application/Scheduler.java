package com.ftm.vcp.bootexamples.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    @Scheduled(cron = "*/2 * * * * *")
    public void purgeOutdatedTraitements() {
            log.info("Calling from scheduler");
    }
}
