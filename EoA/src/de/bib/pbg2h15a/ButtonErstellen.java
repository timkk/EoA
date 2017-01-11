package de.bib.pbg2h15a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * 
 * @author pbg2h15ago 
 * @author pbg2h15awi 
 * @author pbg2h15aza
 *
 */

public class ButtonErstellen {

	private Texture texture;
	private float x;
	private float y;
	
	private boolean imClick;

	public ButtonErstellen(float x, float y,String pfad) {
		texture = new Texture(pfad);
		this.x = x;
		this.y = y;
		imClick=true;
	}

	public boolean isClicked() {
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			if(!imClick){
				Rectangle r = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
				imClick = true;
				return r.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
			}
			
		} else {
			imClick = false;
		}
		return false;
	}

	public void render(SpriteBatch batch) {

		batch.draw(texture, x, y);

	}

}