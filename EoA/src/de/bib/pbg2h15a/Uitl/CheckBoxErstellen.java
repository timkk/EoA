package de.bib.pbg2h15a.Uitl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * @author pbg2h15aza
 * @author pbg2h15awi
 * 
 * Erstellung einer Checkbox
 * Kommentiert von pbg2h15arm
 */

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
	private boolean imClick;
	/**
	 * Erstellt eine Checkbox
	 * @param x = X-Koordinate f�r die Position der CheckBox
	 * @param y = Y-Koordiante f�r die Position der CheckBox
	 * @param checkedPfad = Pfad der Texture f�r die Variable textureChecked
	 * @param uncheckedPfad = Pfad der Texture f�r die Variable textureUnchecked
	 * @param checked = Gibt an ob die CheckBox cheked oder unchecked ist
	 */
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
		imClick = true;

	}

	/**
	 * �berpr�ft ob ein Button geklickt wurde.
	 * @return
	 */
	public boolean isClicked() {
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			if(!imClick){
				imClick = true;
				Rectangle r = new Rectangle(x, y, activeTexture.getWidth(), activeTexture.getHeight());			
				return r.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
			}
			
		}else{
			imClick = false;
		}
		return false;
	}

	/**
	 * �ndert die checked Variable woraufhin sich die activeTexture auf textureChecked oder textureUnchecked stellt
	 */
	public void toggle() {
		checked = !checked;
		if(checked) {
			activeTexture = textureChecked;
		} else {
			activeTexture = textureUnchecked;
		}
	}
	
	/**
	 * Zeichnet die Checkbox
	 * @param batch = Die SpriteBatch auf der gezeichnet wird
	 */
	public void render(SpriteBatch batch) {

		batch.draw(activeTexture, x, y);

	}

}