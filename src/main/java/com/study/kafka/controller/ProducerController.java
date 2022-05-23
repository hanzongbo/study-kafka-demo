package com.study.kafka.controller;

import com.study.kafka.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProducerController {
    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "key") String key){
        System.out.println("------->测试生产者发送消息");
        //for (int i = 0; i < 5; i++) {
            kafkaProducer.sendMessage(key);
        //}
        return "kafka消息已发送.";
    }
}
