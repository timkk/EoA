package de.bib.pbg2h15a.GameComp;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.bib.pbg2h15a.Uitl.Point;

/**
 *  definiert die Begrenzung des Spielfeldes und 
 *  sind zeitgleich unzerst�rbare Bl�cke im Spielfeld (Kommentiert von David Langen/pbg2h15aln)
 *
 */

public class Pillar extends GameObject {
	
	/**
	 * 	Konstruktur eines Pillar-Objekt
	 *	@param x stellt die X-Position dar
	 *	@param y stellt die Y-Position dar
	 *	@author pbg2h15ani
	 *  
	 */
	public Pillar(int x, int y) {
		
		super(new Point(x, y), false, null);
		this.spritesheet = new Texture("img/Stage_1/Saeule.png"); 
	}

	/**
	 * Konstruktur eines Pillar-Objekt
	 * @author pbg2h15asu
	 * @param p Position
	 * 			erstellt S�ule mit Gras texture
	 */
	public Pillar(Point p) {
		
		super(p, false, null);
		this.spritesheet = new Texture("img/Stage_1/Saeule.png"); 
	}
	
	/**
	 * Konstruktur eines Pillar-Objekt
	 * @author pbg2h15asu
	 * @param p Position
	 * @param b true -> innen, false -> au�en
	 */
	public Pillar(Point p, boolean b){
		
		super(p, false, null);
		if(b)
			this.spritesheet = new Texture("img/Stage_1/Saeule.png");
		else 
			this.spritesheet = new Texture("img/Stage_1/Aussenwand.png");
	}

	
	/**
	 * zeichnet den Sprite
	 * @param sb legt das SpriteBatch fest
	 */
	@Override
	public void render(SpriteBatch sb) {
		
		sb.draw(this.getSpritesheet(), this.getPos().getX(), this.getPos().getY());
	}

	@Override
	public void update(float dt) {
		
	}
}