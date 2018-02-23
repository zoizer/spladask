package risk.event;

import risk.general.Zone;

public class LclSelectEvent extends AEvent {
	private static final long serialVersionUID = 8934274030487786176L;
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
