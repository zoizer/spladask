package risk.event;

import risk.general.Zone;

public class LclActionEvent extends AEvent {
	public Zone zone;
	public LclActionEvent(Zone z) {
		super(IEvent.EventType.LclActionEvent);
		this.zone = z;
		System.out.println("EVENT CREATED: " + toString());
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " Target: " + zone.getName();
	}
}
