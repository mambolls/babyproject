package com.baby.babyproject.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @ClassName MyTask
 * @Description 定时任务
 * @Author lilinsong
 * @Date 2020/5/12
 * @Version 1.0
 */
@Configuration
@Slf4j
@EnableScheduling
public class MyTask {

    private static Integer time = 3600;

    @Scheduled(cron = "0/1 * * * * ?")
    public void myTask(){
        if (time>0){
            log.info("剩余时间："+time--);
        }
    }
}
