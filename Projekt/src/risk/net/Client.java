package risk.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import risk.event.IEvent;


public class Client {
	private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
 
    public void startConnection(String ip, int port) throws UnknownHostException, IOException {
    	System.out.println("Client: initializing.");
        clientSocket = new Socket(ip, port);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }
 
    public IEvent sendMessage(IEvent msg) throws IOException, ClassNotFoundException {
    	System.out.println("Client: sending message.");
        out.writeObject(msg);
    	System.out.println("Client: waiting for response.");
        IEvent resp = (IEvent) in.readObject();
        return resp;
    }
 
    public void stopConnection() throws IOException {
    	System.out.println("Client: terminating.");
        in.close();
        out.close();
        clientSocket.close();
    }
}
