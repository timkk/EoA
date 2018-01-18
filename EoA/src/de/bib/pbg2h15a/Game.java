package de.bib.pbg2h15a;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import de.bib.pbg2h15a.GameState.GameStateManager;

/**
 * (Kommentiert von Julian Zameit / pbg2h15aza) Kern des Spiel, �ber die
 * render()-Methode wird erst durch den gsm s�mtliche andere Klassen und deren
 * render()-Methoden asugef�hrt.
 * 
 * @author pbg2h15aza
 * @author pbg2h15awi
 */

public class Game implements ApplicationListener {

	private GameStateManager gsm;

	public void create() {
		gsm = new GameStateManager();
	}

	/**
	 * LibGDX-Methode, die jeden Frame einmal ausgef�hrt wird. Dabei wird zuerst
	 * die Farbe des Screens auf Schwarz (1,1,1,1) gesetzt, danach die
	 * update()-Methode mit der �bergebenen DeltaTime ausgef�hrt und
	 * letztendlich durch render() gerendert.
	 */
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();
	}

	public void resize(int width, int height) {
	}

	public void pause() {
	}

	public void resume() {
	}

	public void dispose() {

	}
}