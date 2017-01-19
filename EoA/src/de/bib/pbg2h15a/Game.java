package de.bib.pbg2h15a;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Game implements ApplicationListener {

	/**
	 * @author pbg2h15aza
	 * @author pbg2h15awi
	 */

	private GameStateManager gsm;

	public void create() {
		gsm = new GameStateManager();
	}

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