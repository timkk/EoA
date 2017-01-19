package de.bib.pbg2h15a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TmpEndScreenTdoT extends GameState {

	/**
	 * @author pbg2h15ake
	 * @author pbg2h15awi
	 */
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

	@Override
	public void init() {
		batch = new SpriteBatch();
		btnBack = new ButtonErstellen(10, Gdx.graphics.getHeight() - 122, "img/Buttons/back.png");
		font = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false);
		font.setColor(Color.GOLD);
		text = "Herzlichen Glückwunsch " + name + "!\n         Du hast gewonnen!";
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		if(btnBack.isClicked()) {
			gsm.setState(GameStateManager.MAIN);
		}
	}

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
