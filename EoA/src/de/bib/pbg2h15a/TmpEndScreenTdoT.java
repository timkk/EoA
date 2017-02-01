package de.bib.pbg2h15a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
  * @author pbg2h15ake
  * @author pbg2h15awi
  * Endscreen nach dem Ablauf des Spiels um den Sieger anzuzeigen
  * (Kommentiert von Felix Kellmereit pbg2h15ake)
  */
public class TmpEndScreenTdoT extends GameState {

	
	private SpriteBatch batch;
	private BitmapFont font;
	
	private ButtonErstellen btnBack;
	private String name;
	private String text;

	protected TmpEndScreenTdoT(GameStateManager gsm,Player player) {
		super(gsm);
		this.name = player.getName();
		init();
	}
	/**
	 * Initialisiert alle Attribute
	 */
	@Override
	public void init() {
		batch = new SpriteBatch();
		btnBack = new ButtonErstellen(10, Gdx.graphics.getHeight() - 122, "img/Buttons/back.png");
		font = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false);
		font.setColor(Color.GOLD);
		text = "Herzlichen Glückwunsch " + name + "!\n         Du hast gewonnen!";
	}
	
	/**
	 * Wechselt beim klicken auf Back-Button zum Hauptmenü
	 */
	@Override
	public void update(float dt) {
		if(btnBack.isClicked()) {
			gsm.setState(GameStateManager.MAIN);
		}
	}
	
	/**
	 * Rendert den Endscreen 
	 */
	@Override
	public void render() {
		batch.begin();
		btnBack.render(batch);

		font.draw(batch, text, Gdx.graphics.getWidth()/2-200, Gdx.graphics.getHeight()/2);
		batch.end();
	}

	@Override
	public void dispose() {

	}
	
	
	
	

}
