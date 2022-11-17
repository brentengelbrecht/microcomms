package com.example.springbootkafkasample;

import com.example.springbootkafkasample.consumer.TopicConsumer;
import com.example.springbootkafkasample.producer.TopicProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@Slf4j
@SpringBootApplication
@EnableKafka
public class SpringBootKafkaSampleApplication implements CommandLineRunner {

	@Autowired
	private TopicProducer topicProducer;

	@Autowired
	private TopicConsumer topicConsumer;

	private final String[] message = {"Are we there yet?", "What is The Matrix?", "Got Milk?", "The answer is 42"};

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaSampleApplication.class, args);
	}

	@Override
	public void run(String... args) {
		for (int i = 0; i < 16_384; i++) {
			topicProducer.send(String.format("%d %s", i, message[(int)(Math.random() * 4)]));
		}
	}
}
