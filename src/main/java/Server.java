import io.grpc.ServerBuilder;

import java.io.IOException;

public class Server extends Thread {

    io.grpc.Server server;

    public Server(int port) {
        this.server = ServerBuilder.forPort(port).addService(new Greeter()).build();
    }

    @Override
    public void run() {
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
