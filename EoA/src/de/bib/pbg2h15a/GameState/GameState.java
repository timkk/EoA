package de.bib.pbg2h15a;

/**
 * Abstrakte Klasse für GameStates
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
	 * Wird jeden Frame aufgerufen um Aktionen auszuführen
	 * @param dt ist die Delta Time
	 */
	public abstract void update(float dt);
	
	
	/**
	 * Rendert alle für die State benötigten Grafiken
	 */
	public abstract void render();
	
	/**
	 * Gibt Ressourcen frei
	 */
	public abstract void dispose();

}