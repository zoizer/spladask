package risk.event;

import risk.general.event.EventManager;
import risk.general.event.IEvent;
import risk.general.util.Delegate;
import risk.general.util.ErrorHandler;

public class InputEventManager extends EventManager {
	public InputEventManager() {
		//super.AttachListener(new Delegate(this, "clickTest"), InputEvent.INPUT_EVENT_ZONE_LEFT_CLICK);
		super.AttachListener(new Delegate(this, "NewGame"), InputEvent.INPUT_EVENT_NEW_GAME);
		super.AttachListener(new Delegate(this, "ZoneLeftClick"), InputEvent.INPUT_EVENT_ZONE_LEFT_CLICK);
		super.AttachListener(new Delegate(this, "ZoneRightClick"), InputEvent.INPUT_EVENT_ZONE_RIGHT_CLICK);
	}
	
	public void NewGame(IEvent event) {
		// CAN WE START A NEW GAME? IF YES, RUN THIS CODE.
		EventManager.Get().QueueEvent(new RiskEvent(0.0f, RiskEvent.EVENT_NEW_GAME_PRE));
	}
	
	public void ZoneLeftClick(IEvent event) {
		
	}
	
	public void ZoneRightClick(IEvent event) {
		
	}
	
}
