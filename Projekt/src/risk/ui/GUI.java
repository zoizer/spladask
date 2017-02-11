package risk.ui;

import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame {
	private UI ui;
	private JMenuBar MenuBar = new JMenuBar();
    private JMenu File = new JMenu("File");
    private JMenuItem Exit = new JMenuItem("Exit");
    private JButton[] grid = new JButton[64];
    
    public GUI(UI ui) {
        InitMenu(ui);
        //InitGrid();
        addWindowListener(ui);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setPreferredSize(new Dimension(400, 300));
        setLocation(100, 100);
        pack();
        setVisible(true);
    }
    
    public void ChangeUI(UI ui) {
    	this.ui = ui;
    }

    public void StartMap() {
    	InitGrid();
    	pack();
    	setVisible(true);
    }
    
    private void InitMenu(UI ui) {
        File.add(Exit);
        MenuBar.add(File);
        Exit.addActionListener(ui);
        setJMenuBar(MenuBar);
    }
    
    private void InitGrid() {
    	Container cont = this.getContentPane();
    	cont.setLayout(new GridLayout(8,8,3,3));
    	for (int i = 0; i < 64; i++) {
    		grid[i] = new JButton("" + i);
    		grid[i].setPreferredSize(new Dimension(50,50));
    		grid[i].setBackground(Color.GREEN);
    		//grid[i].setOpaque(true);
    		cont.add(grid[i]);
    	}
    	
    	grid[10].setText("5");
    }
    
}
