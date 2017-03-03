package risk.gameview;

import risk.general.gameview.IGameView;

public class GameView implements IGameView {
	private int id;
	private int type;
	private String name = "Unnamed";
	
	public GameView(int id, int type) {
		this.id = id;
		this.type = type;
	}
	
	@Override
	public int GetType() { return type; }

	@Override
	public int GetID() { return id; }
	
	@Override
	public String toString() {
		return name;
	}
	
}
/*

Player input ska gå in i en PlayerGAmeView
därefter ska det in i interna medelanden. System medelanden kan undgå.















*/