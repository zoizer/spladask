package risk.event;

public class LclStartGameSentEvent extends AEvent {
	private static final long serialVersionUID = -3182974363634236442L;
	public final String mapName;
	public final String player;
	public final boolean host;
	public final boolean multiplayer;
	
	public LclStartGameSentEvent(String mapName, String player, boolean host, boolean multiplayer) {
		super(IEvent.EventType.LclStartGameSentEvent);
		this.mapName = mapName;
		this.player = player;
		this.host = host;
		this.multiplayer = multiplayer;
		System.out.println("EVENT CREATED: " + toString());
	}
	
	@Override
	public String toString() {
		return  this.getClass().getSimpleName() + " Map: " + mapName + ", Player: " + player;
	}
}
