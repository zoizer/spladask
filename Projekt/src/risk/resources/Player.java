package risk.resources;
import java.util.ArrayList;

public abstract class Player {

	//player-var
	private int nrOfSoldiers;
	private int nrOfTerritories;
	private int playerID;
	private String playerName;
	public ArrayList<Territory> playerTerritories;
	
	//vars to check owned amount of territories in certain continent for bonus soldiers
	private int ownedInNA;
	private int ownedInSA;
	private int ownedInAsia;
	private int ownedInAU;
	private int ownedInAfrica;
	private int ownedInEU;
	
	public ArrayList<Territory> territoriesNA = new ArrayList<Territory>();
    public ArrayList<Territory> territoriesSA = new ArrayList<Territory>();
    public ArrayList<Territory> territoriesAsia = new ArrayList<Territory>();
    public ArrayList<Territory> territoriesAU = new ArrayList<Territory>();
    public ArrayList<Territory> territoriesAfrica = new ArrayList<Territory>();
    public ArrayList<Territory> territoriesEU = new ArrayList<Territory>();
	
    
    public Player (int initSoldiers, int playerID, String playerName){
    nrOfSoldiers = initSoldiers;
    this.playerID = playerID;
    ownedInNA = 0;
	ownedInSA = 0;
	ownedInAsia = 0;
	ownedInAU = 0;
	ownedInAfrica = 0;
	ownedInEU = 0;
	playerTerritories = new ArrayList<Territory>();
	setPlayerName(playerName);
    }
    
    
    // SETTERS 
    
    public void addOwnedTerritory(Territory territory){
    	playerTerritories.add(territory);
    	
    }
    
   public void setPlayerName(String playerName){
    	//TO-DO: If not a bot:
    	this.playerName = playerName;
    }
   
   // GETTERS
   
   public String getPlayerName(){
	   return this.playerName;
   }
   
   
   // TO-DO(Torsdag kväll)

   /*
    * add territory
    * lose territory
    * get territories
    * add soldiers to territory
    * get soldiers
    * lose soldiers
    * throw dice?
    * calculate bonus
    */
}
