package risk.event;

import java.util.List;

import risk.generic.Map;

public class SvrStartGameEvent extends AEvent {
	public final Map map; 
	public final boolean multiplayer;
	public final boolean host;
	public List<String> players;
	
	// should probably contain info about if multiplayer etc.

	public SvrStartGameEvent(Map map, boolean multiplayer, boolean host, List<String> players) {
		super(IEvent.EventType.SvrStartGameEvent);
		this.map = map;
		this.multiplayer = multiplayer;
		this.host = host;
		this.players = players;
	}
}
