import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Listening to client actions
 */
public class Listener {
    private final Client client;
    private final String exit = "exit";
    private AtomicBoolean work;


    public Listener(Client client) {
        this.client = client;
        work = new AtomicBoolean(false);
    }

    /**
     * Runs chat and waits for new lines from user to send
     */
    public void run() {
        client.connect();
        work.set(true);
        Thread thread = new Thread(() -> {
            while (work.get()) {
                Scanner in = new Scanner(System.in);
                String text = in.nextLine();
                client.send(text);
                if (text.equals(exit))
                    work.set(false);
            }
        });
        thread.start();

        while (work.get()) {
            client.get();
        }
    }
}
