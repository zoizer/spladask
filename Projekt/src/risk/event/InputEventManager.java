package risk.event;

import risk.general.event.EventManager;
import risk.general.event.IEvent;
import risk.general.util.Delegate;
import risk.general.util.ErrorHandler;

public class InputEventManager extends EventManager {
	public InputEventManager() {
		super.AttachListener(new Delegate(this, "clickTest"), InputEvent.INPUT_EVENT_MAP_LEFT_CLICK);
	}
	
	
	public void clickTest(IEvent event) {
		ErrorHandler.ASSERT(event instanceof InputEvent);
		System.out.println("Column: " + ((InputEvent)event).GetDataX() + ", Row: " + ((InputEvent)event).GetDataY());
	}
	
}
