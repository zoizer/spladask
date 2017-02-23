package risk.init;

import risk.testing.GameTest;

public class StateManager {
	
	public static enum GameState {
		MAINMENU, GAME, EDITOR
	}
	
	public static GameState gameState = GameState.MAINMENU;
	public static MainMenu mainMenu;
	public static Game game;
	public static Editor editor;

	public static void update(String s) {
		if (s == "Meny"){
			gameState = GameState.MAINMENU;
		} else if (s == "Game"){
			gameState = GameState.GAME;
		}
		switch(gameState) {
		case MAINMENU:
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                MainMenu.createAndShowGUI();
	            }
	        });
			break;
		case GAME:
			GameTest bla = new GameTest();
			break;
		case EDITOR:
			
			break;
		}
		
		
	}
	}
	