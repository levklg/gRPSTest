import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.*;
import io.grpc.stub.*;

public class Client
{
    public static void main( String[] args ) throws Exception
    {
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext()
                .build();

        // Replace the previous synchronous code with asynchronous code.
        // This time use an async stub:
        GreetingServiceGrpc.GreetingServiceStub stub = GreetingServiceGrpc.newStub(channel);

        // Construct a request
        GreetingServiceOuterClass.HelloRequest request =
                GreetingServiceOuterClass.HelloRequest.newBuilder()
                        .setName("Ray")
                        .build();

        GreetingServiceOuterClass.Request numberRequest = GreetingServiceOuterClass.Request.newBuilder()
                .setNumber(7).build();



        // Make an Asynchronous call. Listen to responses w/ StreamObserver
        stub.greeting(request, new StreamObserver<GreetingServiceOuterClass.HelloResponse>() {
            public void onNext(GreetingServiceOuterClass.HelloResponse response) {
                System.out.println(response);
            }
            public void onError(Throwable t) {
            }
            public void onCompleted() {
                // Typically you'll shutdown the channel somewhere else.
                // But for the purpose of the lab, we are only making a single
                // request. We'll shutdown as soon as this request is done.
                channel.shutdownNow();
            }
        });

        stub.setRequest(numberRequest,  new StreamObserver<GreetingServiceOuterClass.Empty>() {
            public void onNext(GreetingServiceOuterClass.Empty response) {
                System.out.println(response);
            }
            public void onError(Throwable t) {
            }
            public void onCompleted() {
                // Typically you'll shutdown the channel somewhere else.
                // But for the purpose of the lab, we are only making a single
                // request. We'll shutdown as soon as this request is done.
                channel.shutdownNow();
            }
        });

    }
}