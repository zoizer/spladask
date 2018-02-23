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

public class InstanceView extends AEventSystem {
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

	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "lclStartGame"), EventType.LclStartGameEvent);
		attachListener(new Delegate(this, "lclHostGame"), EventType.LclHostGameEvent);
		attachListener(new Delegate(this, "lclServerHostStartGame"), EventType.LclServerHostStartGameEvent);
		attachListener(new Delegate(this, "lclPreStartGame"), EventType.LclPreStartGameEvent);
		attachListener(new Delegate(this, "lclStartGameSent"), EventType.LclStartGameSentEvent);
		attachListener(new Delegate(this, "svrStartGame"), EventType.SvrStartGameEvent);
	}

	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "lclStartGame"), EventType.LclStartGameEvent);
		detachListener(new Delegate(this, "lclHostGame"), EventType.LclHostGameEvent);
		detachListener(new Delegate(this, "lclServerHostStartGame"), EventType.LclServerHostStartGameEvent);
		detachListener(new Delegate(this, "lclPreStartGame"), EventType.LclPreStartGameEvent);
		detachListener(new Delegate(this, "lclStartGameSent"), EventType.LclStartGameSentEvent);
		detachListener(new Delegate(this, "svrStartGame"), EventType.SvrStartGameEvent);
	}
	
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
	
	public void lclStartGame(@SuppressWarnings("unused") IEvent ev) { // only for cleanup.
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
	}
	
	public void lclHostGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof LclHostGameEvent);
		LclHostGameEvent e = (LclHostGameEvent) ev;

		ErrorHandler.ASSERT(hostPanel == null);
		ErrorHandler.ASSERT(localView == null);
		
		hostPanel = new HostPanelView(jFrame, actionListener, e.host);
	}
	
	public void lclServerHostStartGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof LclServerHostStartGameEvent);
		LclServerHostStartGameEvent e = (LclServerHostStartGameEvent) ev;
		
		ErrorHandler.ASSERT(remoteViews == null);
		
		remoteViews = new RemoteGameViews(e.remotePlayers);
	}
	
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
	
	public void lclStartGameSent(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof LclStartGameSentEvent);
		LclStartGameSentEvent e = (LclStartGameSentEvent) ev;
		
		requestedName = e.player;
	}

	public void svrStartGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrStartGameEvent);
		SvrStartGameEvent e = (SvrStartGameEvent) ev;
		String name = requestedName;
		NetPlayer p = null;
		for (int i = 0; i < e.players.size(); i++) if (e.players.get(i).name.equals(name)) p = e.players.get(i);
		ErrorHandler.ASSERT(p != null);
		
		ErrorHandler.ASSERT(localView == null);
		ErrorHandler.ASSERT(p.host || remoteViews == null); // if not host then views == null
		ErrorHandler.ASSERT(!p.host || remoteViews != null);// if host then views == null
		
		if (hostPanel != null) {
			hostPanel.destroy();
			hostPanel = null;
		}
		
		localView = new LocalGameView(e.map, jFrame, mouseAdapter, name);
		
		
	}
	
	
}