package risk.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import risk.event.RiskGameEvent;
import risk.event.TimeEvent;
import risk.general.event.EventManager;
import risk.general.event.IEvent;
import risk.general.util.Delegate;


public class TimerButton extends JButton {
	private static final long serialVersionUID = 1685312584989885880L;
	
	private static final String preText = "Next Turn: ";
	private static final String waiting = "Waiting";
	
	public TimerButton() {
		super(waiting);
		EventManager.Get().AttachListener(new Delegate(this, "UpdateTime"), TimeEvent.EVENT_UPDATE_TIME); 
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventManager.Get().QueueEvent(new RiskGameEvent(0.0f, RiskGameEvent.EVENT_TRY_SKIP_TURN, ""));
			}
		});
	}
	
	public void UpdateTime(IEvent event) {
		switch(((TimeEvent)event).GetStatus()) {
		case UNTIL_TURN:
			this.setText(preText + ((TimeEvent)event).GetTime());
			break;
		case GRACE:
			this.setText(((TimeEvent)event).GetMsg() + " get ready " + ((TimeEvent)event).GetTime());
			break;
		case WAIT:
			this.setText(waiting);
			break;
			default: this.setText("Error");
		}
	}
}
