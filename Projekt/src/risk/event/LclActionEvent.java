package risk.event;

import risk.general.Zone;

public class LclActionEvent extends AEvent {
	private static final long serialVersionUID = -1325136646688035490L;
	public Zone zone;
	public LclActionEvent(Zone z) {
		super(EventType.LclActionEvent);
		this.zone = z;
		System.out.println("EVENT CREATED: " + toString());
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " Target: " + zone.getName();
	}
}
