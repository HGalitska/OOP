This program illustrates serialization of object using Java and client-server architecture.

Client connects to server using java.net.Socket and java.net.ServerSocket.
Client serializes Cat object and sends it to server via ObjectOutputStream.
Server receives serialized Cat object and "echoes" it back, but deserialized.

To start program user should start Server.class first and then - Main.class.
