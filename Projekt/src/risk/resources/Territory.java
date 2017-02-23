package risk.resources;
import java.util.ArrayList;

public class Territory {

	private String tName;
	private int soldiers;
	private ArrayList<Territory> adjacents;
	private Player owner;
	private Continent continent;
	
}

	public void Terriory(tName n){
		this.tName = n;
		soldiers = new ArrayList<Soldier>();
		adjacents = new ArrayList<Territory>();
	}
	
	public tName getName() {
		return name;
	}
	
	
	
	
	}