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
 * (Kommentiert von Kevin Wickel pbg2h15awi)
 */

public class ButtonErstellen {

	private Texture texture;
	private float x;
	private float y;
	
	private boolean imClick;
	
	/**
	 * Initialisiert den Button
	 * @param x Position der x-Achse
	 * @param y Position der y-Achse
	 * @param pfad Dateipfad des Bildes
	 */
	public ButtonErstellen(float x, float y,String pfad) {
		texture = new Texture(pfad);
		this.x = x;
		this.y = y;
		imClick=true;
	}

	/**
	 * Prüft ob der Button geklickt wurde
	 * @return true wenn auf den Button gedrückt wird, sonst false
	 */
	public boolean isClicked() {
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			if(!imClick){
				Rectangle r = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
				imClick = true;
				
				boolean btnHit =  r.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

				//KlickSound abspielen
				if(btnHit)					
					Sounds.EFFECT_BUTTON_CLICKED.Play();
				return btnHit;
				
			}
			
		} else {
			imClick = false;
		}
		return false;
	}

	/**
	 * Rendert den Button
	 * @param batch Batch auf der gezeichnet wird
	 */
	public void render(SpriteBatch batch) {

		batch.draw(texture, x, y);

	}

}