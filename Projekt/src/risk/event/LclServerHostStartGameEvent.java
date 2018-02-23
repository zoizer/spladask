package risk.event;

import java.util.List;

import risk.net.ServerClient;

public class LclServerHostStartGameEvent extends AEvent {
	private static final long serialVersionUID = 5573382330399047807L;
	public final List<ServerClient> remotePlayers;
	public LclServerHostStartGameEvent(List<ServerClient> remotePlayers) {
		super(EventType.LclServerHostStartGameEvent);
		this.remotePlayers = remotePlayers;
		System.out.println("EVENT CREATED: " + toString());
	}

}
