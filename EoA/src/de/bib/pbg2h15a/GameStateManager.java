package de.bib.pbg2h15a;

/**
 * Verwaltet die aktuelle GameState
 * @author pbg2h15aza
 * @author pbg2h15awi
 * @author pbg2h15asu
 * (Kommentiert von Kevin Wickel pbg2h15awi)
 */

public class GameStateManager {

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
	
	
	/**
	 * GameStateManager wird aufs HauptMenu gesetzt
	 */
	public GameStateManager() {
		setState(MAIN);
	}

	/**
	 * Wechselt die aktive State
	 * @param state Zielstate in welche gewechselt wird
	 */
	public void setState(int state) {
		
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
	
	/**
	 * Wechselt die aktive State mit Namen der Spieler
	 * @param state Zielstate in welche gewechselt wird
	 * @param name1 Name Spieler 1
	 * @param name2 Name Spieler 2
	 * @param name3 Name Spieler 3
	 * @param name4 Name Spieler 4
	 */
	public void setState(int state, String name1, String name2, String name3, String name4){
		if(state == GAME){
			gameState = new LocalGameState(this, name1, name2, name3, name4, 300, 3);
		}
	}
	
	/**
	 * Wechselt die aktive State mit einem Spieler
	 * @param state Zielstate in welche gewechselt wird
	 * @param player Spieler
	 */
	public void setState(int state, Player player){
		if(state == TMPENDSCREENTDOT){
			gameState = new TmpEndScreenTdoT(this, player);
		}
	}
	
	/**
	 * Wechselt die aktive State mit Statistic
	 * @param state Zielstate in welche gewechselt wird
	 * @param stats Statistics
	 */
	public void setState(int state, Statistic[] stats){
		if(state == ROUND_STATISTIC){
			gameState = new RoundStatGameState(this, stats);
		}
	}
	
	/**
	 * @author pbg2h15agu, pbg2h15afa
	 * Wechselt die aktive State mit Spieleranzahl
	 * @param state Zielstate in welche gewechselt
	 * @param playerCount Anzahl der Spieler
	 */
	public void setState(int state, int playerCount){
		if(state == NAME_STATE){
			gameState = new LocalGamePrepareNames(this, playerCount);
		}
	}

	/**
	 * Updated die aktive State
	 * @param dt DeltaTime
	 */
	public void update(float dt) {
		gameState.update(dt);
	}
	
	/**
	 * Rendert die aktive State
	 */
	public void render() {
		gameState.render();
	}

}