import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

    private final String address;
    private final int port;
    private final String name;

    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

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
    }

    public void send(String text) {
        asyncStub.say(Chat.Request.newBuilder()
                .setName(name)
                .setTime(formatter.format(new Date()))
                .setText(text)
                .build(), new StreamObserver<>() {
            @Override
            public void onNext(Chat.Reply value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }
}
