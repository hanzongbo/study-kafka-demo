package com.study.kafka.producer;

import com.common.utils.MyStringUtils;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

@Component
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String key) {
        try {
            //Producer producer = new Producer();
            String hello = MyStringUtils.getString("hello");
           // ProducerConfig
            //生产消息
            String message = "hello ！ 测试kafka " + key;
            ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send("mytopic", message);
            listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onSuccess(SendResult<String, String> result) {
                    System.out.println("sendMessage success");
                }

                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("sendMessage error");
                }
            });

        } catch (Exception e) {
            System.out.println("sendMessage exception"+e.getMessage());
        }
    }
}
