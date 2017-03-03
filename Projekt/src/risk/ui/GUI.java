package risk.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

import risk.event.RiskGameEvent;
import risk.general.event.EventManager;
import risk.general.event.IEvent;
import risk.general.util.Delegate;
import risk.ui.CustomPanel;

public class GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4784853772774531316L;
	private JMenuBar MenuBar = new JMenuBar();
    private JMenu File = new JMenu("File");
    private JMenuItem newGame = new JMenuItem("New Game");
    private JMenuItem Exit = new JMenuItem("Exit");
    
    private JMenu File1 = new JMenu("Score");
    private JMenuItem score = new JMenuItem("High Score");	
    	
    private JMenuItem newMap = new JMenuItem("Create and Store new Map");
    private JPanel ingame = null;
    private Controls controls = new Controls();
    UI ui;
    
    public GUI(UI ui) {
        InitMenu(ui);
        this.add(controls, BorderLayout.SOUTH);
        this.ui = ui;
        addWindowListener(ui);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(100, 100);
        pack();
        setVisible(true);
        AttachListeners();
    }
    
    private void AttachListeners() {
    	EventManager.Get().AttachListener(new Delegate(this, "StartMap"), RiskGameEvent.EVENT_NEW_GAME);
    }
    
    @SuppressWarnings("unchecked")
	public void StartMap(IEvent event) {
    	BufferedImage bi;
    	CustomPanel cp;
		try {
			bi = ImageIO.read(new File(event.ToString() + ".png"));
			cp = new CustomPanel(bi, ui);
	        
			FileInputStream fileIn = new FileInputStream(event.ToString() + ".gui");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			cp.attachBtns((ArrayList<ZoneButton>)in.readObject());
			in.close();
			
			//this.setContentPane(cp); ////////////
			if(ingame != null) {
				this.remove(ingame);
			}
			this.add(cp, BorderLayout.NORTH);

			ingame = cp;
			
	    	pack();
	    	setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			// SHOULD GIVE WARNING HERE THAT MAP WASNT FOUND.
		}
    }
    
    private void InitMenu(UI ui) {
    	newGame.addActionListener(ui);
        Exit.addActionListener(ui);
        newMap.addActionListener(ui);
    	File.add(newGame);
        File.add(Exit);
        File.add(newMap);
        MenuBar.add(File);
        setJMenuBar(MenuBar);
	
	score.addActionListener(ui);
    	File.add(score);
        MenuBar.add(File1);
        setJMenuBar(MenuBar)
    }
    
    public class Controls extends JPanel {
		private static final long serialVersionUID = -5022575419076933625L;
		
		/////////////////////// PLAYER
    	private static final String strPlayer = "Player: ";
    	private String name = "<none>";
    	JTextField player = new JTextField(strPlayer + name);
    	
    	public void setPlayer(String str) {
    		name = str;
    		player.setText(strPlayer + name);
    	}
    	
    	//////////////////////// PRODUCTION
    	private static final String strProd = "Production: ";
    	private int prod = 0;
    	JTextField production = new JTextField(strProd + prod);
    	
    	public void setProduce(int n) { // MAY NEED TO RE-RENDER.
    		prod = n;
    		production.setText(strProd + prod);
    	}
    	
    	public Controls() {
    		player.setEditable(false);
    		player.setHighlighter(null);
    		this.add(player, BorderLayout.NORTH);
    		
    		production.setEditable(false);
    		production.setHighlighter(null);
    		this.add(production, BorderLayout.NORTH);
    		
        	this.setBackground(Color.lightGray);
        	this.setBorder(BorderFactory.createLineBorder(Color.darkGray));
    	}
    }
}
