package com.example.grpc.server.service;

import com.example.grpc.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import com.example.grpc.HelloRequest;
import com.example.grpc.HelloResponse;

import java.util.Arrays;
import java.util.List;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        System.out.println("Received hello request: " + request.getLastName() + ", " + request.getFirstName());

        /*
        try {
            Thread.sleep((long) (Math.random() * 4000.0));
        } catch (InterruptedException e) {
            System.out.println("Thread woke up");
        }
        */

        String greeting = new StringBuilder()
                .append("Hello, ")
                .append(request.getFirstName())
                .append(" ")
                .append(request.getLastName())
                .append(" [")
                .append(request.getId())
                .append("]")
                .toString();

        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .build();

        System.out.println("Sent response to client: " + response);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void streamHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        List<String> hellos = Arrays.asList("Hola", "Bonjour", "Guten tag", "Salve", "Namaste", "Konnichiwa", "Zdravstvuyte");
        hellos.forEach(i -> {
            String greeting = new StringBuilder()
                    .append(i)
                    .append(", ")
                    .append(request.getFirstName())
                    .append(" ")
                    .append(request.getLastName())
                    .append(" [")
                    .append(request.getId())
                    .append("]")
                    .toString();

            HelloResponse response = HelloResponse.newBuilder()
                    .setGreeting(greeting)
                    .build();

            System.out.println("Sent response to client: " + response);

            responseObserver.onNext(response);
        });

        responseObserver.onCompleted();
    }
}
