package risk.event;

import risk.general.Zone;
import risk.model.Phase;

public class SvrUpdateZoneEvent extends ANetEvent {
	private static final long serialVersionUID = -4927500976677698213L;
	public final Zone zone;
	public final int zoneid;
	public final Phase phase;
	
	public SvrUpdateZoneEvent(Zone z, int zoneid, Phase phase) {
		super(EventType.SvrUpdateZoneEvent);
		this.zone = z;
		this.zoneid = zoneid;
		this.phase = phase;
		System.out.println("EVENT CREATED: " + toString());
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " Zone: " + zone.getName();
	}
}
