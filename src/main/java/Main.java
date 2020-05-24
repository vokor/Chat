import java.util.logging.Logger;

class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("Main");
        Server server;
        String name;
        int port;
        String address = "127.0.0.1";

        switch (args.length) {
            case 2 : {
                name = args[0];
                port = Integer.valueOf(args[1]);
                server = new Server(port);
                server.start();
                logger.info("Server");
                break;
            }
            case 3: {
                name = args[0];
                address = args[1];
                port = Integer.valueOf(args[2]);
                logger.info("Client");
                break;
            }
            default: {
                logger.info("Invalid number of arguments");
                return;
            }
        }
        new Listener(new Client(address, port, name)).run();
    }
}
