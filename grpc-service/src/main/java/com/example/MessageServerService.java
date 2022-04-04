package com.example;

import com.example.greeting.GreetingRequest;
import com.example.greeting.GreetingResponse;
import com.example.greeting.GreetingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class MessageServerService extends GreetingServiceGrpc
        .GreetingServiceImplBase
{

    @Override
    public void greeting(GreetingRequest request,
                         StreamObserver<GreetingResponse> responseObserver) {

         GreetingResponse response = GreetingResponse.newBuilder()
                 .setMessage(request.getName() + " says " + request.getMessage())
                 .build();

         responseObserver.onNext(response);
         responseObserver.onCompleted();
    }
}
