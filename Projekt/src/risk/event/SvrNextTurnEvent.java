package risk.event;

import risk.net.NetPlayer;

public class SvrNextTurnEvent extends ANetEvent {
	private static final long serialVersionUID = 8003248686452798392L;
	public final NetPlayer playersTurn;
	public final boolean initPhase;
	
	public SvrNextTurnEvent(NetPlayer p, boolean initPhase) {
		super(EventType.SvrNextTurnEvent);
		this.playersTurn = p;
		this.initPhase = initPhase;
		System.out.println("EVENT CREATED: " + toString());
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " Player: " + playersTurn.name;
	}
}
