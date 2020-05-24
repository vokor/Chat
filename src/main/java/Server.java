import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Level;

public class Server extends Thread {

    io.grpc.Server server;

    public Server(int port) {
        this.server = ServerBuilder.forPort(port).addService(new Greeter()).build();
    }

    @Override
    public void run() {
        try {
            server.start();
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
