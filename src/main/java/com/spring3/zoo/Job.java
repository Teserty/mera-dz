package com.spring3.zoo;


import com.spring3.zoo.impl.AnimalAsyncServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Job {
    private AnimalAsyncService asyncService;
    @Autowired
    public void setAsyncService(AnimalAsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @Scheduled(cron = "1 * * * * *")
    public void job(){
        System.out.println(LocalDateTime.now());
        asyncService.feedAll();
    }
}
