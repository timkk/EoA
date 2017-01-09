package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * 
 * @author pbg2h15asu
 * empty Gameobject with texture ...
 */

public class Background extends GameObject{

	public Background(Point pos) {
		super(pos, true, new Texture("img/Stage_1/GrassZentrum.png"));
		// TODO Auto-generated constructor stub
	}
	
	public Background(Point pos, Texture texture) {
		super(pos, true, texture);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(spritesheet, pos.getX(), pos.getY());
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

}
