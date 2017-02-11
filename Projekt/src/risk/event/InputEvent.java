package risk.event;

import risk.general.event.Event;

public class InputEvent extends Event {
	public final static int INPUT_EVENT_MAP_LEFT_CLICK = 1;
	public final static int INPUT_EVENT_MAP_RIGHT_CLICK = 2;
	private int datax;
	private int datay;
	
	public InputEvent(float timestamp, int eventType, int datax, int datay) {
		super(timestamp, eventType);
		this.datax = datax;
		this.datay = datay;
	}
}
