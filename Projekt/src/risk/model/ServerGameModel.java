package risk.model;

import java.util.List;

import risk.event.AEventSystem;
import risk.event.EventType;
import risk.event.IEvent;
import risk.event.RpcTrainEvent;
import risk.event.SvrNextTurnEvent;
import risk.event.SvrTrainEvent;
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
	private boolean initPhase;
	
	public ServerGameModel(Map map, List<NetPlayer> players) {
		this.map = map;
		this.players = players;
		this.activePlayer = 0;
		this.initPhase = true;
		attachListeners();

		IEvent ev = new SvrNextTurnEvent(players.get(0), initPhase);
		new TimedEvent(3, ev, EventType.ERROR_DO_NOT_USE);
	}
	
	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "svrNextTurn"), EventType.SvrNextTurnEvent);
		attachListener(new Delegate(this, "rpcTrain"), EventType.RpcTrainEvent);
		
	}

	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "svrNextTurn"), EventType.SvrNextTurnEvent);
		detachListener(new Delegate(this, "rpcTrain"), EventType.RpcTrainEvent);
	}

	@Override
	public void destroy() {
		detachListeners();
	}
	
	public void svrNextTurn(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrNextTurnEvent);
		SvrNextTurnEvent e = (SvrNextTurnEvent) ev;
		activePlayer = players.indexOf(e.playersTurn);
	}
	
	
	
	public NetPlayer getNextPlayer() {
		int tmp = activePlayer + 1;
		if (tmp == players.size()) return players.get(0);
		return players.get(tmp);
	}
	
	public void rpcTrain(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof RpcTrainEvent);
		RpcTrainEvent e = (RpcTrainEvent) ev;
		
		if (e.player.equals(players.get(activePlayer).name)) {
			Zone z = map.getZone(e.zoneId);
			if (!z.hasOwner() || (z.getOwner().equals(e.player))) {
				z.setOwner(e.player);
				z.setArmy(z.getArmy() + 1);
				queueEvent(new SvrTrainEvent(z.clone(), e.zoneId));
				queueEvent(new SvrNextTurnEvent(getNextPlayer(), initPhase));
			}
		}
	}
	
}
