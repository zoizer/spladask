package risk.gameview;


/**
 * GameView is a class that representates a players view, be that a remote player, AI or just a normal pc user.
 * (Last notable change: merge with IGameView)
 * 
 * @author 		Filip Törnqvist
 * @version 	04/03
 */
public class GameView {
	public static final int GAME_VIEW_TYPE_PLAYER = 1;
	public static final int GAME_VIEW_TYPE_AI = 2;
	public static final int GAME_VIEW_TYPE_REMOTE = 3;
	
	private int id;
	private int type;
	private String name = "Unnamed";
	
	public GameView(int id, int type) {
		this.id = id;
		this.type = type;
	}
	
	public int GetType() { return type; }

	public int GetID() { return id; }
	
	public String toString() {
		return name;
	}
	
}
