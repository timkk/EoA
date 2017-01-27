package de.bib.pbg2h15a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
	
	
	private static int mapNr=1;
	private static int rundenZeitMin=3;
	private static int rundenZeitSek=30;
	private static int rundenAnzahl = 3;

	// img/GamePrepare
	ButtonErstellen name;
	private TextField txfName;	
	
	ButtonErstellen changeStage;
	ButtonErstellen changeStageLeft;
	ButtonErstellen changeStageRight;
	texHelper changeStageValue1;
	texHelper changeStageValue10;

	ButtonErstellen runden;
	ButtonErstellen rundenLeft;
	ButtonErstellen rundenRight;
	texHelper rundenValue1;
	texHelper rundenValue10;

	ButtonErstellen zeit;
	ButtonErstellen zeitLeft;
	ButtonErstellen zeitRight;
	texHelper zeitSecValue1;
	texHelper zeitSecValue10;
	ButtonErstellen zeitMinValue;
	ButtonErstellen doublePoint;
	//BitmapFont zeitValue;

		

	ButtonErstellen zuruck;
	ButtonErstellen start;
	
	/**
	 * 
	 * @author pbg2h15agu, pbg2h15afa
	 */

	ButtonErstellen playerLeft;
	ButtonErstellen playerRight;
	texHelper playerValue1;
	texHelper playerValue10;
	private static int playerCount = 1;
	
	/**
	 * 
	 */

	private SpriteBatch batch;

	protected LocalGamePrepareState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		batch = new SpriteBatch();    	
    	buttonSet();
    	
    	/*zeitValue = new BitmapFont();
    	zeitValue.setColor(Color.BLACK);*/
    	
    	stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		

		// Namenseingabefeld
		/**
		 * @author pbg2h15ago, pbg2h15ani
		 */
		/*
		skinName = new Skin(Gdx.files.internal("img/GamePrepare/uiskin.json"));
		
		txfName = new TextField("", skinName);
		txfName.setPosition(330, 375);
		txfName.setSize(200, 50);
		stage.addActor(txfName);*/
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
		
		/**
		 * @author pbg2h15agu, pbg2h15afa
		 */
		if(start.isClicked()){
			gsm.setState(GameStateManager.NAME_STATE, playerCount);
		}
		/**
		 * 
		 */
		
		/**
		 *  @author pbg2h15ala, pbg2h15ago
		 */
		
		// Stage einstellen
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		if (changeStageLeft.isClicked() && mapNr > 1) {
			mapNr--;
			
		}
		if (changeStageRight.isClicked() && mapNr < 3) {
			mapNr++;
			
		}
		setNumberTo(mapNr, changeStageValue10, changeStageValue1);
		/**
		 * @author pbg2h15ago
		 */
		
		/* Zeit einstellen
		
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
			
		}*/
		/**
		 * @author pbg2h15agu, pbg2h15afa
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
		setNumberTo(rundenZeitSek, zeitSecValue10, zeitSecValue1);
		zeitMinValue = new ButtonErstellen((Gdx.graphics.getWidth() / 2) + 80, Gdx.graphics.getHeight()-505, "img/GamePrepare/number_"+rundenZeitMin+".png");
		/**
		 * 
		 */
		
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
		 * @author pbg2h15agu, pbg2h15afa
		 */
		//SpielerAnzahl
		if(playerRight.isClicked() && playerCount < 4){
        	playerCount++;
		}
      
		if(playerLeft.isClicked() && playerCount > 1){
	    	playerCount--;
		}
	   
		setNumberTo(playerCount, playerValue10, playerValue1);
		/*
		 * 
		 */
			
		/**
		 *  @author pbg2h15ala
		 */
		
		name.render(batch);
		/**
		 *  @author pbg2h15agu, pbg2h15afa
		 */
		playerLeft.render(batch);
		playerRight.render(batch);
		playerValue1.render(batch);
		playerValue10.render(batch);
		/**
		 * 
		 */
		
		changeStage.render(batch);
		changeStageLeft.render(batch);
		changeStageRight.render(batch);
		changeStageValue1.render(batch);
		changeStageValue10.render(batch);
		
		runden.render(batch);
		rundenLeft.render(batch);
		rundenValue1.render(batch);
		rundenValue10.render(batch);
		rundenRight.render(batch);
		
		zeit.render(batch);
		zeitLeft.render(batch);
		zeitMinValue.render(batch);
		doublePoint.render(batch);
		zeitSecValue10.render(batch);
		zeitSecValue1.render(batch);
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

		name = new ButtonErstellen((Gdx.graphics.getWidth() / 2) - 190, Gdx.graphics.getHeight()-235, "img/GamePrepare/Spieleranzahl.png");
		
		/**
		 *  @author pbg2h15agu, pbg2h15afa
		 */
		playerLeft = new ButtonErstellen((Gdx.graphics.getWidth() / 2) + 30, Gdx.graphics.getHeight()-235, "img/GamePrepare/arrow_left.png");
		playerValue1 = new texHelper((Gdx.graphics.getWidth() / 2) + 130, Gdx.graphics.getHeight()-235, "img/GamePrepare/number.png");
		playerValue10 = new texHelper((Gdx.graphics.getWidth() / 2) + 80, Gdx.graphics.getHeight()-235, "img/GamePrepare/number.png");
		playerRight = new ButtonErstellen((Gdx.graphics.getWidth() / 2) + 180, Gdx.graphics.getHeight()-235, "img/GamePrepare/arrow_right.png");
		/**
		 * 
		 */


		// 1. Zeile

		changeStage = new ButtonErstellen((Gdx.graphics.getWidth() / 2) - 190, Gdx.graphics.getHeight()-325, "img/GamePrepare/stage.png");
		changeStageLeft = new ButtonErstellen((Gdx.graphics.getWidth() / 2) + 30, Gdx.graphics.getHeight()-325, "img/GamePrepare/arrow_left.png");
		changeStageValue1 = new texHelper((Gdx.graphics.getWidth() / 2) + 130, Gdx.graphics.getHeight()-325, "img/GamePrepare/number.png");
		changeStageValue10 = new texHelper((Gdx.graphics.getWidth() / 2) + 80, Gdx.graphics.getHeight()-325, "img/GamePrepare/number.png");
		changeStageRight = new ButtonErstellen((Gdx.graphics.getWidth() / 2) + 180, Gdx.graphics.getHeight()-325, "img/GamePrepare/arrow_right.png");

		// 2. Zeile

		runden = new ButtonErstellen((Gdx.graphics.getWidth() / 2) - 190, Gdx.graphics.getHeight()-415, "img/GamePrepare/runden.png");
		rundenLeft = new ButtonErstellen((Gdx.graphics.getWidth() / 2) + 30, Gdx.graphics.getHeight()-415, "img/GamePrepare/arrow_left.png");
		rundenValue1 = new texHelper((Gdx.graphics.getWidth() / 2) + 130, Gdx.graphics.getHeight()-415, "img/GamePrepare/number.png");
		rundenValue10 = new texHelper((Gdx.graphics.getWidth() / 2) + 80, Gdx.graphics.getHeight()-415, "img/GamePrepare/number.png");
		rundenRight = new ButtonErstellen((Gdx.graphics.getWidth() / 2) + 180, Gdx.graphics.getHeight()-415, "img/GamePrepare/arrow_right.png");

		// 3. Zeile

		zeit = new ButtonErstellen((Gdx.graphics.getWidth() / 2) - 190, Gdx.graphics.getHeight()-505, "img/GamePrepare/zeit.png");
		zeitLeft = new ButtonErstellen((Gdx.graphics.getWidth() / 2) + 30, Gdx.graphics.getHeight()-505, "img/GamePrepare/arrow_left.png");
		zeitMinValue = new ButtonErstellen((Gdx.graphics.getWidth() / 2) + 80, Gdx.graphics.getHeight()-505, "img/GamePrepare/number_"+rundenZeitMin+".png");
		doublePoint = new ButtonErstellen((Gdx.graphics.getWidth() / 2) + 130, Gdx.graphics.getHeight()-505, "img/GamePrepare/number_doppel.png");
		zeitSecValue1 = new texHelper((Gdx.graphics.getWidth() / 2) + 230, Gdx.graphics.getHeight()-505, "img/GamePrepare/number.png");
		zeitSecValue10 = new texHelper((Gdx.graphics.getWidth() / 2) + 180, Gdx.graphics.getHeight()-505, "img/GamePrepare/number.png");
		zeitRight = new ButtonErstellen((Gdx.graphics.getWidth() / 2) + 280, Gdx.graphics.getHeight()-505, "img/GamePrepare/arrow_right.png");

		// 4. Zeile

		zuruck = new ButtonErstellen((Gdx.graphics.getWidth() / 2) - 190, Gdx.graphics.getHeight()-595, "img/GamePrepare/zuruck.png");
		start = new ButtonErstellen((Gdx.graphics.getWidth() / 2) + 30, Gdx.graphics.getHeight()-595, "img/GamePrepare/weiter.png");
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
		value10.setTexture("img/GamePrepare/number_" + number/10+".png");
		value1.setTexture("img/GamePrepare/number_" + number%10+".png");
	}
	
	public static int getWinRounds(){
		return rundenAnzahl;
	}
	
	public static int getTimeMin(){
		return rundenZeitMin;
	}
	
	public static int getTimeSec(){
		return rundenZeitSek;
	}
	
	public static int getMap(){
		return mapNr;
	}

}