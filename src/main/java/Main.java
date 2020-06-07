import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Implementation of chat with gRPC framework
 */
class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("Main");
        Server server;
        String name;
        int port;
        String address = "127.0.0.1";

        System.out.println("Enter SRV or CLI");
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();

        System.out.println("Enter your name");
        in = new Scanner(System.in);
        name = in.nextLine();


        System.out.println("Enter port");
        in = new Scanner(System.in);
        port = Integer.valueOf(in.nextLine());

	if (text.equals("SRV")) {
                server = new Server(port);
                server.start();
                logger.info("Server");
            } else {
                System.out.println("Enter server adress");
                in = new Scanner(System.in);
                address = in.nextLine();
                logger.info("Client");
            }

        new Listener(new Client(address, port, name)).run();
    }
}
