package com.example.service;

import com.example.greeting.Greeting;
import com.example.greeting.GreetingServiceGrpc;
import com.example.greeting.GreetingRequest;
import com.example.greeting.GreetingResponse;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class GreetingClientService {

    @GrpcClient("grpc-greeting-service")
    private GreetingServiceGrpc.GreetingServiceBlockingStub synchronousClient;

    @GrpcClient("grpc-greeting-service")
    private GreetingServiceGrpc.GreetingServiceStub asynchronousClient;

    public Object getMessage(String name, String message) {
        GreetingRequest request = GreetingRequest.newBuilder()
                .setMessage(message)
                .setName(name)
                .build();
        GreetingResponse response = synchronousClient.greeting(request);
        return response.getAllFields();
    }

    public List<Object> getNames(String name) throws InterruptedException {
        final CountDownLatch downLatch = new CountDownLatch(1);
        GreetingRequest greetingRequest = GreetingRequest.newBuilder()
                .setName(name)
                .build();

        List<Object> response = new ArrayList<>();

        asynchronousClient.getAsyncGreeting(greetingRequest, new StreamObserver<>() {

            @Override
            public void onNext(Greeting greeting) {
                response.add(greeting.getAllFields());
            }

            @Override
            public void onError(Throwable throwable) {
                downLatch.countDown();
            }

            @Override
            public void onCompleted() {
                downLatch.countDown();
            }
        });

        boolean await = downLatch.await(1, TimeUnit.MINUTES);
        return await ? response : Collections.emptyList();
    }
}
