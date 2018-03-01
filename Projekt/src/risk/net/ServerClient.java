package risk.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import risk.event.AEventSystem;
import risk.event.ANetEvent;
import risk.event.EventType;
import risk.event.IEvent;
import risk.event.LclEndGameEvent;
import risk.event.RpcConnectEvent;
import risk.event.RpcDisconnectEvent;
import risk.util.Delegate;
import risk.util.ErrorHandler;
import risk.util.IDestroyable;
import risk.util.Utility;

/**
 * Handles client IO on the server
 * Knows about what input and output is relevant to the client and server
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-03-01
 */
public class ServerClient extends AEventSystem implements Runnable, IDestroyable {
	private Server parent;
	private Socket clientSocket;
	private ObjectOutputStream out;	// must be initialized before run.
	private ObjectInputStream in;	// must be initialized before run.
	private AtomicReference<AEventSystem> controller;
	// AEventSystem view;
	// ^ Not valid. view must probably be implemented in ServerClient extending its own AEventSystem, which listens and forwards all those messages.
	
	
	/**
	 * 
	 * @param server The owning server
	 * @param socket The client socket
	 */
	public ServerClient(Server server, Socket socket) {
		this.parent = server;
		this.clientSocket = socket;
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
	
	/**
	 * Sets the controller to receive the input
	 * @param sys The input event system
	 */
	public void setController(AEventSystem sys) {
		controller.set(sys);
	}

	/**
	 * Sets the status of the server client
	 * @param v True if this should work as a remote view, false if not.
	 */
	public void setRemoteViewStatus(boolean v) {
		if (v) attachListeners();
		else detachListeners();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
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
                	if (parent.addValidatedPlayer(this,  ((RpcConnectEvent)input).player)) {
                		out.writeObject(input);	// echo back for success
                		queueEvent(input);	// auto post all 
                	}
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
		} catch (@SuppressWarnings("unused") IOException | ClassNotFoundException e) {
			queueEvent(new LclEndGameEvent());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see risk.event.IEventSystem#attachListeners()
	 */
	@Override
	public void attachListeners() {
		List<EventType> e = Utility.getEventsOfType("Svr");
		for (EventType ev : e) attachListener(new Delegate(this, "forwardEvent"), ev);
	}

	/*
	 * (non-Javadoc)
	 * @see risk.event.IEventSystem#detachListeners()
	 */
	@Override
	public void detachListeners() {
		List<EventType> e = Utility.getEventsOfType("Svr");
		for (EventType ev : e) detachListener(new Delegate(this, "forwardEvent"), ev);
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * Forwards events to the client.
	 * 
	 * @param e the event which was listened to
	 */
	public void forwardEvent(IEvent e) {
		try {
			ErrorHandler.ASSERT(e instanceof ANetEvent);
			System.out.println("Server sent: " + e.toString());
			out.writeObject(e);
		} catch (@SuppressWarnings("unused") SocketException e1) {
		//	queueEvent(new LclEndGameEvent()); // run will stop.
		} catch (IOException e1) {
			e1.printStackTrace();
			ErrorHandler.ASSERT(false);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see risk.util.IDestroyable#destroy()
	 */
	@Override
	public void destroy() {
		try {
			in.close();
		} catch (@SuppressWarnings("unused") IOException e) {
		}
	}
}
