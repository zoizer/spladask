package risk.testing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameTest extends JFrame {

	private class WindowListener extends WindowAdapter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            f(); // only for exit atm
        }
        
        public void windowClosing(WindowEvent e) {
        	f();
        }
        
        public void f() {
        	/*int confirm = JOptionPane.showOptionDialog(null,
                    "Are You Sure to Close this Application?",
                    "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }*/
        	System.exit(0);
        }
    }
	
	private JMenuBar MenuBar = new JMenuBar();
    private JMenu File = new JMenu("File");
    private JMenuItem Exit = new JMenuItem("Exit");
    private WindowListener windowListener = new WindowListener();
    private JButton[] grid = new JButton[64];
    
    public GameTest() {
        InitMenu();
        InitGrid();
        addWindowListener(windowListener);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setPreferredSize(new Dimension(400, 300));
        setLocation(100, 100);
        pack();
        setVisible(true);
    }

    private void InitMenu() {
        File.add(Exit);
        MenuBar.add(File);
        Exit.addActionListener(windowListener);
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
