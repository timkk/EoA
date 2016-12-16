package de.bib.pbg2h15a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GUI{
	/**
	 * @author pbd2h15aho
	 */
	
	private Texture txTimer;
	private Texture txP1;
	private Texture txP2;
	private Texture txP3;
	private Texture txP4;
	private Texture txDP;
	
	//MenüButtons
	private Texture txBtnBack;
	private Texture txBtnPause;
	
	private TextureRegion trBtnBack;
	private TextureRegion trBtnPause;
	
	private TextureRegionDrawable tdBtnBack;
	private TextureRegionDrawable tdBtnPause;
	
	private ImageButton iBtnBack;
	private ImageButton iBtnPause;
	
	//SteuerungsButtons
	private Texture txBtnBomb;
	private Texture txBtnUp;
	private Texture txBtnDown;
	private Texture txBtnRight;
	private Texture txBtnLeft;
	
	private TextureRegion trBtnBomb;
	private TextureRegion trBtnUp;
	private TextureRegion trBtnDown;
	private TextureRegion trBtnRight;
	private TextureRegion trBtnLeft;
	
	
	private TextureRegionDrawable tdBtnBomb;
	private TextureRegionDrawable tdBtnUp;
	private TextureRegionDrawable tdBtnDown;
	private TextureRegionDrawable tdBtnRight;
	private TextureRegionDrawable tdBtnLeft;
	
	private ImageButton iBtnBomb;
	private ImageButton iBtnUp;
	private ImageButton iBtnDown;
	private ImageButton iBtnRight;
	private ImageButton iBtnLeft;
	
	//Tabels
	private Table tblMenueBtns;
	
	//Schrift
	private BitmapFont bitfont;
	
	//Objekte
	private Timer time;
	private Player p1; 
	private Player p2; 
	private Player p3;
	private Player p4;
	private static final int P_ANZEIGE_ABSTAND_TOP=80;
	
	
	public GUI(Timer time, Player p1, Player p2, Player p3, Player p4) {
		super();
		this.time = time;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
		
		txTimer= new Texture("img/Buttons/GUI/Timer_BGLabel.png");
		txP1= new Texture("img/Buttons/GUI/Spieler1_BGLabel.png");
		txP2= new Texture("img/Buttons/GUI/Spieler2_BGLabel.png");
		txP3= new Texture("img/Buttons/GUI/Spieler3_BGLabel.png");
		txP4= new Texture("img/Buttons/GUI/Spieler4_BGLabel.png");
		txDP= new Texture("img/Buttons/GUI/SpielerDead_BGLabel.png");
		
		//Menü Buttons
		txBtnBack =new Texture("img/Buttons/GUI/ZurueckIconButton.png");
		txBtnPause =new Texture("img/Buttons/GUI/PauseIconButton.png");
		
		trBtnBack= new TextureRegion(txBtnBack);
		trBtnPause= new TextureRegion(txBtnPause);
		
		tdBtnBack= new TextureRegionDrawable(trBtnBack);
		tdBtnPause= new TextureRegionDrawable(trBtnPause);
		
		iBtnBack= new ImageButton(tdBtnBack);
		iBtnPause=new ImageButton(tdBtnPause);
		
		
		//Steuerungs Buttons
		txBtnBomb =new Texture("img/Buttons/GUI/BombeButton.png");
		txBtnUp =new Texture("img/Buttons/GUI/UpButton.png");
		txBtnDown =new Texture("img/Buttons/GUI/DownButton.png");
		txBtnRight =new Texture("img/Buttons/GUI/RightButton.png");
		txBtnLeft =new Texture("img/Buttons/GUI/LeftButton.png");
		
		trBtnBomb= new TextureRegion(txBtnBomb);
		trBtnUp= new TextureRegion(txBtnUp);
		trBtnDown= new TextureRegion(txBtnDown);
		trBtnRight= new TextureRegion(txBtnRight);
		trBtnLeft= new TextureRegion(txBtnLeft);
		
		tdBtnBomb= new TextureRegionDrawable(trBtnBomb);
		tdBtnUp= new TextureRegionDrawable(trBtnUp);
		tdBtnDown= new TextureRegionDrawable(trBtnDown);
		tdBtnRight= new TextureRegionDrawable(trBtnRight);
		tdBtnLeft= new TextureRegionDrawable(trBtnLeft);
		
		iBtnBomb= new ImageButton(tdBtnBomb);
		iBtnUp= new ImageButton(tdBtnUp);
		iBtnDown= new ImageButton(tdBtnDown);
		iBtnRight= new ImageButton(tdBtnRight);
		iBtnLeft= new ImageButton(tdBtnLeft);
		
		//Schrift
		bitfont = new BitmapFont();
		bitfont.setColor(Color.WHITE);
		
		//Tabels
		tblMenueBtns= new Table();
		tblMenueBtns.add(iBtnBack);
		tblMenueBtns.add(iBtnPause);
		tblMenueBtns.setFillParent(true);
		tblMenueBtns.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		tblMenueBtns.setPosition(Gdx.graphics.getWidth()/2-80,Gdx.graphics.getHeight()/2-40);
		
		
		
		//Button Funktionalität
		//TODO zurück zum Menü
		iBtnBack.addListener(new EventListener() {	
			@Override
			public boolean handle(Event event) {
				if(iBtnBack.isPressed()){
					p1.setLife(1);
				}
				return false;
			}
		});
		
		//TODO PauseMenü
		iBtnPause.addListener(new EventListener() {	
			@Override
			public boolean handle(Event event) {
				if(iBtnPause.isPressed()){
					p1.setLife(0);
				}
				return false;
			}
		});
		
		//TODO Verknüpfen mit move up
		
		
		
		
		
		//TODO Verknüpfen mit move down
		
		
		
		//TODO Verknüpfen mit move left
		
		
		
		//TODO Verknüpfen mit move right
		
		
		
		
	}
	
	public void render(SpriteBatch sb){
		
		//PositionTimer
		sb.draw(txTimer,  Gdx.graphics.getWidth()/2-txTimer.getWidth()/2-3, Gdx.graphics.getHeight()-txTimer.getHeight()-7);
		String text=""+(int)time.getTime();
		bitfont.draw(sb, text,(Gdx.graphics.getWidth()/2)-text.length()*3, Gdx.graphics.getHeight()-30);
		
		//Position Spielerdaten
		//Player1
		isAlifeAnzeigen(p1, sb, txP1,150);
		bitfont.draw(sb, p1.getName(),115, Gdx.graphics.getHeight()-95);
		
		//Player2
		isAlifeAnzeigen(p2, sb, txP2,350);
		bitfont.draw(sb, p2.getName(),315, Gdx.graphics.getHeight()-95);
		
		//Player3
		isAlifeAnzeigen(p3, sb, txP3,Gdx.graphics.getWidth()-350);
		bitfont.draw(sb, p3.getName(),Gdx.graphics.getWidth()-415, Gdx.graphics.getHeight()-95);
		
		//Player4
		isAlifeAnzeigen(p4, sb, txP4,Gdx.graphics.getWidth()-150);
		bitfont.draw(sb, p4.getName(),Gdx.graphics.getWidth()-215, Gdx.graphics.getHeight()-95);
		
		
		//Position Buttons
		tblMenueBtns.draw(sb, 1);
		
		
	}
	
	
	private void isAlifeAnzeigen(Player p,SpriteBatch sb,Texture t,int x){
		//TODO evt mit einer Methode is alive verknüpfen
		if(p.getLife()>0){
			sb.draw(t, x-t.getWidth()/2, Gdx.graphics.getHeight()-t.getHeight()-P_ANZEIGE_ABSTAND_TOP);
		}else{
			sb.draw(txDP, x-txDP.getWidth()/2, Gdx.graphics.getHeight()-txDP.getHeight()-P_ANZEIGE_ABSTAND_TOP);
		}
	}
	

}
