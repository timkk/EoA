package de.bib.pbg2h15a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * 
 * @author pbg2h15ala
 */

public class LocalGamePrepareState extends GameState {
	
	// Textures
		ButtonErstellen name;
		ButtonErstellen changeStage;
		
		ButtonErstellen runden;
		ButtonErstellen rundenLeft;
		ButtonErstellen rundenRight;
		ButtonErstellen rundenValue1;
		ButtonErstellen rundenValue10;
		
		ButtonErstellen zeit;
		ButtonErstellen zeitLeft;
		ButtonErstellen zeitRight;
		ButtonErstellen zeitValue1;
		ButtonErstellen zeitValue10;
		
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
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		
		 Gdx.gl.glClearColor(1, 1, 1, 1);
         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         batch.begin();
         
         name.render(batch);
         changeStage.render(batch);
         
         runden.render(batch);
     	 rundenLeft.render(batch);
     	 rundenValue1.render(batch);
     	 rundenValue10.render(batch);
     	 rundenRight.render(batch);
     	 
     	 zeit.render(batch);
    	 zeitLeft.render(batch);
    	 zeitValue1.render(batch);
    	 zeitValue10.render(batch);
    	 zeitRight.render(batch);
         
         start.render(batch);
         zuruck.render(batch);

         batch.end();	
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	private void buttonSet(){
	    
		//1. Zeile
    	
    	name = new ButtonErstellen(110, 350, "textures/name.png");
    	changeStage = new  ButtonErstellen(330, 350, "textures/stage.png");
    	
    	// 2. Zeile
    	
    	runden = new ButtonErstellen(110, 250, "textures/runden.png");
    	rundenLeft = new ButtonErstellen(330, 250, "textures/arrow_left.png");
    	rundenValue1 = new ButtonErstellen(380, 250, "textures/number.png");
    	rundenValue10 = new ButtonErstellen(430, 250, "textures/number.png");
    	rundenRight = new ButtonErstellen(480, 250, "textures/arrow_right.png");
    	
    	// 3. Zeile
    	
    	zeit = new ButtonErstellen(110, 150, "textures/zeit.png");
    	zeitLeft = new ButtonErstellen(330, 150, "textures/arrow_left.png");
    	zeitValue1 = new ButtonErstellen(380, 150, "textures/number.png");
    	zeitValue10 = new ButtonErstellen(430, 150, "textures/number.png");
    	zeitRight = new ButtonErstellen(480, 150, "textures/arrow_right.png");
    	
    	// 4. Zeile
    	
    	zuruck = new ButtonErstellen(110, 50, "textures/zuruck.png");
    	start = new ButtonErstellen(330, 50, "textures/start.png");
	}

}
