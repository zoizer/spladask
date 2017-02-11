package risk.gameview;

import risk.general.gameview.IGameView;

public class GameView implements IGameView {
	private int id;
	private int type;
	
	public GameView(int id, int type) {
		this.id = id;
		this.type = type;
	}
	
	@Override
	public int GetType() { return type; }

	@Override
	public int GetID() { return id; }

}
/*

Player input ska g� in i en PlayerGAmeView
d�refter ska det in i interna medelanden. System medelanden kan undg�.















*/