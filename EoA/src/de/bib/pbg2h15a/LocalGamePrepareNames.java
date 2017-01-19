package de.bib.pbg2h15a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class LocalGamePrepareNames extends GameState{

	/**
	 * @author pbg2h15are
	 * @author pbg2h15aro
	 *jojojo
	 */
	

	private Stage stage;
	
	private SpriteBatch batch;
	
	
	private PlayerUIList playerUIs;
	private int playerAmount = 2;
	
	private ButtonErstellen back;
	private ButtonErstellen start;
	
	protected LocalGamePrepareNames(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
		if(back.isClicked()){
			gsm.setState(GameStateManager.MAIN);
		}
		
		// Spiel starten
		
		if(start.isClicked()){
			gsm.setState(GameStateManager.GAME);
		}
		
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
