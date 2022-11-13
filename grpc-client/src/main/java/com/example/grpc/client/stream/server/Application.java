package com.example.grpc.client.stream.server;

import com.example.grpc.HelloRequest;
import com.example.grpc.HelloResponse;
import com.example.grpc.HelloServiceGrpc;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Executor executor = Executors.newFixedThreadPool(3);

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceStub stub
                = HelloServiceGrpc.newStub(channel);

        System.out.println("Send request: Mickey");
        stub.streamHello(HelloRequest.newBuilder()
                .setId(101)
                .setFirstName("Mickey")
                .setLastName("Mouse")
                .build(), new StreamObserver<HelloResponse>() {
            @Override
            public void onNext(HelloResponse helloResponse) {
                System.out.println("Response from server: Greeting: " + helloResponse.getGreeting());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error from server: " + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Response stream closed");
            }
        });

        Thread.sleep(1000L);
        channel.shutdown();
    }

}
