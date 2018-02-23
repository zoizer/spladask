package risk.event;

import risk.net.NetPlayer;

public class LclJoinGameEvent extends AEvent {
	private static final long serialVersionUID = -8429737616543396456L;
	public final NetPlayer player;
	public final String hostAddr;
	public final int port;
	
	public LclJoinGameEvent(NetPlayer player, String hostAddr, int port) {
		super(EventType.LclJoinGameEvent);
		this.player = player;
		this.hostAddr = hostAddr;
		this.port = port;
		System.out.println("EVENT CREATED: " + toString());
	}

}
