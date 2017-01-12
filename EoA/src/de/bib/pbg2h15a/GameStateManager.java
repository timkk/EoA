package de.bib.pbg2h15a;

public class GameStateManager {

	/**
	 * @author pbg2h15aza
	 * @author pbg2h15awi
	 * @author pbg2h15asu
	 */
	
	private GameState gameState;
	
	public static boolean SOUND = true;
	public static boolean MUSIC = true;
	
	public static final int MAIN = 0;
	public static final int MODE_SELECT = 100;
	public static final int LOCAL_PREPARE = 150;
	public static final int OPTIONS = 50;
	public static final int GAME = 200;
	public static final int ENDSCREEN = 300;
	public static final int ROUND_STATISTIC = 3;
	public static final int FINAL_STATISTIC = 4;
	
	
	public GameStateManager() {
		setState(MAIN);
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
		if(state == LOCAL_PREPARE){
			gameState = new LocalGamePrepareState(this);
		}
		if(state == GAME){
			gameState = new LocalGameState(this);
		}
		if(state == FINAL_STATISTIC){
			gameState = FinalStatGameState.getInstance(this);
		}
		
	}
	
	public void setState(int state, Statistic[] stats){
		if(state == ROUND_STATISTIC){
			gameState = new RoundStatGameState(this, stats);
		}
	}
	
	public void update(float dt) {
		gameState.update(dt);
	}
	
	public void render() {
		gameState.render();
	}

}