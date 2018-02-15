package risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import risk.event.EventManager;
import risk.event.IEvent;
import risk.event.IEventSystem;
import risk.event.RpcPreStartGameEvent;
import risk.event.SvrStartGameEvent;
import risk.testing.Editor;
import risk.util.Delegate;
import risk.util.ErrorHandler;

public class InstanceController extends MouseAdapter implements ActionListener, IEventSystem {
	LocalPlayerController playerCtrl;
	RemotePlayerControllers remotePlayerCtrl;
	
	public InstanceController() {
		playerCtrl = null;
		remotePlayerCtrl = null;
	}
	
	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "startGame"), IEvent.EventType.SvrStartGameEvent);
	}

	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "startGame"), IEvent.EventType.SvrStartGameEvent);
	}

	
	
	// handle all non-user related commands.
	
	
	// All commands should just decorate or modify them, they should NOT 
	
	protected void queueEvent(IEvent e) {
		EventManager.get().queueEvent(e);
	}
	
	protected void attachListener(Delegate d, IEvent.EventType eventType) {
		EventManager.get().attachListener(d, eventType);
	}
	
	protected void detachListener(Delegate d, IEvent.EventType eventType) {
		EventManager.get().attachListener(d, eventType);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		      if(cmd.equals("New Game")) queueEvent(new RpcPreStartGameEvent("sistariskcolored"));
		 else if(cmd.equals("Create Map")) Editor.CREATE_MAP("sistariskcolored"); // TEMPORARY
		 else if(cmd.equals("Exit")) System.exit(0); // TEMPORARY
	}
	
	public void startGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrStartGameEvent);
		SvrStartGameEvent e = (SvrStartGameEvent) ev;
		playerCtrl = new LocalPlayerController();
		
		if (e.multiplayer && e.host) remotePlayerCtrl = new RemotePlayerControllers(); // add paramters, like adress.
	}
	
	public void mouseClicked(MouseEvent e) {
		if (playerCtrl != null) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				playerCtrl.leftClick(e.getPoint());
			} else if (e.getButton() == MouseEvent.BUTTON3) {
				playerCtrl.rightClick(e.getPoint());
			}
		}
	}
}
