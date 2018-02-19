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
import risk.event.IEvent;
//import risk.event.LclNameRequest;
import risk.event.LclStartGameEvent;
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
		attachListener(new Delegate(this, "lclStartGame"), IEvent.EventType.LclStartGameEvent);
		detachListener(new Delegate(this, "lclStartGameSent"), IEvent.EventType.LclStartGameSentEvent);
		attachListener(new Delegate(this, "startGame"), IEvent.EventType.SvrStartGameEvent);
	}

	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "lclStartGame"), IEvent.EventType.LclStartGameEvent);
		detachListener(new Delegate(this, "lclStartGameSent"), IEvent.EventType.LclStartGameSentEvent);
		detachListener(new Delegate(this, "startGame"), IEvent.EventType.SvrStartGameEvent);
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
	
	public void lclStartGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof LclStartGameEvent);
		LclStartGameEvent e = (LclStartGameEvent) ev;
		
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

	public void startGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrStartGameEvent);
		SvrStartGameEvent e = (SvrStartGameEvent) ev;
		String name = requestedName;
		NetPlayer p = null;
		for (int i = 0; i < e.players.size(); i++) if (e.players.get(i).name.equals(name)) p = e.players.get(i);
		ErrorHandler.ASSERT(p != null);
		
		if (localView != null) {
			localView.destroy();
			localView = null;
		}
		
		if (remoteViews != null) {
			remoteViews.destroy();
			remoteViews = null;
		}
		
		localView = new LocalGameView(e.map, jFrame, mouseAdapter, name);
		
		if (e.players.size() != 1 && p.host) {
			remoteViews = new RemoteGameViews(); // need to pass some more parameters, like addresses etc.
		}
	}
	
	
}