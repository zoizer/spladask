package risk.event;

public class RpcPreStartGameEvent extends AEvent {
	public final String mapName;
	
	public RpcPreStartGameEvent(String mapName) {
		super(IEvent.EventType.RpcPreStartGameEvent);
		this.mapName = mapName;
	}
}
