package risk.event;

import risk.general.Zone;

public class LclTrySelectEvent extends AEvent {
	public Zone zone;
	public LclTrySelectEvent(Zone z) {
		super(IEvent.EventType.LclTrySelectEvent);
		this.zone = z;
	}

}
