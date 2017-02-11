package risk.ui;

import java.awt.*;
import javax.swing.*;

import risk.event.RiskEvent;
import risk.general.event.EventManager;
import risk.general.event.IEvent;
import risk.general.util.Delegate;

public class GUI extends JFrame {
	private UI ui;
	private JMenuBar MenuBar = new JMenuBar();
    private JMenu File = new JMenu("File");
    private JMenuItem newGame = new JMenuItem("New Game");
    private JMenuItem Exit = new JMenuItem("Exit");
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
    	EventManager.Get().AttachListener(new Delegate(this, "StartMap"), RiskEvent.EVENT_NEW_GAME);
    	////
    	//// add more later.
    	////
    }
    
    public void ChangeUI(UI ui) {
    	this.ui = ui;
    }

    public void StartMap(IEvent event) {
    	InitGrid();
    	pack();
    	setVisible(true);
    }
    
    private void InitMenu(UI ui) {
    	newGame.addActionListener(ui);
        Exit.addActionListener(ui);
    	File.add(newGame);
        File.add(Exit);
        MenuBar.add(File);
        setJMenuBar(MenuBar);
    }
    
    private void InitGrid() {
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
    }
    
}
