package com.study.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
public class KafkaConsumer {

    //@KafkaListener(topics = {"mytopic"})
    public void KafkaConsumer(ConsumerRecord<String, String> record) {
        String topic = record.topic();
        String msg = record.value();
        System.out.println("消费者接受消息：topic-->" + topic + ",msg->>" + msg);
    }
}
