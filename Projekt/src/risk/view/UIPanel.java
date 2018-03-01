package risk.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import risk.model.Phase;

/**
 * The UIPanel displays and manages some ui directly connected to the game.
 *  
 * 
 * @author 		Filip Törnqvist
 * @version		2018-02-28
 */
public class UIPanel extends JPanel {
	private static final long serialVersionUID = -5022575419076933625L;
	private GridLayout layout;
	private final String strPlayer;
	private String name;
	private JTextField player;
	private final String strProd;
	private int prod;
	private JTextField production;
	private final String strStren;
	private int str;
	private JTextField strength;
	private JTextField ftfPhase;
	private Phase phase;
	private int initTrainableUnits;
	private int trainableUnits;
	private String winner;
	private final String sYourTurn;
	private JTextField yourTurn;
	private boolean bYourTurn;
	
	/**
	 * 
	 * @param bl The desired Layout of the UIPanel
	 */
	public UIPanel(LayoutManager bl) {
		super(bl);
		layout = new GridLayout(0,5);
		strPlayer = "Player: ";
		name = "<none>";
		player = new JTextField(strPlayer + name);
		strProd = "Total Production: ";
		prod = 0;
		production = new JTextField(strProd + prod);
		strStren = "Total Strength: ";
		str = 0;
		strength = new JTextField(strStren + str);
		ftfPhase = new JTextField("");
		phase = Phase.ERROR_DO_NOT_USE;
		initTrainableUnits = 0;
		trainableUnits = 0;
		winner = null;
		sYourTurn = "Your turn!";
		yourTurn = new JTextField("");
		bYourTurn = false;
		
		this.setLayout(layout);
		
		player.setEditable(false);
		player.setHighlighter(null);
		this.add(player);
		
		production.setEditable(false);
		production.setHighlighter(null);
		this.add(production);

		strength.setEditable(false);
		strength.setHighlighter(null);
		this.add(strength);
		
		ftfPhase.setEditable(false);
		ftfPhase.setHighlighter(null);
		this.add(ftfPhase);

		yourTurn.setEditable(false);
		yourTurn.setHighlighter(null);
		this.add(yourTurn);
		
		this.setBackground(Color.lightGray);
    	this.setBorder(BorderFactory.createLineBorder(Color.darkGray));
	}
	
	/**
	 * 
	 * @param str The local player
	 */
	public void setPlayer(String str) {
		name = str;
		player.setText(strPlayer + name);
	}
	
	/**
	 * 
	 * getProduce(getProduce() + change) is a good example if usage
	 * 
	 * @param n The total produce for the local player
	 */
	public void setProduce(int n) { // MAY NEED TO RE-RENDER.
		prod = n;
		production.setText(strProd + prod);
	}
	
	/**
	 * 
	 * 
	 * @return The total produce for the local player
	 */
	public int getProduce() {
		return prod;
	}
	
	/**
	 * setStrength(getStrength() + change) is a good example if usage
	 * 
	 * @param n The total unit strength for the local player
	 */
	public void setStrength(int n) {
		str = n;
		strength.setText(strStren + str);
	}
	
	/**
	 * 
	 * @return The total unit strength for the local player
	 */
	public int getStrength() {
		return str;
	}

	/**
	 * 
	 * setTrainableUnits(setTrainableUnits() + change) is a good example if usage
	 * 
	 * @param n The currently trainable units for the local player
	 */
	public void setTrainableUnits(int n) {
		trainableUnits = n;
		if (phase == Phase.TRAIN_PHASE) {
			ftfPhase.setText("Training phase, units left to train: " + trainableUnits);
		} 
	}
	
	/**
	 * 
	 * @return The currently trainable units
	 */
	public int getTrainableUnits() {
		return trainableUnits;
	}
	
	/**
	 * Resets the trainable units to the production value
	 */
	public void resetTrainableUnits() {
		trainableUnits = prod;
		if (phase == Phase.TRAIN_PHASE) {
			ftfPhase.setText("Training phase, units left to train: " + trainableUnits);
		} 
	}
	
	/**
	 * Set the initial trainable units for the initialization phase of the game
	 * @param n The initial trainable units
	 */
	public void setInitTrainableUnits(int n) {
		initTrainableUnits = n;
		if (phase == Phase.INIT_PHASE) ftfPhase.setText("Starting phase, units left to place: " + initTrainableUnits);
	}
	
	/**
	 * @return The initial trainable units
	 */
	public int getInitTrainableUnits() {
		return initTrainableUnits;
	}
	
	/**
	 * Sets the winner (if set then game visuals cant continue)
	 * @param name Name of the winner
	 */
	public void setWinner(String name) {
		winner = name;
	}
	
	/**
	 * Updates the phase and graphics for that phase
	 * 
	 * @param phase The new (or old) phase
	 */
	public void setPhase(Phase phase) {
		if (winner != null) {
			ftfPhase.setText("Victory for " + winner + "!");
		}
		else if (this.phase != phase) {
			this.phase = phase;
			if (phase == Phase.INIT_PHASE) {
				ftfPhase.setText("Starting phase, units left to place: " + initTrainableUnits);
			} else if (phase == Phase.TRAIN_PHASE) {
				ftfPhase.setText("Training phase, units left to train: " + trainableUnits);
			} else if (phase == Phase.ATTACK_PHASE) {
				ftfPhase.setText("Attack phase, drag click to attack");
			} else if (phase == Phase.ERROR_DO_NOT_USE) {
				ftfPhase.setText("");
			}
		}
	}
	
	/**
	 * Sets graphics to display whether or not its your turn
	 * @param v True if it's your turn, false if not.
	 */
	public void setYourTurn(boolean v) {
		bYourTurn = v;
		if (v) yourTurn.setText(sYourTurn);
		else yourTurn.setText("");
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		this.setBorder(BorderFactory.createLineBorder(bYourTurn ? Color.green : Color.lightGray));
		super.paintComponent(g);
	}
}