package risk.event;

/**
 * RiskGameEvent contains an event generally relevant to the entire game.
 * 
 * 
 * @author 		Filip T�rnqvist
 * @version 	04/02
 */
public class RiskGameEvent extends RiskEvent {
	public final static int EVENT_ID = 5000;
	public final static int EVENT_NEW_GAME = 1 + EVENT_ID;
	public final static int EVENT_LOAD_GAME = 2 + EVENT_ID;
	public final static int EVENT_TRY_SKIP_TURN = 3 + EVENT_ID;
	public final static int EVENT_NEW_TURN = 5 + EVENT_ID;
	
	private String msg;
	
	public RiskGameEvent(float timestamp, int eventType, String msg) {
		super(timestamp, eventType);
		this.msg = msg;
	}

	@Override
	public String ToString() {
		return msg;
	}
}
