package de.bib.pbg2h15a;

public abstract class GameState {

	/**
	 * @author pbg2h15aza
	 * @author pbg2h15awi
	 */
	
	protected GameStateManager gsm;
	
	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public abstract void init();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();
}
