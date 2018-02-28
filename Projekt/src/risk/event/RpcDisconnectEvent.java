package risk.event;

import risk.net.NetPlayer;

/**
 * BUGBUG NOT USED, CAN PROBABLY FIX BUG WITH THIS
 * Sent by client player to leave.
 * (Sent by client to server)
 * 
 * @see EventType#RpcDisconnectEvent
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class RpcDisconnectEvent extends ANetEvent {
	private static final long serialVersionUID = -5764433761607697926L;
	
	/**
	 * Player who sent the event
	 */
	public final NetPlayer player;
	
	/**
	 * 
	 * @param p Player who wants to leave
	 */
	public RpcDisconnectEvent(NetPlayer p) {
		super(EventType.RpcDisconnectEvent);
		this.player = p;
		System.out.println("EVENT CREATED: " + toString());
	}

}
