package risk.event;

import risk.net.NetPlayer;

public class LclHostGameEvent extends AEvent {
	private static final long serialVersionUID = 8493059126250174534L;
	public final NetPlayer host;
	
	public LclHostGameEvent(NetPlayer host) {
		super(IEvent.EventType.LclHostGameEvent);
		this.host = host;
	}
}
