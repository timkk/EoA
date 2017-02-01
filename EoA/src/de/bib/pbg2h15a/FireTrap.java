package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;

/**
 * (kommentiert von Fortmeier)
 * @author pbg2h15afo, pbg2h15awi, pbg2h15ast
 *
 */

public class FireTrap extends Traps {

	/**
	 * Konstruktor für die FireTrap
	 * @param pos die position der Falle
	 */
	public FireTrap(Point pos) {
		super(pos);
		
		this.spritesheet = new Texture("img/Stage_1/FeuerFalle.png");
		
	}

	/**
	 * Gibt eine Bome zurück
	 * 
	 * @return eine Bombe mit max Reichweite
	 */
	public Bomb activate(){
		Player p = new Player("Fire", pos, Player_Frames.P1_MV_DOWN, null, null);
		p.setBombRadius(20);
		return new Bomb(p, pos, 20);
	}
	
}
