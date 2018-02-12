package risk.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * the ControlPanel displays and manages some ui directly connected to the game.
 *  
 * 
 * @author 		Filip Törnqvist
 * @version 	04/03
 */
public class ControlPanel extends JPanel {
	private static final long serialVersionUID = -5022575419076933625L;
	/////////////////////// GRID LAYOUT
	private GridLayout layout = new GridLayout(0,3);
	
	
	/////////////////////// PLAYER
	private static final String strPlayer = "Player: ";
	private String name = "<none>";
	private JTextField player = new JTextField(strPlayer + name);
	
	public void setPlayer(String str) {
		name = str;
		player.setText(strPlayer + name);
	}
	
	//////////////////////// PRODUCTION
	private static final String strProd = "Production: ";
	private int prod = 0;
	private JTextField production = new JTextField(strProd + prod);
	
	public void setProduce(int n) { // MAY NEED TO RE-RENDER.
		prod = n;
		production.setText(strProd + prod);
	}
	
	//////////////////////// TIMER
	private TimerButton tbtn = null;
	
	public ControlPanel(LayoutManager bl) {
		super(bl);
		
		this.setLayout(layout);
		
		player.setEditable(false);
		player.setHighlighter(null);
		this.add(player);
		
		production.setEditable(false);
		production.setHighlighter(null);
		this.add(production);
		
		tbtn = new TimerButton();
		this.add(tbtn,  BorderLayout.EAST);
		
    	this.setBackground(Color.lightGray);
    	this.setBorder(BorderFactory.createLineBorder(Color.darkGray));
	}
}