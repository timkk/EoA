package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pillar extends GameObject {
	
	/**
	 * 
	 * @author pbg2h15ani
	 *  
	 */
	public Pillar(int x, int y) {
		
		super(new Point(x, y), false, null);
		this.spritesheet = new Texture("Saeule.png"); // TODO 
	}

	/**
	 * @author pbg2h15asu
	 * @param p Position
	 */
	public Pillar(Point p) {
		
		super(p, false, null);
		this.spritesheet = new Texture("Saeule.png"); // TODO 
	}

	@Override
	public void render(SpriteBatch sb) {
		
		sb.draw(spritesheet, pos.getX(), pos.getY());
		
	}

	@Override
	public void update(float dt) {
		
		
		
	}

}
