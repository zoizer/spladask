package risk.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import risk.event.InputEvent;
import risk.event.RiskEvent;
import risk.general.event.EventManager;
import risk.general.util.ErrorHandler;

public class UI extends WindowAdapter implements ActionListener {
	EventManager eventManager;
	
	public UI(EventManager eventManager) {
		this.eventManager = eventManager;
	}
	
	 @Override
     public void actionPerformed(ActionEvent e) {
		 String cmd = e.getActionCommand();
		 
		 if(cmd.equals("Exit")) {
			 f();
		 } else if(cmd.equals("New Game")) {
			 EventManager.Get().QueueEvent(new RiskEvent(0.0f, RiskEvent.EVENT_NEW_GAME_REQUEST));
		 } else {
			 int i = Integer.parseInt(cmd);
			 int x = i % 8;
			 int y = i / 8;
			 eventManager.QueueEvent(new InputEvent(0.0f, InputEvent.INPUT_EVENT_MAP_LEFT_CLICK, x, y));
		 }
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
