import io.grpc.stub.StreamObserver;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Greeter extends GreeterGrpc.GreeterImplBase {

    String user;
    int count;
    private final Object object;
    private final ConcurrentLinkedQueue<Chat.Reply> queue1;
    private final ConcurrentLinkedQueue<Chat.Reply> queue2;
    private final AtomicBoolean work;
    private final String exit = "exit";

    public Greeter() {
        queue1 = new ConcurrentLinkedQueue<>();
        queue2 = new ConcurrentLinkedQueue<>();
        count = 0;
        object = new Object();
        work = new AtomicBoolean(true);
    }

    String format(String time) {
        return "[" + time + "]";
    }

    String pack(String name, String time, String text) {
        return format(time) + " " + name + " -> " + text;
    }

    @Override
    public void send(Chat.Request req, StreamObserver<Chat.Empty> responseObserver) {
        Chat.Reply reply = Chat.Reply
                .newBuilder()
                .setMessage(pack(req.getName(), req.getTime(), req.getText()))
                .build();

        Chat.Empty empty = Chat.Empty.newBuilder().build();
        responseObserver.onNext(empty);
        responseObserver.onCompleted();
        if (req.getText().equals(exit)) {
            work.set(false);
        }
        checker(req.getName());
        if (user.equals(req.getName()))
            queue2.add(reply);
        else
            queue1.add(reply);
    }

    @Override
    public void get(Chat.From from, StreamObserver<Chat.Reply> responseObserver) {
        checker(from.getName());
        while (work.get()) {
            Chat.Reply msg;
            if (user.equals(from.getName())) {
                msg = queue1.poll();
            } else
                msg = queue2.poll();
            if (msg != null) {
                responseObserver.onNext(msg);
                break;
            }
        }
        if (!work.get()) {
            Chat.Reply reply = Chat.Reply.newBuilder().setMessage(exit).build();
            responseObserver.onNext(reply);
        }
        responseObserver.onCompleted();
    }

    void checker(String from) {
        synchronized (object) {
            if (count == 0) {
                user = from;
                count++;
            }
        }
    }
}