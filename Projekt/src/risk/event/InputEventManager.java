package risk.event;

import risk.game.Zone;
import risk.game.logic.Core;
import risk.gameview.PlayerGameView;
import risk.util.Delegate;
import risk.util.ErrorHandler;

/**
 * This is the EventManager handling all direct input, like clicking zones etc.
 * 
 * 
 * @author 		Filip Törnqvist
 * @version 	01/03
 */
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
		if(event instanceof InputEvent) {
			int zoneid = ((InputEvent)event).GetData();
			if(Core.Get().ZoneExists(zoneid)) {
				if(Core.Get().GetActiveView() instanceof PlayerGameView)
					EventManager.Get().QueueEvent(new RiskZoneEvent(0.0f, RiskZoneEvent.EVENT_SELECT_ZONE, zoneid)); // SELECT ZONE
				else ErrorHandler.WARNING("Can't Select zone cause it's not the players turn!");
			} else ErrorHandler.WARNING("Could not find zone to select.");
		} else ErrorHandler.WARNING("ZoneLeftClick did not get an input event!");
	}
	
	public void ZoneRightClick(IEvent event) {
		if(event instanceof InputEvent) {
			int zoneid = ((InputEvent)event).GetData();
			if(Core.Get().ZoneExists(zoneid)) {
				Zone activeZone = Core.Get().GetZone(Core.Get().GetSelectedZoneID()); // get source zone
				if(activeZone != null) {
					if(activeZone.GetNeighbour(zoneid) != null) {
						EventManager.Get().QueueEvent(new RiskZoneInteractionEvent(0.0f, RiskZoneInteractionEvent.EVENT_ATTACK_ZONE, zoneid, Core.Get().GetSelectedZoneID())); // ATTACK ZONE
					} else ErrorHandler.WARNING("Could not attack this zone, as it is not a neighbour. MAY WANNA ADD AN INGAME WARNING HERE.");
				} else ErrorHandler.WARNING("Could not attack since no zone is selected.");
			} else ErrorHandler.WARNING("Could not find zone to attack.");
		} else ErrorHandler.WARNING("ZoneRightClick did not get an input event!");
	}
	
}
