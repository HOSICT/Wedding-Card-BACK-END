package com.example.weddingCard.scheduler;

import com.example.weddingCard.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    private final InformationService informationService;

    @Autowired
    public ScheduledTask(InformationService informationService) {
        this.informationService = informationService;
    }

    @Scheduled(cron = "0 41 0 * * ?")
    public void deleteOldInformation() {
        informationService.deleteOldInformation();
    }
}