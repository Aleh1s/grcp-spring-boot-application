package com.example;

import com.example.random.RandomRequest;
import com.example.random.RandomResponse;
import com.example.random.RandomServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Random;

@GrpcService
public class RandomServerService extends RandomServiceGrpc.RandomServiceImplBase {

    @Override
    public void getRandom(RandomRequest request,
                          StreamObserver<RandomResponse> responseObserver) {

        RandomResponse response = RandomResponse.newBuilder()
                .setName(request.getName())
                .setRandomNumber(new Random().nextInt())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
