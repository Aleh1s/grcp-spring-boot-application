package com.example;

import com.example.db.Db;
import com.example.greeting.Greeting;
import com.example.greeting.GreetingRequest;
import com.example.greeting.GreetingResponse;
import com.example.greeting.GreetingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class GreetingServerService extends GreetingServiceGrpc
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

    @Override
    public void getAsyncGreeting(GreetingRequest request, StreamObserver<Greeting> responseObserver) throws InterruptedException {

        Db.getNames()
                .stream()
                .filter(greeting -> greeting.getName().equals(request.getName()))
                .forEach(responseObserver::onNext);

//        greetings.forEach(responseObserver::onNext);

        responseObserver.onCompleted();
    }
}
