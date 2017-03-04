package risk.gameview;

public class NetworkGameView extends GameView {

	/**
	 * Supposed to manage a user from a remote computer.
	 * 
	 * 
	 * @author 		Filip T�rnqvist
	 * @version 	04/03
	 */
public NetworkGameView(int id) {
		super(id, GAME_VIEW_TYPE_REMOTE);
	}
}
