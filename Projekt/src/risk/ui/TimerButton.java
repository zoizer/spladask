package risk.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import risk.event.EventManager;
import risk.event.IEvent;
import risk.event.RiskGameEvent;
import risk.event.TimeEvent;
import risk.util.Delegate;

/**
 * TimerButton is the visual representation of a automatically clicking button that will click itself once the time runs out.
 * 
 * @author 		Filip Törnqvist
 * @version 	04/03
 */
public class TimerButton extends JButton {
	private static final long serialVersionUID = 1685312584989885880L;
	
	private static final String preText = "Next Turn: ";
	private static final String waiting = "Waiting";
	
	public TimerButton() {
		super(waiting);
		EventManager.get().attachListener(new Delegate(this, "triggerQueueTime"), TimeEvent.EVENT_UPDATE_TIME); 
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventManager.get().queueEvent(new RiskGameEvent(0.0f, RiskGameEvent.EVENT_TRY_SKIP_TURN, ""));
			}
		});
	}
	
	public void triggerQueueTime(IEvent event) {
		switch(((TimeEvent)event).GetStatus()) {
		case UNTIL_TURN:
			this.setText(preText + ((TimeEvent)event).GetTime());
			break;
		case WAIT:
			this.setText(waiting);
			break;
			default: this.setText("Error");
		}
	}
}
