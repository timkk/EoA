package de.bib.pbg2h15a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ModeSelectState extends GameState {

	/**
	 * @author pbg2h15aza
	 * @author pbg2h15awi
	 */
	
	private SpriteBatch batch;
	private BitmapFont font;

	private ButtonErstellen btnLocal;
	private ButtonErstellen btnNetwork;
	private ButtonErstellen btnBack;

	private String modiText;
	
	protected ModeSelectState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		btnLocal = new ButtonErstellen(Gdx.graphics.getWidth()/2 - 350/2, Gdx.graphics.getHeight()/1.5f - 42, "img/Buttons/lokalesSpiel.png");
		btnNetwork = new ButtonErstellen(Gdx.graphics.getWidth()/2 - 350/2, Gdx.graphics.getHeight()/2.5f - 42, "img/Buttons/netzwerkSpiel.png");
		btnBack = new ButtonErstellen(10, Gdx.graphics.getHeight() - 122, "img/Buttons/back.png");
		
		modiText = "Modi auswählen";
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.RED);
		
	}

	@Override
	public void update(float dt) {
		
		if (btnLocal.isClicked()) {
			gsm.setState(GameStateManager.LOCAL_PREPARE);
		} else if (btnNetwork.isClicked()) {
			modiText = "Netzwerk Spiel ausgewählt.";
		} else if(btnBack.isClicked()) {
			gsm.setState(GameStateManager.MAIN);
		}
		
	}

	@Override
	public void render() {

		batch.begin();

		btnLocal.render(batch);
		btnNetwork.render(batch);
		btnBack.render(batch);

		font.draw(batch, modiText, Gdx.graphics.getWidth()/2-20, Gdx.graphics.getHeight()-10);

		batch.end();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}