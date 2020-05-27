import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Listener {
    Client client;
    private final String exit = "exit";
    AtomicBoolean work;

    public Listener(Client client) {
        this.client = client;
        work = new AtomicBoolean(false);
    }

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
