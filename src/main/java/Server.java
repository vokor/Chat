import io.grpc.Grpc;
import io.grpc.ManagedChannel;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.stub.StreamObserver;
import com.google.protobuf.MessageLite;

public class Server extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(Chat.HelloRequest req, StreamObserver<Chat.HelloReply> responseObserver) {
        Chat.HelloReply reply = Chat.HelloReply.newBuilder().setMessage("User: " + req.getName() + " connected").build();
    }
}
