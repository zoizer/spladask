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
 * the ControlPanel displays and manages some ui directly connected to the game.
 *  
 * 
 * @author 		Filip Törnqvist
 * @version 	04/03
 */
public class UIPanel extends JPanel {
	private static final long serialVersionUID = -5022575419076933625L;
	/////////////////////// GRID LAYOUT
	private GridLayout layout = new GridLayout(0,5);
	
	
	/////////////////////// PLAYER
	private static final String strPlayer = "Player: ";
	private String name = "<none>";
	private JTextField player = new JTextField(strPlayer + name);
	
	public void setPlayer(String str) {
		name = str;
		player.setText(strPlayer + name);
	}
	
	//////////////////////// PRODUCTION
	private static final String strProd = "Total Production: ";
	private int prod = 0;
	private JTextField production = new JTextField(strProd + prod);
	
	public void setProduce(int n) { // MAY NEED TO RE-RENDER.
		prod = n;
		production.setText(strProd + prod);
	}
	
	public int getProduce() {
		return prod;
	}
	
	private static final String strStren = "Total Strength: ";
	private int str = 0;
	private JTextField strength = new JTextField(strStren + str);
	
	public void setStrength(int n) {
		str = n;
		strength.setText(strStren + str);
	}
	
	public int getStrength() {
		return str;
	}

	private static final String sInitPhase = "Units left to place: ";
	private JTextField ftfPhase = new JTextField("");
	private Phase phase = Phase.ERROR_DO_NOT_USE;
	private int unitsLeftToTrain = 0;
	
	public void setTrainableUnits(int n) {
		unitsLeftToTrain = n;
		if (phase == Phase.INIT_PHASE) {
			ftfPhase.setText("Starting phase, units left to place: " + unitsLeftToTrain);
		} else if (phase == Phase.TRAIN_PHASE) {
			ftfPhase.setText("Training phase, units left to train: " + unitsLeftToTrain);
		} 
	}
	
	public int getTrainableUnits() {
		return unitsLeftToTrain;
	}
	
	public void setPhase(Phase phase) {
		if (this.phase != phase) {
			this.phase = phase;
			if (phase == Phase.INIT_PHASE) {
				ftfPhase.setText("Starting phase, units left to place: " + unitsLeftToTrain);
			} else if (phase == Phase.TRAIN_PHASE) {
				ftfPhase.setText("Training phase, units left to train: " + unitsLeftToTrain);
			} else if (phase == Phase.ATTACK_PHASE) {
				ftfPhase.setText("Attack phase, drag click to attack");
			} else if (phase == Phase.MOVE_PHASE) {
				ftfPhase.setText("Move phase, drag click to move");
			} else if (phase == Phase.ERROR_DO_NOT_USE) {
				ftfPhase.setText("");
			}
		}
	}
	
	
	
	private static final String sYourTurn = "Your turn!";
	private JTextField yourTurn = new JTextField("");
	private boolean bYourTurn;
	public void setYourTurn(boolean v) {
		bYourTurn = v;
		if (v) yourTurn.setText(sYourTurn);
		else yourTurn.setText("");
	}
	
	public UIPanel(LayoutManager bl) {
		super(bl);
		
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
	
	@Override
	public void paintComponent(Graphics g) {
		this.setBorder(BorderFactory.createLineBorder(bYourTurn ? Color.green : Color.lightGray));
		super.paintComponent(g);
	}
}