package risk.game;

public class Zone {
	private final int x;
	private final int y;
	private final int production;
	
	private int ownerid;
	
	public Zone(int x, int y, int production) {
		this.x = x;
		this.y = y;
		this.production = production;
		ownerid = 0;
	}
	
	public void setOwner(int ownerid) {
		this.ownerid = ownerid;
	}
	
	public int GetX() {
		return x;
	}
	
	public int GetY() {
		return y;
	}
	
	public int GetProduction() {
		return production;
	}
	
	public int GetOwnerID() {
		return ownerid;
	}
}
