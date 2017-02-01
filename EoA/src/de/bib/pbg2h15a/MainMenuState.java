package de.bib.pbg2h15a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Erstellt das Hauptmenü mit den drei Auswahlbuttons und deren Funktion
 * 
 * @author pbg2h15aza
 * @author pbg2h15awi
 * 
 * (Kommentiert von Marco Struck pbg2h15ast)
 */

public class MainMenuState extends GameState {

	private SpriteBatch batch; 

	private ButtonErstellen btnStart;
	private ButtonErstellen btnOptions;
	private ButtonErstellen btnQuit;

	/**
	 * Konstruktor der Klasse MainMenuState
	 * 
	 * @param gsm
	 */
	protected MainMenuState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	/**
	 * Erstellt den Start/-Quit/-Options Button
	 * 
	 * Erstellt die Main Menu Buttons und versieht diese mit den zughörigen Texturen
	 * Zusätzlich wird die Main Menu Musik abgespielt und ein SpriteBatch erstellt
	 * 
	 */
	@Override
	public void init() {
		btnStart = new ButtonErstellen(Gdx.graphics.getWidth()/2 - 350/2, Gdx.graphics.getHeight()/1.5f, "img/Buttons/startSpiel.png");
		btnOptions = new ButtonErstellen(Gdx.graphics.getWidth()/2 - 350/2, Gdx.graphics.getHeight()/2, "img/Buttons/options.png");
		btnQuit = new ButtonErstellen(Gdx.graphics.getWidth()/2 - 350/2, Gdx.graphics.getHeight()/3, "img/Buttons/quit.png");
		
		batch = new SpriteBatch();
		
		//Musik abspielen
		Tunes.MUSIC_MENU.Play();
	}

	/**
	 * Wechselt in das zugehörige Menü, wenn auf den Start/-Quit/-Options Button geklickt wird
	 * 
	 * Wechselt in die Modiauswahl, wenn auf den Startbutton geklickt wird
	 * Wechselt in das Optionsmenü, wenn auf den Optionsbutton geklickt wird
	 * Beendet die Anwendung, wenn auf den Quitbutton geklickt wird
	 * 
	 */
	@Override
	public void update(float dt) {
		
		if(btnStart.isClicked()) {
			gsm.setState(GameStateManager.MODE_SELECT);
		} else if(btnQuit.isClicked()) {
			Gdx.app.exit();
		} else if(btnOptions.isClicked()) {
			gsm.setState(GameStateManager.OPTIONS);
		}
		
	}

	/**
	 * Erstellt die Texturen der Buttons
	 * 
	 */
	
	@Override
	public void render() {
		batch.begin();
		
		btnStart.render(batch);
		btnOptions.render(batch);
		btnQuit.render(batch);
		
		batch.end();
	}
	
	/**
	 * Gibt Ressourcen frei
	 */
	@Override
	public void dispose() {
		batch.dispose();
	}

}