package risk.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

import risk.event.AEventSystem;
import risk.util.ErrorHandler;

public class ServerClient implements Runnable {
	Server parent;
	Socket clientSocket;
	AtomicBoolean run;
	AtomicBoolean live;
	AtomicBoolean discarded;
	ObjectOutputStream out;	// must be initialized before run.
	ObjectInputStream in;	// must be initialized before run.
	AEventSystem controller;
	// AEventSystem view;
	// ^ Not valid. view must probably be implemented in ServerClient extending its own AEventSystem, which listens and forwards all those messages.
	
	public ServerClient(Server server, Socket socket, AtomicBoolean run, AtomicBoolean live) {
		this.parent = server;
		this.clientSocket = socket;
		this.run = run;
		this.live = live;
		this.discarded = new AtomicBoolean(false);
		
		try {
			this.out = new ObjectOutputStream(clientSocket.getOutputStream());
			this.in = new ObjectInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			ErrorHandler.ASSERT(false);
		}
	}
	
	public void discard() {
		discarded.set(true);
	}

	@Override
	public void run() {
		try {
            String inputLine;
            while ((inputLine = (String)in.readObject()) != null) {
                if (".".equals(inputLine)) {
                    out.writeObject("bye");
                    break;
                }
                System.out.println("Server: " + inputLine);
                out.writeObject(inputLine);
            }
 
            in.close();
            out.close();
            clientSocket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			ErrorHandler.ASSERT(false);
		}
	}

}
