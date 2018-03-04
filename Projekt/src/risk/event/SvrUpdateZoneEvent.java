package risk.event;

import risk.general.Phase;
import risk.general.Zone;

/**
 * Sent by server to modify zone.
 * (Sent from server to all)
 * 
 * @see EventType#SvrUpdateZoneEvent
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class SvrUpdateZoneEvent extends ANetEvent {
	private static final long serialVersionUID = -4927500976677698213L;
	
	/**
	 * A new instance of the updated zone
	 */
	public final Zone zone;
	
	/**
	 * the id of the updated zone
	 */
	public final int zoneid;
	
	/**
	 * the phase of the game
	 */
	public final Phase phase;
	
	/**
	 * 
	 * @param z The updated zone
	 * @param zoneid The id of the updated zone
	 * @param phase The phase of the game
	 */
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
