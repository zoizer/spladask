package risk.event;

import risk.model.Phase;
import risk.net.NetPlayer;

public class SvrNextTurnEvent extends ANetEvent {
	private static final long serialVersionUID = 8003248686452798392L;
	public final NetPlayer playersTurn;
	public final Phase phase;
	
	public SvrNextTurnEvent(NetPlayer p, Phase phase) {
		super(EventType.SvrNextTurnEvent);
		this.playersTurn = p;
		this.phase = phase;
		System.out.println("EVENT CREATED: " + toString());
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " Player: " + playersTurn.name;
	}
}
