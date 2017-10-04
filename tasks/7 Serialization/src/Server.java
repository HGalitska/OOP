import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket = null;
    private Socket clientSocket = null;
    private ObjectOutputStream output = null;
    private ObjectInputStream input = null;

    public void start(int port) {
        Cat message = null;

        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();   //waiting for client connection
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            input = new ObjectInputStream(clientSocket.getInputStream());
            message = (Cat)input.readObject(); //getting message from client (serialized object) and deserializing it
            output.writeObject(message);  //writing deserialized object to server's output stream
        }
        catch (IOException e) {
            System.out.println(e);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            input.close();
            output.close();
            clientSocket.close();
            serverSocket.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start(6666); //starting server on port
    }
}
