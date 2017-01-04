package de.bib.pbg2h15a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class CheckBoxErstellen {

	/**
	 * @author pbg2h15aza
	 * @author pbg2h15awi
	 */
	
	private Texture textureChecked;
	private Texture textureUnchecked;
	private Texture activeTexture;
	private boolean checked;
	private float x;
	private float y;
	private boolean test;

	public CheckBoxErstellen(float x, float y,String checkedPfad, String uncheckedPfad, boolean checked) {
		textureChecked = new Texture(checkedPfad);
		textureUnchecked = new Texture(uncheckedPfad);
		this.x = x;
		this.y = y;
		this.checked = checked;
		if(checked) {
			activeTexture = textureChecked;
		} else {
			activeTexture = textureUnchecked;
		}

	}

	public boolean isClicked() {
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			if(!test){
				test = true;
				Rectangle r = new Rectangle(x, y, activeTexture.getWidth(), activeTexture.getHeight());			
				return r.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
			}
			
		}else{
			test = false;
		}
		return false;
	}

	public void toggle() {
		checked = !checked;
		if(checked) {
			activeTexture = textureChecked;
		} else {
			activeTexture = textureUnchecked;
		}
	}
	
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub

		batch.draw(activeTexture, x, y);

	}

}
