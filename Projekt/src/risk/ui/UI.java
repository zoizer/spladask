package risk.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import risk.general.event.EventManager;

public class UI extends WindowAdapter implements ActionListener {
	EventManager eventManager;
	
	public UI(EventManager eventManager) {
		this.eventManager = eventManager;
	}
	
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
