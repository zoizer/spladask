package risk.ui;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import risk.event.RiskEvent;
import risk.event.RiskGameEvent;
import risk.general.event.EventManager;
import risk.general.event.IEvent;
import risk.general.util.Delegate;

import risk.ui.CustomPanel;

public class GUI extends JFrame {
	private UI ui;
	private JMenuBar MenuBar = new JMenuBar();
    private JMenu File = new JMenu("File");
    private JMenuItem newGame = new JMenuItem("New Game");
    private JMenuItem Exit = new JMenuItem("Exit");
    private JMenuItem newMap = new JMenuItem("Create and Store new Map");
    private JButton[] grid = new JButton[64];
    
    public GUI(UI ui) {
    	this.ui = ui;
        InitMenu(ui);
        //InitGrid();
        addWindowListener(ui);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setPreferredSize(new Dimension(400, 300));
        setLocation(100, 100);
        pack();
        setVisible(true);
        
        AttachListeners();
    }
    
    private void AttachListeners() {
    	EventManager.Get().AttachListener(new Delegate(this, "StartMap"), RiskGameEvent.EVENT_NEW_GAME);
    	////
    	//// add more later.
    	////
    }
    
    public void ChangeUI(UI ui) {
    	this.ui = ui;
    }

    public void StartMap(IEvent event) {
    	BufferedImage bi;
    	CustomPanel cp;
		try {
			bi = ImageIO.read(new File(event.ToString() + ".png"));
			cp = new CustomPanel(bi);
			
			
			
			// Add btns here.
			/*Polygon nw = new Polygon();
	        nw.addPoint(84, 51);
	        nw.addPoint(191, 83);
	        nw.addPoint(215,53);
	        nw.addPoint(287,113);
	        nw.addPoint(312, 127);
	        nw.addPoint(96,128);
			cp.attachBtn(new ZoneButton(nw, "A zone.", 1, 3));*/
			Polygon brazil = new Polygon();
	        brazil.addPoint(378,451);
	        brazil.addPoint(428,475);
	        brazil.addPoint(415,538);
	        brazil.addPoint(392,542);
	        brazil.addPoint(382,559);
	        brazil.addPoint(313,469);
	        
			FileInputStream fileIn = new FileInputStream(event.ToString() + ".gui");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			cp.attachBtns((ArrayList<ZoneButton>)in.readObject());
	        cp.attachBtn(new ZoneButton(brazil, "brazil", 99, 2));
			
			this.setContentPane(cp);
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
    }
    
   /* private void InitGrid() {
    	Container cont = this.getContentPane();
    	cont.removeAll();
    	cont.setLayout(new GridLayout(8,8,3,3));
    	for (int i = 0; i < 64; i++) {
    		grid[i] = new JButton("" + i);
    		grid[i].setPreferredSize(new Dimension(50,50));
    		grid[i].setBackground(Color.GREEN);
    		//grid[i].setOpaque(true);
    		grid[i].addActionListener(ui);
    		cont.add(grid[i]);
    	}
    	
    	grid[10].setText("5");
    }*/
    
}
