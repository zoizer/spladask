package risk.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import risk.event.AEventSystem;
import risk.event.EventType;
import risk.event.IEvent;
import risk.event.LclServerHostStartGameEvent;
import risk.event.LclStartGameHostEvent;
import risk.event.SvrStartGameEvent;
import risk.general.Map;
import risk.util.Delegate;
import risk.util.ErrorHandler;

/**
 * 
 * @author Filip Törnqvist
 *
 */
public class Server extends AEventSystem implements Runnable {
	private ServerSocket serverSocket;
	private AtomicBoolean run;
	private AtomicBoolean clientsLive;
	private AtomicBoolean listen;
	private List<NetPlayer> validatedPlayers;
	private List<ServerClient> remotePlayers;
	private final Object lock;
	private int port;
	private Map map;
	private NetPlayer host;
	
	public Server(int port, Map map, NetPlayer host) {
		lock = new Object();
		this.map = map;
		this.host = host;
		this.port = port;
		run = new AtomicBoolean(true);
		clientsLive = new AtomicBoolean(true);
		listen = new AtomicBoolean(true);
		validatedPlayers = new ArrayList<NetPlayer>();
		remotePlayers = new ArrayList<ServerClient>();
		
		try { serverSocket = new ServerSocket(this.port); } 
		catch (IOException e) {
			e.printStackTrace();
			serverSocket = null;
			ErrorHandler.ASSERT(false);
		}
		
		attachListeners();
	}
	
	public boolean addValidatedPlayer(ServerClient c, NetPlayer p) {
		synchronized (lock) {
			if (run.get()) { // is the server actually allowing new players?
				boolean ok = true;
				for (NetPlayer player : validatedPlayers) {
					if (player.name.equals(p.name)) {
						ok = false;
						break;
					}
				}
				
				
				if (ok && !p.name.equals(host.name)) {
					remotePlayers.add(c);
					return validatedPlayers.add(p);
				}
			}
		}
		
		return false;
	}
	
	private void stopServerListen() {
		if (listen.get()) {
			listen.set(false);
			detachListeners();
		}
		run.set(false);
		try {
			serverSocket.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			ErrorHandler.ASSERT(false);
		}
	}
	
	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "lclStartGameHost"), EventType.LclStartGameHostEvent);
	}

	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "lclStartGameHost"), EventType.LclStartGameHostEvent);
	}

	@Override
	public void run() {
		try {
			while(run.get()) (new Thread(new ServerClient(this, serverSocket.accept(), clientsLive))).start();
		} catch (@SuppressWarnings("unused") SocketException e) {
		//	queueEvent(new LclEndGameEvent());
		} catch(IOException e) {
			e.printStackTrace();
			ErrorHandler.ASSERT(false);
		} finally {
			stopServerListen();
		}
	}
	
	public void destroy() { // Server calls detachListeners automatically when turned off.
		stopServerListen();
		clientsLive.set(false);
	}
	
	public void lclStartGameHost(IEvent e) { // do not detachListeners as someone might reuse this class without recreating it.
		ErrorHandler.ASSERT(e instanceof LclStartGameHostEvent);
		stopServerListen();
		List<NetPlayer> players;
		List<ServerClient> clients;
		synchronized (lock) {
			players = validatedPlayers;
			validatedPlayers = new ArrayList<NetPlayer>();
			clients = remotePlayers;
			remotePlayers = new ArrayList<ServerClient>();
		}
		
		players.add(host);
		queueEvent(new LclServerHostStartGameEvent(clients));
		// REMEMBER, CLIENTS MUST BE READY TO RECEIVE DATA WHEN I SEND START EVENT TO GUARANTEE THREAD SAFTEY.
		// CAN DO THAT BY HAVING A OUTPUT/INPUT MEMBER IN THE CLIENT WHICH IS NULL (AND IGNORED) UNTIL IT IS RECEIVED IN CONTROLLER/VIEW WHICH IS INITIALIZED BY THE EVENTMANAGER THREAD.
		queueEvent(new SvrStartGameEvent(map, players));
	}
}
