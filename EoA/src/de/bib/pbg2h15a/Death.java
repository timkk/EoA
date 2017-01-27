package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Death extends GameObject{
	
	/**
	 * @author pbg2h15are
	 */
	
	private Point pos;
	private static Texture deathSign = new Texture("img/Stage_1/TodMarker.png");
	
	public Death(Point pos)
	{
		super(pos, true, deathSign);
		this.pos = pos;
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Texture Rendern
		//sb.draw(deathSign, , y, originX, originY, width, height, scaleX, scaleY, rotation);
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

}
