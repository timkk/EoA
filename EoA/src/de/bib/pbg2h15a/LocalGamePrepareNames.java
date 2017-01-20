package de.bib.pbg2h15a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class LocalGamePrepareNames extends GameState{

	/**
	 * @author pbg2h15are
	 * @author pbg2h15aro
	 *
	 */
	

	private Stage stage;
	
	private SpriteBatch batch;
	
	
	private PlayerUIList playerUIs;
	private int playerAmount = 2;
	
	private ButtonErstellen back;
	private ButtonErstellen start;
	
	private BitmapFont font;
	
	protected LocalGamePrepareNames(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false);
		font.setColor(Color.BLACK);
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		back = new ButtonErstellen(110, 15, "img/GamePrepare/zuruck.png");
		start = new ButtonErstellen(330, 15, "img/GamePrepare/start.png");
		
		playerUIs = new PlayerUIList(playerAmount);
		for (PlayerUI p : playerUIs.getList()) {
			stage.addActor(p.getTxf());
		}
	}

	@Override
	public void update(float dt) {
		if(back.isClicked()){
			gsm.setState(GameStateManager.MAIN);
		}
		
		// Spiel starten
		
		if(start.isClicked()){
			gsm.setState(GameStateManager.GAME, playerUIs.getList().get(1).getTxf().getText(), playerUIs.getList().get(0).getTxf().getText());
		}
		
	}

	@Override
	public void render() {
		batch.begin();
		
		back.render(batch);
		start.render(batch);
		
		font.draw(batch, "Spieler 1", (Gdx.graphics.getWidth() / 2) - 150, (Gdx.graphics.getHeight()-215));
		font.draw(batch, "Spieler 2", (Gdx.graphics.getWidth() / 2) - 150, (Gdx.graphics.getHeight()-465));
		
		batch.end();
		stage.draw();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
