package risk.gameview;

import risk.general.gameview.IGameView;

public class GameView implements IGameView {
	private int id;
	private int type;
	private int selectedZone;
	
	public GameView(int id, int type) {
		this.id = id;
		this.type = type;
		selectedZone = -1;
	}
	
	@Override
	public int GetType() { return type; }

	@Override
	public int GetID() { return id; }
	
	public int GetSelectedZoneID() {
		return selectedZone;
	}
	
	public void SetSelectedZoneID(int id) {
		selectedZone = id;
	}

}
/*

Player input ska gå in i en PlayerGAmeView
därefter ska det in i interna medelanden. System medelanden kan undgå.















*/