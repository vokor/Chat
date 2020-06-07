package Chat.server;

import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * A simple gRPC server
 */
public class Server extends Thread {

    private final io.grpc.Server server;

    public Server(int port) {
        this.server = ServerBuilder.forPort(port).addService(new Greeter()).build();
    }

    /**
     * Run server
     */
    @Override
    public void run() {
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
