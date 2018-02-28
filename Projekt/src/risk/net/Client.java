package risk.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import risk.event.AEventSystem;
import risk.event.EventType;
import risk.event.IEvent;
import risk.event.LclEndGameEvent;
import risk.event.RpcConnectEvent;
import risk.util.Delegate;
import risk.util.ErrorHandler;
import risk.util.Utility;


public class Client extends AEventSystem implements Runnable {
	private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private AtomicBoolean run;
    private String name;
    
    public Client(String ip, int port, String name) {
    	run = new AtomicBoolean(false);
    	this.name = name;
    	clientSocket = null;
    	out = null;
    	in = null;
        try {
            clientSocket = new Socket(ip, port);
			out = new ObjectOutputStream(clientSocket.getOutputStream());
	        in = new ObjectInputStream(clientSocket.getInputStream());
	        sendMessage(new RpcConnectEvent(new NetPlayer(name, false)));
		} catch (@SuppressWarnings("unused") IOException e) {
		}
    }
    
    public boolean initialize() {
		try {
	    	if (in == null) return false;
	    	IEvent e;
			e = (IEvent)in.readObject();
			if (e != null && e instanceof RpcConnectEvent) {
				if (((RpcConnectEvent)e).player.name.equals(name)) {
					run.set(true);
					attachListeners();
					return true;
				}
			}
		} catch (@SuppressWarnings("unused") ClassNotFoundException | IOException e1) {
		}
		
    	
    	
    	return false;
    }
 
    public void sendMessage(IEvent msg) { // thread safe shutdown as all events go through the same thread.
    	try {
			System.out.println("Client: sending message.");
            out.writeObject(msg);
    	} catch (@SuppressWarnings("unused") SocketException e) {
    		// do nothing as 'run' will solve this issue.
    	} catch (IOException e) {
    		e.printStackTrace();
    		ErrorHandler.ASSERT(false);
    	}
    }
 
    public void stopConnection() {
    	run.set(false);
    	try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (clientSocket != null) clientSocket.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    		ErrorHandler.ASSERT(false);
		}
    }
    
    public void destroy() {
    	detachListeners();
    	stopConnection();
    }

	@Override
	public void attachListeners() {
		List<EventType> e = Utility.getEventsOfType("Rpc");
		for (EventType ev : e) attachListener(new Delegate(this, "sendMessage"), ev);
	}

	@Override
	public void detachListeners() {
		List<EventType> e = Utility.getEventsOfType("Rpc");
		for (EventType ev : e) detachListener(new Delegate(this, "sendMessage"), ev);
	}

	@Override
	public void run() {
		IEvent e;
		
		try {
			while (run.get()) {
				e = (IEvent)in.readObject();
				if (e != null) {
					System.out.println("FROM SERVER: " + e.toString());
					queueEvent(e);
				}
			}
		} catch (@SuppressWarnings("unused") SocketException e1) {
			destroy();
			queueEvent(new LclEndGameEvent());
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
			ErrorHandler.ASSERT(false);
		}
		
	}
}
