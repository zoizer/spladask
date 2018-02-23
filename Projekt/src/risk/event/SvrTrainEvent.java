package risk.event;

import risk.general.Zone;

public class SvrTrainEvent extends ANetEvent {
	private static final long serialVersionUID = -4927500976677698213L;
	public final Zone zone;
	public final int zoneid;
	
	public SvrTrainEvent(Zone z, int zoneid) {
		super(EventType.SvrTrainEvent);
		this.zone = z;
		this.zoneid = zoneid;
		System.out.println("EVENT CREATED: " + toString());
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " Zone: " + zone.getName();
	}
}
