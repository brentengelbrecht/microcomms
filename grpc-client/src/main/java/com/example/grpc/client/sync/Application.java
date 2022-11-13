package com.example.grpc.client.sync;

import com.example.grpc.HelloRequest;
import com.example.grpc.HelloResponse;
import com.example.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Application {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub
                = HelloServiceGrpc.newBlockingStub(channel);

        System.out.println("Send request 1: Mickey");
        HelloResponse helloResponse1 = stub.hello(HelloRequest.newBuilder()
                .setId(101)
                .setFirstName("Mickey")
                .setLastName("Mouse")
                .build());
        System.out.println("Response 1 from server: Greeting: " + helloResponse1.getGreeting());

        System.out.println("Send request 2: Leia");
        HelloResponse helloResponse2 = stub.hello(HelloRequest.newBuilder()
                .setId(202)
                .setFirstName("Princess")
                .setLastName("Leia")
                .build());
        System.out.println("Response 2 from server: Greeting: " + helloResponse2.getGreeting());

        System.out.println("Send request 3: Indy");
        HelloResponse helloResponse3 = stub.hello(HelloRequest.newBuilder()
                .setId(303)
                .setFirstName("Indiana")
                .setLastName("Jones")
                .build());
        System.out.println("Response 3 from server: Greeting: " + helloResponse3.getGreeting());

        channel.shutdown();
    }

}
