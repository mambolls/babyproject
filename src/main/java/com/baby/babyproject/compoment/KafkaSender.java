package com.baby.babyproject.compoment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @ClassName KafkaSender
 * @Description kafka消息发送
 * @Author lilinsong
 * @Date 2020/6/17 11:38
 * @Version 1.0
 */
@Slf4j
@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //发送消息方法
    public void send(String topic,String message) {
        kafkaTemplate.send(topic, message);
    }
}
