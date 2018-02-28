package risk.event;

import risk.net.NetPlayer;

/**
 * Sent by client player to join.
 * (Sent by client to server)
 * 
 * @see EventType#RpcConnectEvent
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class RpcConnectEvent extends ANetEvent {
	private static final long serialVersionUID = 5906433813262202491L;
	
	/**
	 * The player that connected
	 */
	public final NetPlayer player;
	
	/**
	 * 
	 * @param p Player that connected
	 */
	public RpcConnectEvent(NetPlayer p) {
		super(EventType.RpcConnectEvent);
		this.player = p;
		System.out.println("EVENT CREATED: " + toString());
	}
	
	
}
