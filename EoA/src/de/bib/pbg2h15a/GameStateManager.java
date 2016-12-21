package de.bib.pbg2h15a;

public class GameStateManager {

	/**
	 * @author pbg2h15aza
	 * @author pbg2h15awi
	 * @author pbg2h15asu
	 */
	
	private GameState gameState;
	
	public static boolean sound = true;
	public static boolean music = true;
	
	public static final int MAIN = 0;
	public static final int MODE_SELECT = 100;
	public static final int OPTIONS = 150;
	public static final int GAME = 200;
	public static final int ENDSCREEN = 300;
	
	public GameStateManager() {
		setState(GAME);
	}
	
	public void setState(int state) {
		if(gameState != null) gameState.dispose();
		
		if(state == MAIN) {
			gameState = new MainMenuState(this);
		}
		if(state == OPTIONS) {
			gameState = new OptionsState(this);
		}
		if(state == MODE_SELECT) {
			gameState = new ModeSelectState(this);
		}
		if(state == GAME){
			//prototyp
			gameState = new LocalGameState(this);
		}
	}
	
	public void update(float dt) {
		gameState.update(dt);
	}
	
	public void render() {
		gameState.render();
	}
	
}
