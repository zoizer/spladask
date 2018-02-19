package risk.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import risk.event.AEventSystem;
import risk.event.IEvent;
import risk.event.LclHostGameEvent;
import risk.event.RpcConnectEvent;
import risk.net.NetPlayer;
import risk.util.Delegate;
import risk.util.ErrorHandler;

public class HostPanelView extends AEventSystem {
	private JFrame parent;
	private ActionListener actionListener;
	private JPanel jPanel;
	private JButton jButton;
	private JTextField jTextField;
	private NetPlayer host;
	private String textFieldBegin;
	private List<NetPlayer> clients;
	private final Object lock;
	
	public HostPanelView(JFrame jFrame, ActionListener actionListener, NetPlayer host) {
		this.parent = jFrame;
		this.actionListener = actionListener;
		this.jPanel = new JPanel();
		this.jButton = new JButton();
		this.jTextField = new JTextField();
		this.host = host;
		this.clients = new ArrayList<NetPlayer>();
		this.lock = new Object();
		
		textFieldBegin = "Host: " + host.name + "\nClients:";
		
		jButton.setText("Start Multiplayer Game");
		jButton.setEnabled(false);
		jButton.addActionListener(actionListener);
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
	
	public void destroy() {
		detachListeners();
		parent.remove(jPanel);
		parent = null;
		actionListener = null;
	}
	
	public void addPlayer(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof RpcConnectEvent);
		RpcConnectEvent e = (RpcConnectEvent) ev;
		clients.add(e.player);
		
		String str = textFieldBegin;
		synchronized (lock) {
			if (!clients.isEmpty()) jButton.setEnabled(true);
			for (int i = 0; i < clients.size();) {
				str += " " + clients.get(i);
				if (++i < clients.size()) str += ",";
			}
			jTextField.setText(str);
			System.out.println("SERVER GUI: " + str); // temp
		}
	}

	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "addPlayer"), IEvent.EventType.RpcConnectEvent);
	}

	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "addPlayer"), IEvent.EventType.RpcConnectEvent);
	}
}
