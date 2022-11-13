package com.example.restdemo.sync;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class RestClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RestClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("RUNNING...");
		WebClient client = WebClient.create("http://localhost:8080");
		Mono<String> response = client.get().uri("/test").retrieve().bodyToMono(String.class);
		System.out.println("RESPONSE: " + response.block());
		System.out.println(" *** After the web call returns");
	}
}
