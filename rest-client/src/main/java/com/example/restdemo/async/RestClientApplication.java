package com.example.restdemo.async;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class RestClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RestClientApplication.class, args);
	}

	private CompletableFuture<String> asyncRestCall(HttpMethod method, String url) {
		return WebClient.create(url)
				.method(method)
				.retrieve()
				.bodyToMono(String.class)
				.subscribeOn(Schedulers.single())
				.toFuture();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("RUNNING...");
		asyncRestCall(HttpMethod.GET, "http://localhost:8080/test")
				.thenAccept(response -> System.out.println("RESPONSE: " + response));
		System.out.println(" *** Do other stuff");
		System.out.println("         while waiting for the request");
		System.out.println("               to complete ...........");
	}
}
