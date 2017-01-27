package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Traps extends Collectable{
	
	/**
	 * @author pbg2h15ake
	 * @author pbg2h15are
	 * @author pbg2h15ast
	 * @author pbg2h15afo
	 */
	
	public Traps(Point pos) {
		super(pos);
		
	}
	
	@Override
	public void render(SpriteBatch sb) {
		
		sb.draw(spritesheet, pos.getX(), pos.getY());
	}

	@Override
	public void update(float dt) {
		
	}

}
