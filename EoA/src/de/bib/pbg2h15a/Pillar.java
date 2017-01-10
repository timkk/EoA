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
		this.spritesheet = new Texture("img/Stage_1/Saeule.png"); 
	}

	/**
	 * @author pbg2h15asu
	 * @param p Position
	 */
	public Pillar(Point p) {
		
		super(p, false, null);
		this.spritesheet = new Texture("img/Stage_1/Saeule.png"); 
	}
	
	/**
	 * @author pbg2h15asu
	 * @param p Position
	 * @param b true -> innen, false -> auﬂen
	 */
	public Pillar(Point p, boolean b){
		super(p, false, null);
		if(b)
			this.spritesheet = new Texture("img/Stage_1/Saeule.png");
		else 
			this.spritesheet = new Texture("img/Stage_1/Aussenwand.png");
	}

	@Override
	public void render(SpriteBatch sb) {
		
		sb.draw(spritesheet, pos.getX(), pos.getY());
		
	}

	@Override
	public void update(float dt) {
		
		
		
	}

}
