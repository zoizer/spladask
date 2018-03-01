package risk.view;

import java.util.List;

import risk.event.AEventSystem;
import risk.net.ServerClient;

/**
 * This class represents all remote views
 * Knows about the ServerClients
 * @author 		Filip Törnqvist
 * @version 	2018-03-01
 *
 */
public class RemoteGameViews extends AEventSystem implements IGameView {
	private List<ServerClient> remoteViews;
	
	/**
	 * 
	 * @param remoteViews the list of all remote players
	 */
	public RemoteGameViews(List<ServerClient> remoteViews) {
		this.remoteViews = remoteViews;
		
		for (ServerClient e : remoteViews) {
			e.setRemoteViewStatus(true);
		}
		attachListeners();
	}

	/*
	 * (non-Javadoc)
	 * @see risk.event.IEventSystem#attachListeners()
	 */
	@Override
	public void attachListeners() {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * @see risk.event.IEventSystem#detachListeners()
	 */
	@Override
	public void detachListeners() {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * @see risk.util.IDestroyable#destroy()
	 */
	@Override
	public void destroy() {
		detachListeners();
		for (ServerClient e : remoteViews) {
			e.setRemoteViewStatus(false);
		}
	}

}
