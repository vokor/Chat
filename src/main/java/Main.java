import java.util.Scanner;
import java.util.logging.Logger;

class Main {
    // Example: Igor 4000
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("Main");
        Server server;
        String name;
        int port;
        String address = "127.0.0.1";

        if (args.length == 2) {
            name = args[0];
            port = Integer.valueOf(args[1]);
            System.out.println("If you want to run as Client enter your Address else 'No'");
            Scanner in = new Scanner(System.in);
            String text = in.nextLine();
            if (text.equals("No")) {
                server = new Server(port);
                server.start();
                logger.info("Server");
            } else {
                address = text;
                logger.info("Client");
            }
        } else {
            logger.info("Invalid number of arguments");
            logger.info("Example: Igor 4000");
            return;
        }
        new Listener(new Client(address, port, name)).run();
    }
}
