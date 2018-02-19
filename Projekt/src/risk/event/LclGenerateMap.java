package risk.event;

public class LclGenerateMap extends AEvent {
	public String name;
	public LclGenerateMap(String name) {
		super(IEvent.EventType.LclGenerateMap);
		this.name = name;
		System.out.println("EVENT CREATED: " + toString());
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " Name: " + name;
	}
}
