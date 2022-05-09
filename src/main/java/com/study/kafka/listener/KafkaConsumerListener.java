package com.study.kafka.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.Resource;

@Configuration
public class KafkaConsumerListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;




}
