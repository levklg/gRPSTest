package service;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.stub.StreamObserver;

import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void greeting(GreetingServiceOuterClass.HelloRequest request,
                         StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {

        // You must use a builder to construct a new Protobuffer object
        GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass.HelloResponse.newBuilder()
                .setGreeting("Hello there, " + request.getName())
                .build();

        // Feel free to construct different responses if you'd like.
        responseObserver.onNext(response);

        // When you are done, you must call onCompleted.
        responseObserver.onCompleted();
    }
    @Override
    public void setRequest(com.example.grpc.GreetingServiceOuterClass.Request request,
                           io.grpc.stub.StreamObserver<com.example.grpc.GreetingServiceOuterClass.Empty> responseObserver) {

    }


    @Override
    public void getResponse(com.example.grpc.GreetingServiceOuterClass.Request request,
                            io.grpc.stub.StreamObserver<com.example.grpc.GreetingServiceOuterClass.Response> responseObserver) {



    }


}

