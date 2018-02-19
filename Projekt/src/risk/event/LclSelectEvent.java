package risk.event;

import risk.general.Zone;

public class LclSelectEvent extends AEvent {
	public Zone zone;
	public LclSelectEvent(Zone z) {
		super(IEvent.EventType.LclSelectEvent);
		this.zone = z;
		System.out.println("EVENT CREATED: " + toString());
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " Target: " + zone.getName();
	}
}
