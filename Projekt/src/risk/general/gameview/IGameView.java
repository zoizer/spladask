package risk.general.gameview;

public interface IGameView {

	public static final int GAME_VIEW_TYPE_PLAYER = 1;
	public static final int GAME_VIEW_TYPE_AI = 2;
	public static final int GAME_VIEW_TYPE_REMOTE = 3;
	
	public int GetType();
	public int GetID();
}
