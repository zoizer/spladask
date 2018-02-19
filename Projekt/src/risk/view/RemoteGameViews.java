package risk.view;

import java.util.List;

import risk.event.AEventSystem;
import risk.net.ServerClient;

public class RemoteGameViews extends AEventSystem implements IGameView {
	private List<ServerClient> remoteViews;
	
	public RemoteGameViews(List<ServerClient> remoteViews) {
		this.remoteViews = remoteViews;
		
		for (ServerClient e : remoteViews) {
			e.remoteView(true);
		}
		attachListeners();
	}

	@Override
	public void attachListeners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detachListeners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		detachListeners();
		for (ServerClient e : remoteViews) {
			e.remoteView(false);
		}
	}

}
