import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

public class Listener {
    Client client;
    private volatile boolean work;
    private final String exit = "exit";

    public Listener(Client client) {
        this.client = client;
    }

    public void run() {
        client.connect();
        work = true;

        Thread thread = new Thread(() -> {
            while (work) {
                Scanner in = new Scanner(System.in);
                String text = in.next();
                if (text.equals(exit))
                    return;
                client.send(text);
            }
        });
        thread.start();

    }
}
