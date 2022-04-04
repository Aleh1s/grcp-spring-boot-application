package com.example.service;

import com.example.greeting.GreetingServiceGrpc;
import com.example.greeting.GreetingRequest;
import com.example.greeting.GreetingResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GreetingClientService {

    @GrpcClient("grpc-greeting-service")
    private GreetingServiceGrpc.GreetingServiceBlockingStub synchronousClient;

    public Object getMessage(String name, String message) {
        GreetingRequest request = GreetingRequest.newBuilder()
                .setMessage(message)
                .setName(name)
                .build();
        GreetingResponse response = synchronousClient.greeting(request);
        return response.getAllFields();
    }
}
