package risk.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import risk.event.*;
import risk.general.event.*;
import risk.general.util.Delegate;
import risk.general.util.ErrorHandler;

public class UI extends WindowAdapter implements ActionListener {
	EventManager eventManager;
	
	public UI(EventManager eventManager) {
		this.eventManager = eventManager;
		
		// Attach Listeners //
		eventManager.AttachListener(new Delegate(this, "Quit"), InputEvent.INPUT_EVENT_QUIT);
	}
	
	 @Override
     public void actionPerformed(ActionEvent e) {
		 String cmd = e.getActionCommand();
		 
		 if(cmd.equals("Exit"))
			 eventManager.QueueEvent(new InputEvent(0.0f, InputEvent.INPUT_EVENT_QUIT, 0));
		 else if(cmd.equals("New Game"))
			 eventManager.QueueEvent(new InputEvent(0.0f, InputEvent.INPUT_EVENT_NEW_GAME, 0));
		 else
			 eventManager.QueueEvent(new InputEvent(0.0f, InputEvent.INPUT_EVENT_ZONE_LEFT_CLICK, Integer.parseInt(cmd)));
     }
     
     public void windowClosing(WindowEvent e) {
     	Quit(null);
     }
     
     public void Quit(IEvent event) {
     	/*int confirm = JOptionPane.showOptionDialog(null,
                 "Are You Sure to Close this Application?",
                 "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                 JOptionPane.QUESTION_MESSAGE, null, null, null);
         if (confirm == JOptionPane.YES_OPTION) {
             System.exit(0);
         }*/
    	if(event != null && event instanceof InputEvent) {
			System.exit(((InputEvent)event).GetData());
    	} else System.exit(0);
     }
}
