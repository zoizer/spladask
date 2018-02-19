package risk.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import risk.event.AEventSystem;
import risk.event.ANetEvent;
import risk.event.IEvent;
import risk.event.RpcConnectEvent;
import risk.event.RpcDisconnectEvent;
import risk.util.Delegate;
import risk.util.ErrorHandler;

public class ServerClient extends AEventSystem implements Runnable {
	Server parent;
	Socket clientSocket;
	AtomicBoolean live;
	ObjectOutputStream out;	// must be initialized before run.
	ObjectInputStream in;	// must be initialized before run.
	AtomicReference<AEventSystem> controller;
	// AEventSystem view;
	// ^ Not valid. view must probably be implemented in ServerClient extending its own AEventSystem, which listens and forwards all those messages.
	
	public ServerClient(Server server, Socket socket, AtomicBoolean live) {
		this.parent = server;
		this.clientSocket = socket;
		this.live = live;
		this.controller = new AtomicReference<AEventSystem>();
		this.controller.set(null);
		
		try {
			this.out = new ObjectOutputStream(clientSocket.getOutputStream());
			this.in = new ObjectInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			ErrorHandler.ASSERT(false);
		}
	}
	
	public void setController(AEventSystem sys) {
		controller.set(sys);
	}

	public void remoteView(boolean v) {
		if (v) attachListeners();
		else detachListeners();
	}

	@Override
	public void run() {
		try {
            IEvent input;
            
            while ((input = (IEvent)in.readObject()) != null) {
                if (input instanceof RpcDisconnectEvent) {
                    ErrorHandler.ASSERT(false); // CRASH ON DISCONNECT FOR NOW.
                    break;
                }
                
                if (input instanceof RpcConnectEvent) { // should only happen once. 
                	if (parent.addValidatedPlayer(this,  ((RpcConnectEvent)input).player)) queueEvent(input);	// auto post all 
                	else break; // Player Rejected! Didnt join fast enough or has wrong name.
                }
                
                AEventSystem ctrl = controller.get();
                if (ctrl != null) {
                	System.out.println("Server received: " + input.toString());
                	ctrl.queueEvent(input);
                }
                
              //  System.out.println("Server: " + inputLine);
              //  out.writeObject(inputLine);
            }
 
            in.close();
            out.close();
            clientSocket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			ErrorHandler.ASSERT(false);
		}
	}

	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "forwardEvent"), IEvent.EventType.SvrAttackEvent);
		attachListener(new Delegate(this, "forwardEvent"), IEvent.EventType.SvrStartGameEvent);
	}

	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "forwardEvent"), IEvent.EventType.SvrAttackEvent);
		detachListener(new Delegate(this, "forwardEvent"), IEvent.EventType.SvrStartGameEvent);
	}
	
	public void forwardEvent(IEvent e) {
		try {
			ErrorHandler.ASSERT(e instanceof ANetEvent);
			System.out.println("Server sent: " + e.toString());
			out.writeObject(e);
		} catch (IOException e1) {
			e1.printStackTrace();
			ErrorHandler.ASSERT(false);
		}
	}
}
