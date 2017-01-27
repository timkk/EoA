package de.bib.pbg2h15a;

import com.badlogic.gdx.Gdx;

public class GameStateManager {

	/**
	 * @author pbg2h15aza
	 * @author pbg2h15awi
	 * @author pbg2h15asu
	 */
	
	public static GameState gameState;
	
	public static boolean SOUND = true;
	public static boolean MUSIC = true;
	
	public static final int MAIN = 0;
	public static final int MODE_SELECT = 100;
	public static final int LOCAL_PREPARE = 150;
	public static final int OPTIONS = 50;
	public static final int GAME = 200;
	public static final int ENDSCREEN = 300;
	public static final int TMPENDSCREENTDOT = 301;
	public static final int ROUND_STATISTIC = 3;
	public static final int FINAL_STATISTIC = 4;
	public static final int NAME_STATE = 500;
	
	
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
			gameState = new LocalGameState(this, null, null, null, null, 300, 3);
		}
		if(state == FINAL_STATISTIC){
			gameState = FinalStatGameState.getInstance(this);
		}
		
	}
	
	public void setState(int state, String name1, String name2, String name3, String name4){
		if(state == GAME){
			gameState = new LocalGameState(this, name1, name2, name3, name4, 300, 3);
		}
	}
	
	
	public void setState(int state, Player player){
		if(state == TMPENDSCREENTDOT){
			gameState = new TmpEndScreenTdoT(this, player);
		}
	}
	
	public void setState(int state, Statistic[] stats){
		if(state == ROUND_STATISTIC){
			gameState = new RoundStatGameState(this, stats);
		}
	}
	
	/**
	 * @author pbg2h15agu, pbg2h15afa
	 */
	public void setState(int state, int playerCount){
		if(state == NAME_STATE){
			gameState = new LocalGamePrepareNames(this, playerCount);
		}
	}
	/**
	 * 
	 */
	
	public void update(float dt) {
		gameState.update(dt);
	}
	
	public void render() {
		gameState.render();
	}

}