package com.example.springbootkafkasample.producer;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class TopicProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${kafka.topic}")
    private String topic;

    @Value(value = "${kafka.key}")
    private String key;

    public void send(String message) {
        log.info("Sending message: {}", message);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(@NotNull Throwable throwable) {
                log.error("Message sending failed: {}", throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                log.info("Message sent successfully: Offset={}", stringStringSendResult.getRecordMetadata().offset());
            }
        });

    }

}
