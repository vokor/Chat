import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class Client {

    private final String address;
    private final int port;
    private final String name;

    public Client(String address, int port, String name) {
        this.address = address;
        this.port = port;
        this.name = name;
    }

    GreeterGrpc.GreeterStub asyncStub;

    /** Construct client for accessing Greeter server using the existing channel. */
    public void RouteGuideClient(ManagedChannelBuilder<?> channelBuilder) {
        var channel = channelBuilder.build();
        asyncStub = GreeterGrpc.newStub(channel);
    }

    public void connect() {
        RouteGuideClient(ManagedChannelBuilder.forAddress(address, port).usePlaintext());
        asyncStub.sayHello(Chat.HelloRequest.newBuilder().setName(name).build(), new StreamObserver<>() {
            @Override
            public void onNext(Chat.HelloReply value) {
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }

    public void send(String text) {
    }
}
