package risk.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import risk.event.AEventSystem;
import risk.event.EventType;
import risk.event.IEvent;
import risk.event.LclGenerateMap;
import risk.event.LclServerHostStartGameEvent;
import risk.event.LclPreStartGameEvent;
import risk.event.LclStartGameHostEvent;
import risk.event.LclStartGameSentEvent;
import risk.event.LclStartGameEvent;
import risk.event.SvrStartGameEvent;
import risk.net.NetPlayer;
import risk.util.Delegate;
import risk.util.ErrorHandler;

/**
 * Handles all user input. 
 * This is instance controller, containing all other smaller controllers.
 * Knows nothing about the outside world other than if there are players in the current game
 * 
 * @author Filip T�rnqvist
 * @version 2018-02-28
 */
public class InstanceController extends AEventSystem {
	private LocalPlayerController playerCtrl;
	private RemotePlayerControllers remotePlayerCtrl;
	private String player;
	
	/**
	 * Ready to use after constructor
	 */
	public InstanceController() {
		playerCtrl = null;
		remotePlayerCtrl = null;
		player = null;
		attachListeners();
	}
	
	/**
	 * Attaches listeners
	 */
	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "lclServerHostStartGame"), EventType.LclServerHostStartGameEvent);
		attachListener(new Delegate(this, "lclStartGameSentEvent"), EventType.LclStartGameSentEvent);
		attachListener(new Delegate(this, "svrStartGame"), EventType.SvrStartGameEvent);
	}

	/**
	 * Detaches listeners
	 */
	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "lclServerHostStartGame"), EventType.LclServerHostStartGameEvent);
		detachListener(new Delegate(this, "lclStartGameSentEvent"), EventType.LclStartGameSentEvent);
		detachListener(new Delegate(this, "svrStartGame"), EventType.SvrStartGameEvent);
	}
	
	/**
	 * gets the local player.
	 * 
	 * @return the local player
	 */
	private LocalPlayerController getLocalPlayer() {
		return playerCtrl;
	}
	
	/**
	 * Gets a mouse adapter which will route all events to the controller.
	 * Must be stored to keep alive
	 * 
	 * @return customized MouseAdapter
	 */
	public MouseAdapter getMouseAdapter() {
		return new MouseAdapterController(this);
	}
	
	/**
	 * Gets a WindowAdapter which will route all events to the controller.
	 * Must be stored to keep alive
	 * 
	 * @return customized WindowAdapter
	 */
	public WindowAdapter getWindowAdapter() {
		return new WindowAdapterController(this);
	}
	
	/**
	 * Gets an ActionListener which will route all events to the controller.
	 * Must be stored to keep alive
	 * 
	 * @return customized ActionListener
	 */
	public ActionListener getActionListener() {
		return new ActionListenerController(this);
	}
	
	/**
	 * Gets an IResponse object which will allow the user to respond directly to specific events without actually sending their events themselves.
	 * If the messages are applicable then the controller will create these new events.
	 * 
	 * @return customized IResponse object
	 */
	public IResponse getIResponse() {
		return new EventResponse(this);
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * Called on server only. Purpose is to create the controller which handles remote player input.
	 * 
	 * @param ev the event which was listened to
	 */
	public void lclServerHostStartGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof LclServerHostStartGameEvent);
		LclServerHostStartGameEvent e = (LclServerHostStartGameEvent) ev;
		
		if (remotePlayerCtrl != null) {
			remotePlayerCtrl.destroy();
			remotePlayerCtrl = null;
		}
		
		remotePlayerCtrl = new RemotePlayerControllers(e.remotePlayers);
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * Called everywhere, used to specialize controller before game starts
	 * 
	 * @param ev the event which was listened to
	 */
	public void lclStartGameSentEvent(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof LclStartGameSentEvent);
		LclStartGameSentEvent e = (LclStartGameSentEvent) ev;
		this.player = e.player;
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * Called everywhere, used to specialize controller to the started game.
	 * 
	 * @param ev the event which was listened to
	 */
	public void svrStartGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrStartGameEvent);
		SvrStartGameEvent e = (SvrStartGameEvent) ev;
		NetPlayer p = null;
		for (int i = 0; i < e.players.size(); i++) {
			if (e.players.get(i).name.equals(player)) {
				p = e.players.get(i);
			}
		}
		ErrorHandler.ASSERT(p != null);
		
		if (playerCtrl != null) {
			playerCtrl.destroy();
			playerCtrl = null;
		}
		
		playerCtrl = new LocalPlayerController(e.map, player);
		
		if (e.players.size() != 1 && p.host)/* remotePlayerCtrl = new RemotePlayerControllers()*/; // add paramters, like adress.
	}
	
	/**
	 * This is a MouseAdapter which routes specific events to the controller.
	 * 
	 * @author Filip T�rnqvist
	 * @version 2018-02-28
	 */
	private static class MouseAdapterController extends MouseAdapter {
		private InstanceController parent;
		private Point leftPrev;
		private Point rightPrev;
		
		/**
		 * Constructor
		 * 
		 * @param ctrl the InstanceController (I do not trust member classes!)
		 */
		public MouseAdapterController(InstanceController ctrl) {
			parent = ctrl;
			leftPrev = new Point(-1, -1);
			rightPrev = new Point(-1, -1);
		}
		
		/**
		 * Customized mousePressed, will interpret and route some events to the controller.
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			LocalPlayerController player = parent.getLocalPlayer();
			if (player != null) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					leftPrev = e.getPoint();
					player.downClick(e.getPoint());
				} else if (e.getButton() == MouseEvent.BUTTON3) {
					rightPrev = e.getPoint();
				}
			}
		}
		
		/**
		 * Customized mouseReleased, will interpret and route some events to the controller.
		 */
		@Override
		public void mouseReleased(MouseEvent e) {
			LocalPlayerController player = parent.getLocalPlayer();
			if (player != null) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					player.upClick(e.getPoint());
					player.leftClick(e.getPoint(), leftPrev);
				} else if (e.getButton() == MouseEvent.BUTTON3) {
					player.rightClick(e.getPoint(), rightPrev);
				}
			}
		}
	}
	
	/**
	 * This is a WindowAdapter which routes specific events to the controller.
	 * (WIP)
	 * 
	 * @author Filip T�rnqvist
	 * @version 2018-02-28
	 */
	private static class WindowAdapterController extends WindowAdapter {
		@SuppressWarnings("unused")
		private InstanceController parent;
		
		/**
		 * Constructor
		 * 
		 * @param ctrl the InstanceController
		 */
		public WindowAdapterController(InstanceController ctrl) {
			parent = ctrl;
		}
		
		/**
		 * Temporary impl.
		 */
		public void windowClosing(WindowEvent e) {
	     	System.exit(0); // TEMPORARY
		}
	}
	
	/**
	 * This is an ActionListener which will interpret menu events and post events based on them.
	 * 
	 * @author Filip T�rnqvist
	 * @version 2018-02-28
	 */
	private static class ActionListenerController implements ActionListener {
		private InstanceController parent;
		
		/**
		 * Constructor
		 * 
		 * @param ctrl the InstanceController
		 */
		public ActionListenerController(InstanceController ctrl) {
			parent = ctrl;
		}
		
		/**
		 * The Impl.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();

			      if(cmd.equals("New Game")) parent.queueEvent(new LclPreStartGameEvent("Default", true, false));
			 else if(cmd.equals("Host Game")) parent.queueEvent(new LclPreStartGameEvent("Default", true, true));
			 else if(cmd.equals("Join Game")) parent.queueEvent(new LclPreStartGameEvent("Default", false, true));
			 else if(cmd.equals("Create Map")) parent.queueEvent(new LclGenerateMap("Default"));
			 else if(cmd.equals("Exit")) System.exit(0); // TEMPORARY
			 else if(cmd.equals("Start Multiplayer Game")) parent.queueEvent(new LclStartGameHostEvent());
		}
	}
	
	/**
	 * This is an implementation of IResponse, which will allow non-controllers to request the controller to send an event.
	 * 
	 * @author Filip T�rnqvist
	 * @version 2018-02-28
	 */
	private static class EventResponse implements IResponse {
		private InstanceController parent;
		
		/**
		 * Constructor
		 * 
		 * @param ctrl the InstanceController
		 */
		public EventResponse(InstanceController ctrl) {
			parent = ctrl;
		}
		
		/**
		 * The response function, used to decorate and send new events.
		 * 
		 * @param e The event which the response belongs to
		 * @param s The response
		 */
		@Override
		public void respond(IEvent e, String s) {
			if (e instanceof LclPreStartGameEvent) {
				LclPreStartGameEvent ev = (LclPreStartGameEvent)e;
				if (ev.host && ev.multiplayer) { // Multiplayer as host
					parent.queueEvent(new LclStartGameSentEvent(ev.mapName, s, ev.host, ev.multiplayer));
					parent.queueEvent(new LclStartGameEvent(ev.mapName, s, "localhost", ev.host, ev.multiplayer)); // BEGIN LISTEN FOR CLIENTS
				} else if (!ev.host && ev.multiplayer) { // Multiplayer as client
					String player = null;
					String hostAddr = null;
					for (int i = 0; i < (s.length() - 1); i++) {

						if (s.charAt(i) == 0) {
							player = s.substring(0, i);
							hostAddr = s.substring(i + 1, s.length());
						}
					}

					ErrorHandler.ASSERT(player != null || hostAddr != null); // String format is wrong!
					
					parent.queueEvent(new LclStartGameSentEvent(ev.mapName, player, ev.host, ev.multiplayer));
					parent.queueEvent(new LclStartGameEvent(ev.mapName, player, hostAddr, ev.host, ev.multiplayer)); // TRY JOIN SERVER
				} else if (ev.host && !ev.multiplayer) { // Singleplayer
					parent.queueEvent(new LclStartGameSentEvent(ev.mapName, s, ev.host, ev.multiplayer));
					parent.queueEvent(new LclStartGameEvent(ev.mapName, s, "localhost", ev.host, ev.multiplayer)); // START GAME
				}
			}
		}
	}
}
