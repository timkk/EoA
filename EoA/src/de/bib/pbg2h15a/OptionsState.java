package de.bib.pbg2h15a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class OptionsState extends GameState {

	/**
	 * @author pbg2h15aza
	 */
	
	private SpriteBatch batch;
	
	private CheckBoxErstellen cbxSound;
	private CheckBoxErstellen cbxMusic;
	
	private ButtonErstellen btnBack;
	
	protected OptionsState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		batch = new SpriteBatch();
		cbxSound = new CheckBoxErstellen(Gdx.graphics.getWidth()/2 - 350/2, Gdx.graphics.getHeight()/1.5f-42, "img/Buttons/soundChecked.png", "img/Buttons/soundUnchecked.png", GameStateManager.SOUND);
		cbxMusic = new CheckBoxErstellen(Gdx.graphics.getWidth()/2 - 350/2, Gdx.graphics.getHeight()/2.5f-42, "img/Buttons/musicChecked.png", "img/Buttons/musicUnchecked.png", GameStateManager.MUSIC);
		btnBack = new ButtonErstellen(10, Gdx.graphics.getHeight() - 122, "img/Buttons/back.png");
	}

	@Override
	public void update(float dt) {
		if(cbxSound.isClicked()) {
			cbxSound.toggle();
			GameStateManager.SOUND = !GameStateManager.SOUND;
		}
		if(cbxMusic.isClicked()){
			GameStateManager.MUSIC = !GameStateManager.MUSIC;
			cbxMusic.toggle();
			Tunes.MUSIC_MENU.Play();
		}
		if(btnBack.isClicked()) {
			gsm.setState(GameStateManager.MAIN);
		}
		
	}

	@Override
	public void render() {
		batch.begin();
		
		cbxSound.render(batch);
		cbxMusic.render(batch);
		btnBack.render(batch);
		
		batch.end();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}