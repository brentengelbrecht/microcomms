package com.example.grpc.client.async;

import com.example.grpc.HelloRequest;
import com.example.grpc.HelloResponse;
import com.example.grpc.HelloServiceGrpc;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Executor executor = Executors.newFixedThreadPool(3);

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceFutureStub stub
                = HelloServiceGrpc.newFutureStub(channel);

        System.out.println("Send request 1: Mickey");
        ListenableFuture<HelloResponse> helloResponse1 = stub.hello(HelloRequest.newBuilder()
                .setId(101)
                .setFirstName("Mickey")
                .setLastName("Mouse")
                .build());
        Futures.addCallback(helloResponse1, new FutureCallback<HelloResponse>() {
            @Override
            public void onSuccess(@NullableDecl HelloResponse helloResponse) {
                System.out.println("Response 1 from server: Greeting: " + helloResponse.getGreeting());
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Error server: " + throwable.getMessage());
            }
        }, executor);

        System.out.println("Send request 2: Leia");
        ListenableFuture<HelloResponse> helloResponse2 = stub.hello(HelloRequest.newBuilder()
                .setId(202)
                .setFirstName("Princess")
                .setLastName("Leia")
                .build());
        Futures.addCallback(helloResponse2, new FutureCallback<HelloResponse>() {
            @Override
            public void onSuccess(@NullableDecl HelloResponse helloResponse) {
                System.out.println("Response 2 from server: Greeting: " + helloResponse.getGreeting());
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Error server: " + throwable.getMessage());
            }
        }, executor);

        System.out.println("Send request 3: Indy");
        ListenableFuture<HelloResponse> helloResponse3 = stub.hello(HelloRequest.newBuilder()
                .setId(303)
                .setFirstName("Indiana")
                .setLastName("Jones")
                .build());
        Futures.addCallback(helloResponse3, new FutureCallback<HelloResponse>() {
            @Override
            public void onSuccess(@NullableDecl HelloResponse helloResponse) {
                System.out.println("Response 3 from server: Greeting: " + helloResponse.getGreeting());
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Error server: " + throwable.getMessage());
            }
        }, executor);

        Thread.sleep(1000L);
        channel.shutdown();
    }

}
