package de.bib.pbg2h15a.GameComp;

import com.badlogic.gdx.graphics.Texture;

import de.bib.pbg2h15a.Uitl.Point;

/**
 * Oberklasse der <code>PowerUp</code>, <code>Illness</code> und <code>Traps</code>.
 * 
 * @author pbg2h15asu
 * @author pbg2h15awi
 */

public abstract class Collectable extends GameObject {
	
	/**
	 * Der Konstruktor erstellt ein <code>Collectable</code> an einem Punkt <code>p</code>.
	 * 	
	 * @param p
	 */
	public Collectable(Point p) {
		super(p, true, null);
	}
}