package com.baby.babyproject.task;

import com.alibaba.fastjson.JSON;
import com.baby.babyproject.compoment.KafkaSender;
import com.baby.babyproject.constants.Constants;
import com.baby.babyproject.module.dao.entity.KafkaFailCache;
import com.baby.babyproject.module.dao.entity.KafkaFailCacheExample;
import com.baby.babyproject.module.dao.mapper.KafkaFailCacheMapper;
import com.baby.babyproject.util.ObjectHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Component
public class MyTask {

    @Autowired
    private KafkaFailCacheMapper kafkaFailCacheMapper;

    @Autowired
    private KafkaSender kafkaSender;

    private static Integer time = 3600;

//    @Scheduled(cron = "0/5 * * * * ?")// 每秒钟执行一次
    public void myTask(){
        KafkaFailCacheExample example = new KafkaFailCacheExample();
        example.createCriteria().andStatusEqualTo("1");
        List<KafkaFailCache> caches = this.kafkaFailCacheMapper.selectByExample(example);
        if (ObjectHelper.isNotEmpty(caches)){
            caches.stream().forEach(e ->{
                Map<String, Object> map = new HashMap<>();
                String id = e.getId();
                Object message = e.getMessage();
                map.put(id,message);
                this.kafkaSender.send(Constants.LOG_TOPIC, JSON.toJSONString(map));
            });
        }
    }
}
