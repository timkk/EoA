package de.bib.pbg2h15a.GameState;

import de.bib.pbg2h15a.GameState.GameStateManager;

/**
 * Abstrakte Klasse f�r GameStates
 * 
 * @author pbg2h15aza
 * @author pbg2h15awi
 * 
 * (Kommentiert von Johnny Gunko pbg2h15agu)
 */

public abstract class GameState {
	
	protected GameStateManager gsm;
	
	/**
	 * Konstruktor 
	 * @param gsm der GameStateManager, als Referenz
	 */
	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	
	/**
	 * Initalizierung der Variablen
	 */
	public abstract void init();
	
	
	/**
	 * Wird jeden Frame aufgerufen um Aktionen auszuf�hren
	 * @param dt ist die Delta Time
	 */
	public abstract void update(float dt);
	
	
	/**
	 * Rendert alle f�r die State ben�tigten Grafiken
	 */
	public abstract void render();
	
	/**
	 * Gibt Ressourcen frei
	 */
	public abstract void dispose();

}