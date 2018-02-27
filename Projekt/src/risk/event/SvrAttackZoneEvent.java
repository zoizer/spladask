package risk.event;

import risk.general.Zone;
import risk.model.Phase;

public class SvrAttackZoneEvent extends ANetEvent {
	private static final long serialVersionUID = -4641731324689810567L;
	
	public final Zone src;
	public final Zone dst;
	public final int srcid;
	public final int dstid;
	public final int lostArmySrc;
	public final int lostArmyDst;
	public final boolean attack;
	public final String winner;
	public final Phase phase;
	
	public SvrAttackZoneEvent(Zone src, Zone dst, boolean attack, int srcid, int dstid, int lostArmySrc, int lostArmyDst, String winner, Phase phase) {
		super(EventType.SvrAttackZoneEvent);
		this.src = src;
		this.dst = dst;
		this.srcid = srcid;
		this.dstid = dstid;
		this.lostArmySrc = lostArmySrc;
		this.lostArmyDst = lostArmyDst;
		this.attack = attack;
		this.winner = winner;
		this.phase = phase;
		System.out.println("EVENT CREATED: " + toString());
	}

}
