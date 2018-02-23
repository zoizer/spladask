package risk.event;

public class LclGenerateMap extends AEvent {
	private static final long serialVersionUID = -7446452590207520167L;
	public String name;
	public LclGenerateMap(String name) {
		super(EventType.LclGenerateMap);
		this.name = name;
		System.out.println("EVENT CREATED: " + toString());
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " Name: " + name;
	}
}
