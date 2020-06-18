package com.baby.babyproject;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.baby.babyproject.**"})
@Slf4j
@MapperScan({"com.baby.babyproject.module.dao.mapper"})
public class BabyprojectApplication {

    public static void main(String[] args) {

        log.info("----------------项目启动开始----------------");
        SpringApplication.run(BabyprojectApplication.class, args);
        log.info("----------------项目启动成功----------------");
    }

}
