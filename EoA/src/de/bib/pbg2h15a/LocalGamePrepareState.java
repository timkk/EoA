package de.bib.pbg2h15a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;



/**
 * 
 * @author pbg2h15ala
 */

public class LocalGamePrepareState extends GameState {
	
	private Stage stage;
	Skin skinName;
	
	
	int mapNr=1;
	int rundenZeitMin=3;
	int rundenZeitSek=30;
	int rundenAnzahl =3;

	// Textures
	ButtonErstellen name;
	private TextField txfName;	

	ButtonErstellen changeStage;
	ButtonErstellen changeStageLeft;
	ButtonErstellen changeStageRight;
	texHelper changeStageValue;

	ButtonErstellen runden;
	ButtonErstellen rundenLeft;
	ButtonErstellen rundenRight;
	texHelper rundenValue1;
	texHelper rundenValue10;

	ButtonErstellen zeit;
	ButtonErstellen zeitLeft;
	ButtonErstellen zeitRight;
	BitmapFont zeitValue;

		

	ButtonErstellen zuruck;
	ButtonErstellen start;

	private SpriteBatch batch;

	protected LocalGamePrepareState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		batch = new SpriteBatch();    	
    	buttonSet();
    	
    	stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		

		// Namenseingabefeld
		/**
		 * @author pbg2h15ago, pbg2h15ani
		 */
		skinName = new Skin(Gdx.files.internal("uiskin.json"));
		
		txfName = new TextField("", skinName);
		txfName.setPosition(330, 375);
		txfName.setSize(200, 50);
		stage.addActor(txfName);
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		/**
		 * @author pbg2h15ala, pbg2h15ago
		 */
		
		//Zurückbutton
		
		if(zuruck.isClicked()){
			gsm.setState(GameStateManager.MAIN);
		}
		
		// Spiel starten
		
		if(start.isClicked()){
			gsm.setState(GameStateManager.GAME);
		}
		
		/**
		 *  @author pbg2h15ala, pbg2h15ago
		 */
		
		// Stage einstellen
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		if (changeStageLeft.isClicked()) {
			mapNr--;
			if (mapNr < 1) {
				mapNr = 3;
			}
			changeStageValue.setTexture("textures/stage_" + mapNr + ".png");
			System.out.println(mapNr);
		}
		if (changeStageRight.isClicked()) {
			mapNr++;
			if (mapNr > 3) {
				mapNr = 1;
			}
			changeStageValue.setTexture("textures/stage_" + mapNr + ".png");
		}
		
		/**
		 * @author pbg2h15ago
		 */
		
		// Zeit einstellen
		
		if(zeitRight.isClicked() && rundenZeitMin <= 5){
			rundenZeitSek += 10;
			if(rundenZeitSek >= 10 && rundenZeitMin == 5){
				rundenZeitSek = 0;
				rundenZeitMin = 1;
			}else{
			if(rundenZeitSek >= 60){
				
					rundenZeitSek = 0;
					rundenZeitMin += 1;	
				}
				
			}
		}
		if(rundenZeitSek < 10){
			zeitValue.draw(batch, ""+rundenZeitMin+":0"+rundenZeitSek, 380, 195);
		}else{
			zeitValue.draw(batch, ""+rundenZeitMin+":"+rundenZeitSek, 380, 195);
		}
		

		if(zeitLeft.isClicked() && rundenZeitMin >= 1){
			rundenZeitSek -= 10;
			if(rundenZeitSek < 0){
				if(rundenZeitMin <= 1 && rundenZeitSek < 0){
					rundenZeitMin = 5;
					rundenZeitSek = 0;
				}else{
					rundenZeitSek = 50;
					rundenZeitMin -= 1;
				}
				
			}
			
		}
		/**
		 * @author pbg2h15ago,pbg2h15aln
		 */
		
		//Gewinnrundenanzahl
      if(rundenRight.isClicked() && rundenAnzahl < 5){
        	rundenAnzahl++;
      }
        
        
       if(rundenLeft.isClicked() && rundenAnzahl > 1){
        	rundenAnzahl--;
       }
       
       setNumberTo(rundenAnzahl, rundenValue10, rundenValue1);
		
		/**
		 *  @author pbg2h15ala
		 */

		name.render(batch);


		changeStage.render(batch);
		changeStageLeft.render(batch);
		changeStageRight.render(batch);
		changeStageValue.render(batch);

		runden.render(batch);
		rundenLeft.render(batch);
		rundenValue1.render(batch);
		rundenValue10.render(batch);
		rundenRight.render(batch);

		zeit.render(batch);
		zeitLeft.render(batch);

		zeitRight.render(batch);

		start.render(batch);
		zuruck.render(batch);

		batch.end();
		stage.draw();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	private void buttonSet(){
		
		/**
		 * @author pbg2h15ala
		 */

		// 0.Zeile

		name = new ButtonErstellen(110, 375, "textures/name.png");


		// 1. Zeile

		changeStage = new ButtonErstellen(110, 285, "textures/stage.png");
		changeStageLeft = new ButtonErstellen(330, 285, "textures/arrow_left.png");
		changeStageValue = new texHelper(380, 285, "textures/stage_1.png");
		changeStageRight = new ButtonErstellen(480, 285, "textures/arrow_right.png");

		// 2. Zeile

		runden = new ButtonErstellen(110, 195, "textures/runden.png");
		rundenLeft = new ButtonErstellen(330, 195, "textures/arrow_left.png");
		rundenValue1 = new texHelper(430, 195, "textures/number.png");
		rundenValue10 = new texHelper(380, 195, "textures/number.png");
		rundenRight = new ButtonErstellen(480, 195, "textures/arrow_right.png");

		// 3. Zeile

		zeit = new ButtonErstellen(110, 105, "textures/zeit.png");
		zeitLeft = new ButtonErstellen(330, 105, "textures/arrow_left.png");
		zeitRight = new ButtonErstellen(480, 105, "textures/arrow_right.png");

		// 4. Zeile

		zuruck = new ButtonErstellen(110, 15, "textures/zuruck.png");
		start = new ButtonErstellen(330, 15, "textures/start.png");
	}
	
	private class texHelper {
		
		/**
		 *  @author pbg2h15ala
		 *  entnommen aus Buttonerstellen
		 */

		private Texture texture;
		private float x;
		private float y;

		private texHelper(float x, float y, String pfad) {
			texture = new Texture(pfad);
			this.x = x;
			this.y = y;

		}
		public void render(SpriteBatch batch) {
			batch.draw(texture, x, y);

		}		
		public void setTexture(String pfad){
			texture = new Texture(pfad);
		}
	}
	
	private void setNumberTo(int number, texHelper value10, texHelper value1){
		/**
		 *  @author pbg2h15ala
		 */
		number%=100;
		value10.setTexture("textures/number_" + number/10+".png");
		value1.setTexture("textures/number_" + number%10+".png");
	}
	

}
