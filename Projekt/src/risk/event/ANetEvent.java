package risk.event;

import java.io.Serializable;

public abstract class ANetEvent extends AEvent implements Serializable {
	private static final long serialVersionUID = -4632366655812185878L;

	public ANetEvent(EventType e) {
		super(e);
	}
}
