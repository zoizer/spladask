package risk.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import risk.controller.IResponse;
import risk.event.AEventSystem;
import risk.event.EventType;
import risk.event.IEvent;
import risk.event.LclHostGameEvent;
import risk.event.LclServerHostStartGameEvent;
//import risk.event.LclNameRequest;
import risk.event.LclPreStartGameEvent;
import risk.event.LclStartGameSentEvent;
import risk.event.SvrStartGameEvent;
import risk.net.NetPlayer;
import risk.util.Delegate;
import risk.util.ErrorHandler;
import risk.util.IResetable;

/**
 * The InstanceView class is the graphical window which handles all visual output and visual menus
 * Knows about the window and the menu, knows nothing about the actual game other then if its running or not
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-03-01
 *
 */
public class InstanceView extends AEventSystem implements IResetable {
	private MouseAdapter mouseAdapter;
	private WindowAdapter windowAdapter;
	private ActionListener actionListener;
	private IResponse response;
	private JFrame jFrame;
	private JMenuBar jMenuBar;
	private JMenu jmFile;
	private JMenuItem jmiNewGame;
	private JMenuItem jmiHostGame;
	private JMenuItem jmiJoinGame;
	private JMenuItem jmiCreateMap;
	private JMenuItem jmiExit;
	private IGameView localView;
	private IGameView remoteViews;
	private String requestedName;
	private HostPanelView hostPanel;
	
	/**
	 * 
	 * @param ma The MouseAdapter handling all mouse events in the game
	 * @param wa The WindowAdapter handling all window events
	 * @param al The ActionListener handling all menu events
	 * @param res The IResponse handling all direct response to certain events
	 */
	public InstanceView(MouseAdapter ma, WindowAdapter wa, ActionListener al, IResponse res) {
		mouseAdapter = ma;
		windowAdapter = wa;
		actionListener = al;
		response = res;
		jFrame = new JFrame();
		jFrame.addWindowListener(windowAdapter);
		jMenuBar = new JMenuBar();
		jmFile = new JMenu("File");
		jmiNewGame = new JMenuItem("New Game");
		jmiHostGame = new JMenuItem("Host Game");
		jmiJoinGame = new JMenuItem("Join Game");
		jmiCreateMap = new JMenuItem("Create Map");
		jmiExit = new JMenuItem("Exit");
		localView = null;
		remoteViews = null;
		requestedName = null;
		hostPanel = null;
		//ctrlPanel = new ControlPanel(new BorderLayout());
		
		createMenu();

        //jFrame.add(ctrlPanel, BorderLayout.SOUTH);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocation(100, 100);
		jFrame.pack();
		jFrame.setVisible(true);
		
		attachListeners();
	}

	/*
	 * (non-Javadoc)
	 * @see risk.event.IEventSystem#attachListeners()
	 */
	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "lclStartGame"), EventType.LclStartGameEvent);
		attachListener(new Delegate(this, "lclHostGame"), EventType.LclHostGameEvent);
		attachListener(new Delegate(this, "lclServerHostStartGame"), EventType.LclServerHostStartGameEvent);
		attachListener(new Delegate(this, "lclPreStartGame"), EventType.LclPreStartGameEvent);
		attachListener(new Delegate(this, "lclStartGameSent"), EventType.LclStartGameSentEvent);
		attachListener(new Delegate(this, "svrStartGame"), EventType.SvrStartGameEvent);
		attachListener(new Delegate(this, "lclEndGame"), EventType.LclEndGameEvent);
	}

	/*
	 * (non-Javadoc)
	 * @see risk.event.IEventSystem#detachListeners()
	 */
	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "lclStartGame"), EventType.LclStartGameEvent);
		detachListener(new Delegate(this, "lclHostGame"), EventType.LclHostGameEvent);
		detachListener(new Delegate(this, "lclServerHostStartGame"), EventType.LclServerHostStartGameEvent);
		detachListener(new Delegate(this, "lclPreStartGame"), EventType.LclPreStartGameEvent);
		detachListener(new Delegate(this, "lclStartGameSent"), EventType.LclStartGameSentEvent);
		detachListener(new Delegate(this, "svrStartGame"), EventType.SvrStartGameEvent);
		detachListener(new Delegate(this, "lclEndGame"), EventType.LclEndGameEvent);
	}
	
	/**
	 * Creates the game menu
	 */
	private void createMenu() {
    	jmiNewGame.addActionListener(actionListener);
    	jmiHostGame.addActionListener(actionListener);
    	jmiJoinGame.addActionListener(actionListener);
        jmiCreateMap.addActionListener(actionListener);
        jmiExit.addActionListener(actionListener);
    	jmFile.add(jmiNewGame);
    	jmFile.add(jmiHostGame);
    	jmFile.add(jmiJoinGame);
        jmFile.add(jmiCreateMap);
        jmFile.add(jmiExit);
        jMenuBar.add(jmFile);
        jFrame.setJMenuBar(jMenuBar);
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * Resets the graphics right before a new game starts
	 * 
	 * @param ev the event which was listened to
	 */
	public void lclStartGame(IEvent ev) { // only for cleanup.
		reset();
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * Adds the visuals for the host player
	 * 
	 * @param ev the event which was listened to
	 */
	public void lclHostGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof LclHostGameEvent);
		LclHostGameEvent e = (LclHostGameEvent) ev;

		ErrorHandler.ASSERT(hostPanel == null);
		ErrorHandler.ASSERT(localView == null);
		
		hostPanel = new HostPanelView(jFrame, actionListener, e.host);
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * Starts the host game
	 * 
	 * @param ev the event which was listened to
	 */
	public void lclServerHostStartGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof LclServerHostStartGameEvent);
		LclServerHostStartGameEvent e = (LclServerHostStartGameEvent) ev;
		
		ErrorHandler.ASSERT(remoteViews == null);
		
		remoteViews = new RemoteGameViews(e.remotePlayers);
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * Opens an input menu to allow input of host and name when joining or creating a game.
	 * 
	 * @param ev the event which was listened to
	 */
	public void lclPreStartGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof LclPreStartGameEvent);
		LclPreStartGameEvent e = (LclPreStartGameEvent) ev;
		
		String s = (String)JOptionPane.showInputDialog(jFrame, "Type your name", "Select Name", JOptionPane.PLAIN_MESSAGE, null, null, "<name>");
		
		if (!e.host && e.multiplayer) {
			char nill = 0;
			String tmp = (String)JOptionPane.showInputDialog(jFrame, "Type the host address", "Select Host", JOptionPane.PLAIN_MESSAGE, null, null, "localhost");
			if ((tmp != null) && (tmp.length() > 0)) {
				s = s + nill + tmp;
			} else return; // FAILED TO INPUT GOOD STRING.
		}
		
		if ((s != null) && (s.length() > 0)) response.respond(e, s);
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * Sets the local player
	 * 
	 * @param ev the event which was listened to
	 */
	public void lclStartGameSent(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof LclStartGameSentEvent);
		LclStartGameSentEvent e = (LclStartGameSentEvent) ev;
		
		requestedName = e.player;
	}

	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * Actually starts the new game
	 * 
	 * @param ev the event which was listened to
	 */
	public void svrStartGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrStartGameEvent);
		SvrStartGameEvent e = (SvrStartGameEvent) ev;
		String name = requestedName;
		NetPlayer p = null;
		for (int i = 0; i < e.players.size(); i++) if (e.players.get(i).name.equals(name)) p = e.players.get(i);
		ErrorHandler.ASSERT(p != null);
		
		ErrorHandler.ASSERT(localView == null);
		ErrorHandler.ASSERT(p.host || remoteViews == null); // if not host then views == null
		
		if (hostPanel != null) {
			hostPanel.destroy();
			hostPanel = null;
		}
		
		localView = new LocalGameView(e.map, jFrame, mouseAdapter, name, e.startingStrength);
		
		
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * Resets the graphics when a game ended.
	 * 
	 * @param ev the event which was listened to
	 */
	public void lclEndGame(IEvent ev) {
		reset();
	}

	/*
	 * (non-Javadoc)
	 * @see risk.util.IResetable#reset()
	 */
	@Override
	public void reset() {
		if (hostPanel != null) {
			hostPanel.destroy();
			hostPanel = null;
		}
		
		if (localView != null) {
			localView.destroy();
			localView = null;
		}
		
		if (remoteViews != null) {
			remoteViews.destroy();
			remoteViews = null;
		}
		
		jFrame.pack();
		jFrame.setVisible(true);
	}
}