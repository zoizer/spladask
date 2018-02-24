package risk.model;

import java.util.ArrayList;
import java.util.List;

import risk.event.AEventSystem;
import risk.event.EventType;
import risk.event.IEvent;
import risk.event.RpcUpdateZoneEvent;
import risk.event.SvrNextTurnEvent;
import risk.event.SvrUpdateZoneEvent;
import risk.general.Map;
import risk.general.Zone;
import risk.net.NetPlayer;
import risk.util.Delegate;
import risk.util.ErrorHandler;
import risk.util.TimedEvent;

public class ServerGameModel extends AEventSystem implements IGameModel {
	private Map map;
	private List<NetPlayer> players; // should not change anymore.
	private int activePlayer;
	private Phase phase;
	private final int startingStrength;
	private List<Integer> trainableUnits;
	
	public ServerGameModel(Map map, List<NetPlayer> players, int startingStrength) {
		this.map = map;
		this.players = players;
		this.activePlayer = 0;
		this.phase = Phase.ERROR_DO_NOT_USE;
		this.startingStrength = startingStrength;
		trainableUnits = new ArrayList<Integer>();
		for (int i = 0; i < players.size(); i++)
			trainableUnits.add(this.startingStrength);
		
		attachListeners();

		IEvent ev = new SvrNextTurnEvent(players.get(0), Phase.INIT_PHASE);
		new TimedEvent(3, ev, EventType.ERROR_DO_NOT_USE);
	}
	
	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "svrNextTurn"), EventType.SvrNextTurnEvent);
		attachListener(new Delegate(this, "rpcUpdateZone"), EventType.RpcUpdateZoneEvent);
		
	}

	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "svrNextTurn"), EventType.SvrNextTurnEvent);
		detachListener(new Delegate(this, "rpcUpdateZone"), EventType.RpcUpdateZoneEvent);
	}

	@Override
	public void destroy() {
		detachListeners();
	}
	
	public void svrNextTurn(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrNextTurnEvent);
		SvrNextTurnEvent e = (SvrNextTurnEvent) ev;
		phase = e.phase;
		activePlayer = players.indexOf(e.playersTurn);
	}
	
	
	
	public int getNextPlayerIndex() {
		int tmp = activePlayer + 1;
		if (tmp == players.size()) return 0;
		return tmp;
	}
	
	public Phase TryUpdatePhase() {
		if (phase == Phase.INIT_PHASE) {
			for (Integer e : trainableUnits) if (e != 0) return phase; // no change
			phase = Phase.TRAIN_PHASE; // update if no 
			return phase;
		} else if (phase == Phase.TRAIN_PHASE) {
			return phase;
		} else if (phase == Phase.ATTACK_PHASE) {
			return phase;
		} else if (phase == Phase.MOVE_PHASE) {
			return phase;
		}
		
		return Phase.ERROR_DO_NOT_USE;
	}
	
	public void rpcUpdateZone(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof RpcUpdateZoneEvent);
		RpcUpdateZoneEvent e = (RpcUpdateZoneEvent) ev;
		
		if (e.player.equals(players.get(activePlayer).name)) {
			Zone z = map.getZone(e.zoneId).clone();
			if (!z.hasOwner() || (z.getOwner().equals(e.player))) {
				// NEED TO VALIDATE UNIT CHANGE.
				int max = trainableUnits.get(activePlayer);
				if (phase == Phase.INIT_PHASE && ((max - e.unitChange) < 0)) return; // cant train that many units.
				trainableUnits.set(activePlayer, max - e.unitChange);
				z.setOwner(e.player);
				z.setArmy(z.getArmy() + e.unitChange);
				queueEvent(new SvrUpdateZoneEvent(z, e.zoneId, phase));
				queueEvent(new SvrNextTurnEvent(players.get(getNextPlayerIndex()), TryUpdatePhase()));
			}
		}
	}
	
}
