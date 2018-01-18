package de.bib.pbg2h15a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Menü zur Eingabe der Spielernamen und Textfeldern entsprechend der Anzahl der Spieler die zuvor im LocalGamePrepareState eingestellt wurden.
 * Wenn ein Spieler keinen Namen eingibt wird ein Name zugewiesen.
 * 
 */
public class LocalGamePrepareNames extends GameState{

	/**
	 * @author pbg2h15are
	 * @author pbg2h15aro
	 *
	 */
	

	private Stage stage;
	
	private SpriteBatch batch;
	
	
	private PlayerUIList playerUIs;
	private int playerAmount;
	
	private ButtonErstellen back;
	private ButtonErstellen start;
	
	private BitmapFont font;
	
	/**
	 * LocalGamePrepareNames benögt beim erstellen die Anzahl der Spieler um entsprechend viele Textfelder zu erzeugen.
	 * 
	 * @param gsm Referenz auf den Gamestate Manager
	 * @param playerCount Anzahl der Spieler
	 */
	
	protected LocalGamePrepareNames(GameStateManager gsm, int playerCount) {
		super(gsm);
		playerAmount = playerCount;
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
		
		back = new ButtonErstellen((Gdx.graphics.getWidth() / 2) - 190, (Gdx.graphics.getHeight()-585), "img/GamePrepare/zuruck.png");
		start = new ButtonErstellen((Gdx.graphics.getWidth() / 2) + 30, (Gdx.graphics.getHeight()-585), "img/GamePrepare/start.png");
		
		playerUIs = new PlayerUIList(playerAmount);
		for (PlayerUI p : playerUIs.getList()) {
			stage.addActor(p.getTxf());
		}
	}

	@Override
	public void update(float dt) {
		if(back.isClicked()){
			gsm.setState(GameStateManager.LOCAL_PREPARE);
		}
		
		// Spiel starten
		
		if(start.isClicked()){
			/**
			 * @author pbg2h15agu, pbg2h15afa
			 */
			if(playerAmount == 4){
				gsm.setState(GameStateManager.GAME, playerUIs.getList().get(3).getTxf().getText(), playerUIs.getList().get(2).getTxf().getText(), playerUIs.getList().get(1).getTxf().getText(), playerUIs.getList().get(0).getTxf().getText(), playerAmount);
			}
			if(playerAmount == 3){
				gsm.setState(GameStateManager.GAME, playerUIs.getList().get(2).getTxf().getText(), playerUIs.getList().get(1).getTxf().getText(), playerUIs.getList().get(0).getTxf().getText(), null, playerAmount);
			}
			if(playerAmount == 2){
				gsm.setState(GameStateManager.GAME, playerUIs.getList().get(1).getTxf().getText(), playerUIs.getList().get(0).getTxf().getText(), null, null, playerAmount);
			}
			if(playerAmount == 1){
				gsm.setState(GameStateManager.GAME, playerUIs.getList().get(0).getTxf().getText(), null, null, null, playerAmount);
			}
			/**
			 * 
			 */
		}
		
	}

	@Override
	public void render() {
		batch.begin();
		
		back.render(batch);
		start.render(batch);
		
		/**
		 * @author pbg2h15agu, pbg2h15afa
		 */
		for(int i = 1; i <= playerAmount; ++i){
			font.draw(batch, "Spieler "+i, (Gdx.graphics.getWidth() / 2) - 150, (Gdx.graphics.getHeight()-165-70*i));
		}
		/**
		 * 
		 */
		
		batch.end();
		stage.draw();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
