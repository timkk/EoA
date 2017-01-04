package de.bib.pbg2h15a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenuState extends GameState {

	/**
	 * @author pbg2h15aza
	 * @author pbg2h15awi
	 */
	
	private SpriteBatch batch;

	private ButtonErstellen btnStart;
	private ButtonErstellen btnOptions;
	private ButtonErstellen btnQuit;

	
	protected MainMenuState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		btnStart = new ButtonErstellen(Gdx.graphics.getWidth()/2 - 219/2, Gdx.graphics.getHeight()/1.5f, "assets/startSpiel.jpg");
		btnOptions = new ButtonErstellen(Gdx.graphics.getWidth()/2 - 219/2, Gdx.graphics.getHeight()/2, "assets/options.jpg");
		btnQuit = new ButtonErstellen(Gdx.graphics.getWidth()/2 - 219/2, Gdx.graphics.getHeight()/3, "assets/quit.jpg");
		
		batch = new SpriteBatch();
	}

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

	@Override
	public void render() {
		batch.begin();
		
		btnStart.render(batch);
		btnOptions.render(batch);
		btnQuit.render(batch);
		
		batch.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
	
}
