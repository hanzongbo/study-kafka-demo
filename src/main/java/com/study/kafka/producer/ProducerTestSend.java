package com.study.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerTestSend {

    public static final String bootStrap = "xxxxxx:9090";
    public static final String topic = "t_3_1";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrap);
        // 序列化协议  下面两种写法都可以
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        //过滤器 可配置多个用逗号隔开
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, "org.apache.kafka.clients.producer.SzzProducerInterceptorsTest");
        //构造 KafkaProducer
        org.apache.kafka.clients.producer.KafkaProducer producer = new org.apache.kafka.clients.producer.KafkaProducer(properties);
        //  发送消息, 并设置 回调(回调函数也可以不要)
        ProducerRecord<String, String> record = new ProducerRecord(topic, "Hello World!");
        try {
            producer.send(record, new TestCallBack(record.topic(), record.key(), record.value()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送成功回调类
     */
    public static class TestCallBack implements Callback {
        private static final Logger log = LoggerFactory.getLogger(TestCallBack.class);
        private String topic;
        private String key;
        private String value;

        public TestCallBack(String topic, String key, String value) {
            this.topic = topic;
            this.key = key;
            this.value = value;

        }

        public void onCompletion(RecordMetadata metadata, Exception e) {
            if (e != null) {
                log.error("Error when sending message to topic {} with key: {}, value: {} with error:",
                        topic, key, value, e);
            } else {
                log.info("send message to topic {} with key: {} value:{} success, partiton:{} offset:{}",
                        topic, key, value, metadata.partition(), metadata.offset());
            }
        }
    }

}
