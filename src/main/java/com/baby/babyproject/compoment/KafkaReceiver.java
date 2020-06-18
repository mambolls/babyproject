package com.baby.babyproject.compoment;

import com.alibaba.fastjson.JSON;
import com.baby.babyproject.constants.Constants;
import com.baby.babyproject.module.dao.entity.BabyProjectLog;
import com.baby.babyproject.module.dao.entity.KafkaFailCache;
import com.baby.babyproject.module.dao.mapper.KafkaFailCacheMapper;
import com.baby.babyproject.module.dao.server.IBabyProjectLogServiceDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class KafkaReceiver {

    @Autowired
    private IBabyProjectLogServiceDao babyProjectLogServiceDapImpl;

    @Autowired
    private KafkaFailCacheMapper kafkaFailCacheMapper;

    @KafkaListener(topics = {Constants.LOG_TOPIC})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        Object message = null;
//        Map<String,Object>  map = new HashMap<>();
        try{
            if (kafkaMessage.isPresent()) {
                message = kafkaMessage.get();
//                if (message instanceof Map){
//                    map = (Map<String, Object>) message;
//                    Set<String> keys = map.keySet();
//                    message = map.get(keys.iterator().next());
//                }
//                int i = 1/0;
                BabyProjectLog projectLog = JSON.parseObject(String.valueOf(message), BabyProjectLog.class);
                this.babyProjectLogServiceDapImpl.insertSelective(projectLog);
                log.info("kafka队列消费成功:" + message);
            }
        }catch (Exception e){
            log.error("kafka队列消费失败：{}",record,e.getMessage());
            e.printStackTrace();
//            message = kafkaMessage.get();
//            insertCache(message);
        }
    }


    public void insertCache(Object message){
        KafkaFailCache cache = new KafkaFailCache();
        if (message instanceof Map){
            Map<String, Object> map = (Map<String, Object>) message;
            Set<String> keys = map.keySet();
            String key = keys.iterator().next();
            KafkaFailCache failCache = this.kafkaFailCacheMapper.selectByPrimaryKey(key);
            Integer retryTimes = failCache.getRetryTimes();
            retryTimes = retryTimes+1;
            cache.setId(key);
            cache.setUpdateTime(new Date());
            failCache.setRetryTimes(retryTimes);
            if (retryTimes == 3){
                failCache.setStatus("0");
            }
            this.kafkaFailCacheMapper.updateByPrimaryKeySelective(cache);
        }else {
            String id = UUID.randomUUID().toString();
            cache.setCrateTime(new Date());
            cache.setId(id);
            cache.setMessage(String.valueOf(message));
            this.kafkaFailCacheMapper.insertSelective(cache);
        }
    }
}