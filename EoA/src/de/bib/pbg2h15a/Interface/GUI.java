package de.bib.pbg2h15a.Interface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import de.bib.pbg2h15a.GameComp.Player;
import de.bib.pbg2h15a.GameState.GameState;
import de.bib.pbg2h15a.GameState.GameStateManager;
import de.bib.pbg2h15a.GameState.LocalGameState;
import de.bib.pbg2h15a.Uitl.Timer;

	/**GUI dient der Erzeugung einer Nutzeroberflaeche im LocalGame. 
	* 
	* @author pbd2h15aho
	* Kommentiert von pbd2h15aho Franziska Hoffmann
	**/
public class GUI{

	/**Prozentuale Angabe der Breite*/
	private static final float SCALE_X=Gdx.graphics.getWidth()/100;
	
	/**Prozentuale Angabe der Hoehe*/
	private static final float SCALE_Y=Gdx.graphics.getHeight()/100;
	
	/**Array an Dateipfaden der Texturen*/
	private static final String[] PFADE={"img/Buttons/GUI/Timer_BGLabel.png"				//0
									   	,"img/Buttons/GUI/SpielerDead_BGLabel.png"
									   	,"img/Buttons/GUI/SpielerDead_BGLabel2.png"
									   	,"img/Buttons/GUI/Spieler1_BGLabel.png"
									   	,"img/Buttons/GUI/Spieler2_BGLabel.png"
									   	,"img/Buttons/GUI/Spieler3_BGLabel.png"				//5
									   	,"img/Buttons/GUI/Spieler4_BGLabel.png"
									   	,"img/Buttons/GUI/ZurueckIconButton.png"
									   	,"img/Buttons/GUI/PauseIconButton.png"
									   	,"img/Buttons/GUI/ZurueckIconButtonPressed.png"
									   	,"img/Buttons/GUI/PauseIconButtonPressed.png"		//10
									   	,"img/Buttons/GUI/BombeButton.png"
									   	,"img/Buttons/GUI/UpButton.png"
									   	,"img/Buttons/GUI/DownButton.png"
									   	,"img/Buttons/GUI/RightButton.png"
									   	,"img/Buttons/GUI/LeftButton.png"					//15
									   	,"img/Buttons/GUI/BombeButtonPressed.png"
									   	,"img/Buttons/GUI/UpButtonPressed.png"
									   	,"img/Buttons/GUI/DownButtonPressed.png"
									   	,"img/Buttons/GUI/RightButtonPressed.png"
									   	,"img/Buttons/GUI/LeftButtonPressed.png"			//20
										};
	
	private Label lbTimer;
	private Image imTimer;
	private Image[] imgsPlayers;
	
	private TextureRegionDrawable tdDP;
	private TextureRegionDrawable tdDP2;
	
	private ImageButton iBtnBack;
	private ImageButton iBtnPause;
	private ImageButton iBtnBomb;
	private ImageButton iBtnUp;
	private ImageButton iBtnDown;
	private ImageButton iBtnRight;
	private ImageButton iBtnLeft;
	
	private Table tblMenueBtns;
	private Table tblLabels;
	private Table tblSteuerungBnt;
	
	private BitmapFont bitfont;
	
	private Stage stage;
	private Timer time;
	private GameStateManager gsm;
	private GameState gs;
	
	private Player[] players;
	private boolean[] alife={true,true,true,true};
	
	/** Konstruktor fuer ein GUI Objekt.
	 * 
	 * @author pbd2h15aho
	 * @param time Timer der die Spielzeit anzeigt
	 * @param p1,p2,p3,p4 Referenz auf den Spieler
	 * @param gameStateManager Referenz auf den GameStateManager
	 * @param gameState Referenz auf den GameState
	 */	
	public GUI(Timer time, Player p1, Player p2, Player p3, Player p4,GameStateManager gameStateManager, GameState gameState) {
		super();
		this.gsm=gameStateManager;
		this.gs=gameState;
		this.time = time;
		players= new Player[4];
		players[0]=p1;
		players[1]=p2;
		players[2]=p3;
		players[3]=p4;
		
		tdDP=new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[1])));
		tdDP2=new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[2])));
		
		bitfont = new BitmapFont();
		bitfont.setColor(Color.WHITE);
		
		create();
		
		stage= new Stage();
		Gdx.input.setInputProcessor(stage);
		for(int i=0; i<imgsPlayers.length;i++){
			stage.addActor(imgsPlayers[i]);
		}
		stage.addActor(imTimer);
		stage.addActor(lbTimer);
		stage.addActor(tblLabels);
		stage.addActor(tblMenueBtns);
		//stage.addActor(tblSteuerungBnt);
		//stage.addActor(iBtnBomb);
	}
	
	
	/** render rendert die einzelnen Texturen in der GUI
	 * 
	 * @author pbd2h15aho
	 */	
	public void render(SpriteBatch sb){
		bitfont.draw(sb,"Puffer", 0, 0);
		lbTimer.setText(""+(int)time.getTime());
		showIsAlife();
		stage.draw();	
	}
	
	/** showIsAlife aendert die Texture der Spieleranzeige, wenn der Spieler stirbt.
	 * 
	 * @author pbd2h15aho
	 */	
	private void showIsAlife(){
		for(int i=0; i<imgsPlayers.length;i++){
			if(players[i].getLife()==0&&alife[i]){
				if(i<2){
					imgsPlayers[i].setDrawable(tdDP);
				}else{
					imgsPlayers[i].setDrawable(tdDP2);
				}
				alife[i]=false;
			}
		}
	}

	
	/**createMenuButtons erzeugt die Buttons des Menues.
	 * 
	 * @author pbd2h15aho
	 */	
	private void createMenuButtons(){
		iBtnBack= new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[7]))),new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[9]))));
		iBtnPause=new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[8]))),new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[10]))));
		
		tblMenueBtns= new Table();
		tblMenueBtns.add(iBtnBack);
		tblMenueBtns.add().size(2*SCALE_X,0);
		
		if(gs instanceof LocalGameState){
			tblMenueBtns.add(iBtnPause);
		}
		tblMenueBtns.setFillParent(true);
		tblMenueBtns.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		tblMenueBtns.setPosition(Gdx.graphics.getWidth()/2-SCALE_X*8 , Gdx.graphics.getHeight()/2-5.3f*SCALE_Y);
	}
	
	
	/**createLabels erzeugt die Labels.
	 * 
	 * @author pbd2h15aho
	 */	
	private void createLabels(){
		Label.LabelStyle lbStyle=new Label.LabelStyle();
		lbStyle.font=bitfont;
		lbStyle.fontColor=Color.WHITE;
		String text=""+(int)time.getTime();
		
		lbTimer=new Label(text,lbStyle);
		lbTimer.setPosition(Gdx.graphics.getWidth()/2-text.length()*3, Gdx.graphics.getHeight()-6*SCALE_Y);
		
		Label lbP1= new Label(players[0].getName(),lbStyle);
		Label lbP2= new Label(players[1].getName(),lbStyle);
		Label lbP3= new Label(players[2].getName(),lbStyle);
		Label lbP4= new Label(players[3].getName(),lbStyle);
		
		tblLabels= new Table();
		tblLabels.add(lbP1).size(15*SCALE_X);
		tblLabels.add().size(5*SCALE_X);
		tblLabels.add(lbP2).size(15*SCALE_X);
		tblLabels.add().size(12.5f*SCALE_X);
		tblLabels.add(lbP3).size(15*SCALE_X);
		tblLabels.add().size(5*SCALE_X);
		tblLabels.add(lbP4).size(10*SCALE_X);
		tblLabels.setFillParent(true);
		tblLabels.setPosition(0,Gdx.graphics.getHeight()/2-13.6f*SCALE_Y);
	}
	
	
	/**createControlButtons erzeugt die Buttons zur Steruerung f�r eine eventuelle Mobileversion.
	 * 
	 * @author pbd2h15aho
	 */	
	private void createControlButtons(){
			
		iBtnBomb= 	new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[11]))),new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[16]))));
		iBtnUp= 	new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[12]))),new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[17]))));
		iBtnDown= 	new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[13]))),new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[18]))));
		iBtnRight= 	new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[14]))),new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[19]))));
		iBtnLeft= 	new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[15]))),new TextureRegionDrawable(new TextureRegion(new Texture(PFADE[20]))));
				
		iBtnBomb.setPosition(Gdx.graphics.getWidth()-12.5f*SCALE_X,10*SCALE_Y);
				
		tblSteuerungBnt= new Table();
		tblSteuerungBnt.add();
		tblSteuerungBnt.add(iBtnUp);
		tblSteuerungBnt.add();
		tblSteuerungBnt.row();
		tblSteuerungBnt.add(iBtnLeft);
		tblSteuerungBnt.add();
		tblSteuerungBnt.add(iBtnRight);
		tblSteuerungBnt.row();
		tblSteuerungBnt.add();
		tblSteuerungBnt.add(iBtnDown);
		tblSteuerungBnt.add();
			
		tblSteuerungBnt.setPosition(8.8f*SCALE_X,20*SCALE_Y);
	}
	
	
	/**createImages erzeugt die Texturen der Spieleranzeige .
	 * 
	 * @author pbd2h15aho
	 */	
	private void createImages() {
		
		imTimer= new Image(new Texture(PFADE[0]));
		imTimer.setPosition(Gdx.graphics.getWidth()/2-imTimer.getWidth()/2-0.3f*SCALE_X,
							Gdx.graphics.getHeight()-imTimer.getHeight()-0.93f*SCALE_Y);
		
		imgsPlayers= new Image[4];
		imgsPlayers[0] = new Image(new Texture(PFADE[3]));
		imgsPlayers[1] = new Image(new Texture(PFADE[4]));
		imgsPlayers[2] = new Image(new Texture(PFADE[5]));
		imgsPlayers[3] = new Image(new Texture(PFADE[6]));
		
		imgsPlayers[0].setPosition(15*SCALE_X-imgsPlayers[0].getWidth()/2, 
								   Gdx.graphics.getHeight()-imgsPlayers[0].getHeight()-10.6f*SCALE_Y);
		imgsPlayers[1].setPosition(35*SCALE_X-imgsPlayers[1].getWidth()/2, 
								   Gdx.graphics.getHeight()-imgsPlayers[1].getHeight()-10.6f*SCALE_Y);
		imgsPlayers[2].setPosition(Gdx.graphics.getWidth()-35*SCALE_X-imgsPlayers[2].getWidth()/2,
								   Gdx.graphics.getHeight()-imgsPlayers[2].getHeight()-10.6f*SCALE_Y);
		imgsPlayers[3].setPosition(Gdx.graphics.getWidth()-15*SCALE_X-imgsPlayers[3].getWidth()/2, 
								   Gdx.graphics.getHeight()-imgsPlayers[3].getHeight()-10.6f*SCALE_Y);
	}
	
	
	/**createListener erzeugt die Listener f�r den Zurueck- und den Pausebuttons.
	 * 
	 * @author pbd2h15aho
	 * The private method createListeners initializes the Buttons Listeners and connects them to their functionalities
	 */	
	private void createListeners(){ 
		
			iBtnBack.addListener(new InputListener() {
				@Override
				public boolean handle(Event event) {
					if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
						gsm.setState(0);
						return true;
					}
					return false;
				}
			});
			/***
			 * @author pbg2h15afo
			 */
			iBtnPause.addListener(new InputListener() {	
				@Override
				public boolean handle(Event event) {
					if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
					((LocalGameState) gs).tooglePause();
						return true;
					}
					return false;}
			});
	}
	
	/**create erzeugt alle Elemente.
	 * 
	 * @author pbd2h15ary
	 */	
	public void create(){
        createImages();
        createLabels();
        createMenuButtons();
        createListeners();
	}


	public ImageButton getiBtnBack() {
		return iBtnBack;
	}


	public ImageButton getiBtnPause() {
		return iBtnPause;
	}


	public ImageButton getiBtnBomb() {
		return iBtnBomb;
	}


	public ImageButton getiBtnUp() {
		return iBtnUp;
	}


	public ImageButton getiBtnDown() {
		return iBtnDown;
	}


	public ImageButton getiBtnRight() {
		return iBtnRight;
	}


	public ImageButton getiBtnLeft() {
		return iBtnLeft;
	}
	

}
