package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * 
 * @author pbg2h15asu
 * empty Gameobject with texture ...
 * Die Klasse Background setzt den Hintergrund mit einer Textur (kommentiert von Dennis Fast)
 */

public class Background extends GameObject{
	
	/**
	 * 
	 * @param pos Position des Hintergrundes
	 * initialisiert  den Hintergrund mit einer Textur
	 */

	public Background(Point pos) {
		super(pos, true, new Texture("img/Stage_1/GrassZentrum.png"));
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * die Methode rendert die Textur
	 * @param sb SpriteBatch auf der gezeichnet wird
	 * 
	 */

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(spritesheet, pos.getX(), pos.getY());
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
}
