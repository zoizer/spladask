package risk.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicBoolean;

import risk.event.AEventSystem;
import risk.event.IEvent;
import risk.event.RpcConnectEvent;
import risk.util.Delegate;
import risk.util.ErrorHandler;


public class Client extends AEventSystem implements Runnable {
	private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private AtomicBoolean run;
    private String name;
    
    public Client(String ip, int port, String name) {
    	run = new AtomicBoolean(true);
    	this.name = name;
    	clientSocket = null;
    	out = null;
    	in = null;
        try {
            clientSocket = new Socket(ip, port);
			out = new ObjectOutputStream(clientSocket.getOutputStream());
	        in = new ObjectInputStream(clientSocket.getInputStream());
	        sendMessage(new RpcConnectEvent(new NetPlayer(name, false)));
		} catch (IOException e) {
		}
    }
    
    public boolean initialize() {
		try {
	    	if (in == null) return false;
	    	IEvent e;
			e = (IEvent)in.readObject();
			if (e != null && e instanceof RpcConnectEvent) {
				if (((RpcConnectEvent)e).player.name.equals(name)) {
					attachListeners();
					return true;
				}
			}
		} catch (ClassNotFoundException | IOException e1) {
		}
		
    	
    	
    	return false;
    }
 
    public void sendMessage(IEvent msg) {
    	try {
        	System.out.println("Client: sending message.");
            out.writeObject(msg);
    	} catch (IOException e) {
    		ErrorHandler.ASSERT(false);
    	}
        //IEvent resp = (IEvent) in.readObject();
        //return resp;
    }
 
    public void stopConnection() {
    	run.set(false);
    	try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (clientSocket != null) clientSocket.close();
    	} catch (IOException e) {
    		ErrorHandler.ASSERT(false);
		}
    }
    
    public void destroy() {
    	detachListeners();
    	stopConnection();
    }

	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "sendMessage"), IEvent.EventType.RpcAttackEvent);
		attachListener(new Delegate(this, "sendMessage"), IEvent.EventType.RpcConnectEvent);
		attachListener(new Delegate(this, "sendMessage"), IEvent.EventType.RpcDisconnectEvent);
		attachListener(new Delegate(this, "sendMessage"), IEvent.EventType.RpcStartGameEvent);
	}

	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "sendMessage"), IEvent.EventType.RpcAttackEvent);
		detachListener(new Delegate(this, "sendMessage"), IEvent.EventType.RpcConnectEvent);
		detachListener(new Delegate(this, "sendMessage"), IEvent.EventType.RpcDisconnectEvent);
		detachListener(new Delegate(this, "sendMessage"), IEvent.EventType.RpcStartGameEvent);
	}

	@Override
	public void run() {
		IEvent e;
		
		try {
			while (run.get()) {
				e = (IEvent)in.readObject();
				if (e != null) {
					queueEvent(e);
				}
			}
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
			ErrorHandler.ASSERT(false);
		}
		
	}
}
