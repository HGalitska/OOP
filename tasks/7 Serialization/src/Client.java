import java.io.*;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            input = new ObjectInputStream(clientSocket.getInputStream());
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public Cat sendMessage(Cat message) {

        try {
            output.writeObject(message); //serializing object and sending it to server
            Cat response = (Cat)input.readObject(); //receiving response object from server *must be the same*
            return response;
        }
        catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void stopConnection() {
        try {
            input.close();
            output.close();
            clientSocket.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
