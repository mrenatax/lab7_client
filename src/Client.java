import ClientAnswer.CommandReader;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;

public class Client {
    public static void main(String[] args) throws SocketException, InterruptedException {
        new CommandReader();
    }
}
