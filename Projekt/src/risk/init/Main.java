package risk.init;

import risk.game.Game;


/**
 * Main. the startup
 * 
 * 
 * @author 		Filip Törnqvist
 * @version 	28/02
 */

import risk.model.InstanceModel;
import risk.view.InstanceView;
import risk.controller.InstanceController;
import risk.event.EventManager;

public final class Main {
	
	
	public static void main(String[] args) {
		//@SuppressWarnings("unused")
		//Game g = new Game();

		@SuppressWarnings("unused") 
		InstanceModel 		m = new InstanceModel();
		@SuppressWarnings("unused") 
		InstanceView 		v = new InstanceView();
		@SuppressWarnings("unused") 
		InstanceController 	c = new InstanceController();
		
		while (true) {
			EventManager.Get().Update();
		}
	}
}
