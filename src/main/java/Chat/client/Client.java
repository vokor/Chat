package Chat.client;

import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple client that requests/sends a message, time and a username from the RPCChat.server.
 */
public class Client {

    private final String address;
    private final int port;
    private final String name;
    private final String exit = "exit";
    private GreeterGrpc.GreeterStub asyncStub;
    private GreeterGrpc.GreeterBlockingStub blockingStub;

    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Client(String address, int port, String name) {
        this.address = address;
        this.port = port;
        this.name = name;
    }

    /** Construct client for accessing Chat.server.Greeter server using the existing channel. */
    public void RouteGuideClient(ManagedChannelBuilder<?> channelBuilder) {
        var channel = channelBuilder.build();
        asyncStub = GreeterGrpc.newStub(channel);
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void connect() {
        RouteGuideClient(ManagedChannelBuilder.forAddress(address, port).usePlaintext());
    }

    public void send(String text) {
        asyncStub.send(Chat.Request.newBuilder()
                .setName(name)
                .setTime(formatter.format(new Date()))
                .setText(text)
                .build(), new StreamObserver<>() {
            @Override
            public void onNext(Chat.Empty value) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }

    public void get() {
        var res = blockingStub.get(Chat.From.newBuilder().setName(name).build());
        if (res.getMessage().equals(exit))
            return;
        System.out.println(res.getMessage());
    }
}
