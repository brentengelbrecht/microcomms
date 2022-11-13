package com.example.grpc.server;

import com.example.grpc.server.service.HelloServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("gRPC Demo");
        Server server = ServerBuilder
                .forPort(8080)
                .executor(Executors.newSingleThreadExecutor())
                .addService(new HelloServiceImpl()).build();

        server.start();
        server.awaitTermination();
    }

}
