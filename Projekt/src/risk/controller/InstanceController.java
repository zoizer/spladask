package risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import risk.event.AEventSystem;
import risk.event.IEvent;
import risk.event.LclGenerateMap;
import risk.event.RpcPreStartGameEvent;
import risk.event.SvrStartGameEvent;
import risk.util.Delegate;
import risk.util.ErrorHandler;

public class InstanceController extends AEventSystem {
	LocalPlayerController playerCtrl;
	RemotePlayerControllers remotePlayerCtrl;
	MouseAdapterController mouseAdapter;
	WindowAdapterController windowAdapter;
	ActionListenerController actionListener;
	
	
	
	public InstanceController() {
		playerCtrl = null;
		remotePlayerCtrl = null;
		mouseAdapter = new MouseAdapterController(this);
		windowAdapter = new WindowAdapterController(this);
		actionListener = new ActionListenerController(this);
	}
	
	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "startGame"), IEvent.EventType.SvrStartGameEvent);
	}

	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "startGame"), IEvent.EventType.SvrStartGameEvent);
	}
	
	private LocalPlayerController getLocalPlayer() {
		return playerCtrl;
	}
	
	public MouseAdapter getMouseAdapter() {
		return mouseAdapter;
	}
	
	public WindowAdapter getWindowAdapter() {
		return windowAdapter;
	}
	
	public ActionListener getActionListener() {
		return actionListener;
	}
	
	public void startGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrStartGameEvent);
		SvrStartGameEvent e = (SvrStartGameEvent) ev;
		playerCtrl = new LocalPlayerController();
		
		if (e.multiplayer && e.host) remotePlayerCtrl = new RemotePlayerControllers(); // add paramters, like adress.
	}
	
	// CLASSES
	public class MouseAdapterController extends MouseAdapter {
		private InstanceController parent;
		
		public MouseAdapterController(InstanceController ctrl) {
			parent = ctrl;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			LocalPlayerController player = parent.getLocalPlayer();
			if (player != null) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					player.leftClick(e.getPoint());
				} else if (e.getButton() == MouseEvent.BUTTON3) {
					player.rightClick(e.getPoint());
				}
			}
		}
	}
	
	public class WindowAdapterController extends WindowAdapter {
		@SuppressWarnings("unused")
		private InstanceController parent;
		
		public WindowAdapterController(InstanceController ctrl) {
			parent = ctrl;
		}
		
		public void windowClosing(WindowEvent e) {
	     	System.exit(0); // TEMPORARY
		}
	}
	
	public class ActionListenerController implements ActionListener {
		private InstanceController parent;
		
		public ActionListenerController(InstanceController ctrl) {
			parent = ctrl;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();

			      if(cmd.equals("New Game")) parent.queueEvent(new RpcPreStartGameEvent("Default"));
			 else if(cmd.equals("Create Map")) parent.queueEvent(new LclGenerateMap("Default"));
			 else if(cmd.equals("Exit")) System.exit(0); // TEMPORARY
		}
	}
}
