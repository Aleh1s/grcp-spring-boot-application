package com.example.service;

import com.example.random.RandomRequest;
import com.example.random.RandomResponse;
import com.example.random.RandomServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.apache.coyote.Request;
import org.springframework.stereotype.Service;

@Service
public class RandomClientService {

    @GrpcClient("grpc-random-service")
    private RandomServiceGrpc.RandomServiceBlockingStub synchronousClient;

    public Object getRandom(String name) {

        RandomRequest request = RandomRequest.newBuilder()
                .setName(name)
                .build();

        RandomResponse response = synchronousClient.getRandom(request);
        return response.getAllFields();
    }
}
