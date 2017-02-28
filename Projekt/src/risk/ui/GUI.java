package risk.ui;

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
	private UI ui;
	private JMenuBar MenuBar = new JMenuBar();
    private JMenu File = new JMenu("File");
    private JMenuItem newGame = new JMenuItem("New Game");
    private JMenuItem Exit = new JMenuItem("Exit");
    private JMenuItem newMap = new JMenuItem("Create and Store new Map");
    
    public GUI(UI ui) {
    	this.ui = ui;
        InitMenu(ui);
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
    
    public void ChangeUI(UI ui) {
    	this.ui = ui;
    }

    public void StartMap(IEvent event) {
    	BufferedImage bi;
    	CustomPanel cp;
		try {
			bi = ImageIO.read(new File(event.ToString() + ".png"));
			cp = new CustomPanel(bi);
	        
			FileInputStream fileIn = new FileInputStream(event.ToString() + ".gui");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			cp.attachBtns((ArrayList<ZoneButton>)in.readObject());
			
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
}
