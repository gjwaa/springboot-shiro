package com.gjw.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
    final String str = "0 * * * * 0-7";

    @Scheduled(cron = str)
    public void hello() {
        System.out.println("执行了！！！！！！！！！！");
    }


}
