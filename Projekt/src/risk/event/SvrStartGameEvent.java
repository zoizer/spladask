package risk.event;

import java.util.List;

import risk.general.Map;
import risk.net.NetPlayer;

public class SvrStartGameEvent extends ANetEvent {
	private static final long serialVersionUID = 9076965650139810351L;
	public final Map map; 
	public final List<NetPlayer> players;
	public final int startingStrength;
	
	// should probably contain info about if multiplayer etc.
	
	public SvrStartGameEvent(Map map, List<NetPlayer> players) {
		super(EventType.SvrStartGameEvent);
		this.map = map;
		this.players = players;
		startingStrength = 5;
		System.out.println("EVENT CREATED: " + toString());
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " multiplayer: " + (players.size() != 1);
	}
}
