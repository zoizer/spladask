package risk.resources;
import java.util.ArrayList;

public class Territory {

	private String tName;
	private int soldiers;
	private ArrayList<Territory> adjacents;
	private Player owner;
	private Continent continent;
	//private Country cName;
	private int nrOfAdjacents;
	


	public Territory(String n){
		this.tName = n;
		soldiers = 0;
		adjacents = new ArrayList<Territory>();
	}
	

	
	// SETTERS
	public void setAdjacents(Territory[] adjacents){
		for (int i = 0; i < adjacents.length; i++)
			this.adjacents.add(adjacents[i]);
			this.nrOfAdjacents = adjacents.length;
	}
	
	public void setSoldiers(int soldiers){
		this.soldiers = soldiers;
	}
	
	public int addSoldiers(int soldiers){
		if (soldiers + this.soldiers < 0){
			System.out.println("Fel");
	}	else {
		this.soldiers += soldiers;
		
	}	return this.soldiers;	
}
	
	public void changeOwner(Player owner){
		this.owner = owner;
	}
	
	public void loseBattle(){
		soldiers--;
		//owner.loseSoldier();
	}
	
	
	
	// GETTERS
	public int getSoldiers(){
		return this.soldiers;
	}
	
	public Player getOwner(){
		return this.owner;
	}
	
	public Continent getContinent(){
		return this.continent;
	}
	
	//public Country getName() {
	//	return this.cName;
	//}
	
	public boolean hasOwner(){
		if (this.owner == null){
			return false;
		} else 
			return true;
	}
	
}