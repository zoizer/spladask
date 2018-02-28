package risk.event;

/**
 * sent to generate a map.
 * (used by any)
 * 
 * @see EventType#LclGenerateMap
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class LclGenerateMap extends AEvent {
	private static final long serialVersionUID = -7446452590207520167L;
	
	/**
	 * The new map name
	 */
	public final String name;
	
	/**
	 * 
	 * @param name The new map name
	 */
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
