package risk.event;

import risk.general.event.Event;

/**
 * InputEvent handles inputEvents.
 * 
 * 
 * @author 		Filip Törnqvist
 * @version 	25/02
 */
public class InputEvent extends Event {
	public final static int INPUT_EVENT_ZONE_LEFT_CLICK = 1; // zone id
	public final static int INPUT_EVENT_ZONE_RIGHT_CLICK = 2; // zone id
	public final static int INPUT_EVENT_NEW_GAME = 3;
	public final static int INPUT_EVENT_QUIT = 4; // Error code
	private int data;
	
	public InputEvent(float timestamp, int eventType, int data) {
		super(timestamp, eventType);
		this.data = data;
	}
	
	public int GetData() { return data; }
}
