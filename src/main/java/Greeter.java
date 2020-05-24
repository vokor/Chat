import io.grpc.stub.StreamObserver;

public class Greeter extends GreeterGrpc.GreeterImplBase {

    String format(String time) {
        return "[" + time + "]";
    }

    String pack(String name, String time, String text) {
        return format(time) + " " + name + " -> " + text + "\n";
    }

    @Override
    public void sayHello(Chat.HelloRequest req, StreamObserver<Chat.HelloReply> responseObserver) {
        Chat.HelloReply reply = Chat.HelloReply
                .newBuilder()
                .setMessage(pack(req.getName(), req.getTime(), req.getText()))
                .build();
        responseObserver.onNext(reply);
    }
}