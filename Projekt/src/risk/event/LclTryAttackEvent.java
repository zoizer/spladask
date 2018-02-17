package risk.event;

import risk.general.Zone;

public class LclTryAttackEvent extends AEvent {
	public Zone zone;
	public LclTryAttackEvent(Zone z) {
		super(IEvent.EventType.LclTryAttackEvent);
		this.zone = z;
	}

}
