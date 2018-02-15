package risk.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import risk.event.AEventSystem;
import risk.event.IEvent;
import risk.event.SvrStartGameEvent;
import risk.ui.ControlPanel;
import risk.util.Delegate;
import risk.util.ErrorHandler;

public class InstanceView extends AEventSystem {
	private ActionListener actionListener;
	private MouseAdapter mouseAdapter;
	private JFrame jFrame;
	private JMenuBar jMenuBar;
	private JMenu jmFile;
	private JMenuItem jmiNewGame;
	private JMenuItem jmiCreateMap;
	private JMenuItem jmiExit;
	private IGameView localView;
	private IGameView remoteViews;
	private ControlPanel ctrlPanel; // kinda feel this belongs to the gameView 
	
	public InstanceView(ActionListener al, MouseAdapter ma) {
		actionListener = al;
		mouseAdapter = ma;
		jFrame = new JFrame();
		jMenuBar = new JMenuBar();
		jmFile = new JMenu("File");
		jmiNewGame = new JMenuItem("New Game");
		jmiCreateMap = new JMenuItem("Create Map");
		jmiExit = new JMenuItem("Exit");
		localView = null;
		remoteViews = null;
		//ctrlPanel = new ControlPanel(new BorderLayout());
		
		createMenu();

        //jFrame.add(ctrlPanel, BorderLayout.SOUTH);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocation(100, 100);
		jFrame.pack();
		jFrame.setVisible(true);
	}

	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "startGame"), IEvent.EventType.SvrStartGameEvent);
	}

	@Override
	public void detachListeners() {
		attachListener(new Delegate(this, "startGame"), IEvent.EventType.SvrStartGameEvent);
	}
	
	private void createMenu() {
    	jmiNewGame.addActionListener(actionListener);
        jmiCreateMap.addActionListener(actionListener);
        jmiExit.addActionListener(actionListener);
    	jmFile.add(jmiNewGame);
        jmFile.add(jmiCreateMap);
        jmFile.add(jmiExit);
        jMenuBar.add(jmFile);
        jFrame.setJMenuBar(jMenuBar);
	}

	public void startGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrStartGameEvent);
		SvrStartGameEvent e = (SvrStartGameEvent) ev;
		
		if (localView != null) {
			localView.Destroy();
			localView = null;
		}
		
		if (remoteViews != null) {
			remoteViews.Destroy();
			remoteViews = null;
		}
		
		localView = new LocalGameView(e.map, jFrame, mouseAdapter);
		
		if (e.multiplayer && e.host) {
			remoteViews = new RemoteGameViews(); // need to pass some more parameters, like addresses etc.
		}
	}
	
	
}