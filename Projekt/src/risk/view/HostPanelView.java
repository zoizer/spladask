package risk.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import risk.event.AEventSystem;
import risk.event.EventType;
import risk.event.IEvent;
import risk.event.RpcConnectEvent;
import risk.net.NetPlayer;
import risk.util.Delegate;
import risk.util.ErrorHandler;
import risk.util.IDestroyable;

/**
 * A Graphical panel which will display some host specific information
 * Knows about the clients
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-03-01
 *
 */
public class HostPanelView extends AEventSystem implements IDestroyable {
	private JFrame parent;
	private ActionListener actionListener;
	private JPanel jPanel;
	private JButton jButton;
	private JTextField jTextField;
	private String textFieldBegin;
	private List<NetPlayer> clients;
	private final Object lock;
	
	/**
	 * 
	 * @param jFrame The JFrame to be attached to
	 * @param actionListener The action listener for clickable buttons
	 * @param host The host player
	 */
	public HostPanelView(JFrame jFrame, ActionListener actionListener, NetPlayer host) {
		this.parent = jFrame;
		this.actionListener = actionListener;
		this.jPanel = new JPanel();
		this.jButton = new JButton();
		this.jTextField = new JTextField();
		this.clients = new ArrayList<NetPlayer>();
		this.lock = new Object();
		
		textFieldBegin = "Host: " + host.name + "\nClients:";
		
		jButton.setText("Start Multiplayer Game");
		jButton.setEnabled(false);
		jButton.addActionListener(this.actionListener);
		jTextField.setText(textFieldBegin);
		jTextField.setEditable(false);
		jTextField.setPreferredSize(new Dimension(400,100));
		jTextField.setHighlighter(null);
		jPanel.add(jTextField);
		jPanel.add(jButton);
		parent.add(jPanel);
		parent.pack();
		parent.setVisible(true);
		
		
		// create text panel displaying different players.
		// add start button when more than two players.
		// destroy cant be called by an event as order of destruction is important.
		// in InstanceView:
		// call hpv.destroy();
		// then create LocalGameView();
		attachListeners();
	}
	
	/*
	 * (non-Javadoc)
	 * @see risk.util.IDestroyable#destroy()
	 */
	@Override
	public void destroy() {
		detachListeners();
		parent.remove(jPanel);
		parent = null;
		actionListener = null;
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * Adds a player to the client list
	 * 
	 * @param e the event which was listened to
	 */
	public void addPlayer(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof RpcConnectEvent);
		RpcConnectEvent e = (RpcConnectEvent) ev;
		clients.add(e.player);
		
		String str = textFieldBegin;
		synchronized (lock) {
			if (!clients.isEmpty()) jButton.setEnabled(true);
			for (int i = 0; i < clients.size();) {
				str += " " + clients.get(i).name;
				if (++i < clients.size()) str += ",";
			}
			jTextField.setText(str);
			System.out.println("SERVER GUI: " + str); // temp
		}
	}

	/*
	 * (non-Javadoc)
	 * @see risk.event.IEventSystem#attachListeners()
	 */
	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "addPlayer"), EventType.RpcConnectEvent);
	}

	/*
	 * (non-Javadoc)
	 * @see risk.event.IEventSystem#detachListeners()
	 */
	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "addPlayer"), EventType.RpcConnectEvent);
	}
}
