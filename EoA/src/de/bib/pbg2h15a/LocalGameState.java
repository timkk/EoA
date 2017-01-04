package de.bib.pbg2h15a;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author pbg2h15asu
 * @author pbg2h15aza: timer & gamestate
 * @author pbg2h15awi
 */

public class LocalGameState extends GameState{

	private float speed = 2.5f;

	private BitmapFont font;
	private BitmapFont font_countdown;
	
	private SpriteBatch batch;
	
	private Texture texture_player;
	private Texture texture_background_center;
	
//	private Texture texture_background_left;
//	private Texture texture_background_right;
//	private Texture texture_background_top;
//	private Texture texture_background_bottom;
//	private Texture texture_background_leftright;
//	private Texture texture_background_topbottom;
//	private Texture texture_background_topleft;
//	private Texture texture_background_topright;
//	private Texture texture_background_bottomleft;
//	private Texture texture_background_bottomright;
	private Texture texture_pillar;
	private Texture texture_pillarOutside;
	private Texture texture_wall;
	private Texture texture_bomb;
	private Texture texture_firetrap;
	private Texture texture_windtrap;
	
	private Sprite sprite_player;
	
	private Player[] player;
	
	//custom class Point(float,float)
	private final Point FIELD_START = new Point(125f, 50f);
	private final Point FIELD_END = new Point(825f, 600f);
	private final int SPRITESIZE = 50;
	
	private final Point[] player_spawns = 
		{new Point(FIELD_START.getX(), FIELD_END.getY()-SPRITESIZE), 
		new Point(FIELD_END.getX()-SPRITESIZE,FIELD_START.getY()),
		new Point(FIELD_START.getX(), FIELD_START.getX()),
		new Point(FIELD_END.getX()-SPRITESIZE,FIELD_END.getY()-SPRITESIZE)};
	private final float COLLISION_OFFSET = 1f;
	
	private Sprite[][] field;
	private List<Sprite> collision_objects;
	
	private Timer timer = new Timer(6);
	
	private GUI gui;
	private Timer rundenTimer;
	
	protected LocalGameState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
    	batch = new SpriteBatch();
    	
    	collision_objects = new LinkedList<Sprite>();

    	font = new BitmapFont();
    	font.setColor(Color.BLACK);
    	font_countdown = new BitmapFont();
    	font_countdown.setColor(Color.FIREBRICK);
    	font_countdown.getData().setScale(2);
    	
    	texture_player = new Texture(Gdx.files.internal("img/Stage_1/WindFalle.png"));//to do
    	sprite_player = new Sprite(texture_player);
    	sprite_player.setPosition(FIELD_START.getX(), FIELD_START.getY());

    	texture_background_center = new Texture(Gdx.files.internal("img/Stage_1/GrassZentrum.png"));
//    	texture_background_left = new Texture(Gdx.files.internal("img/Stage_1/GrassLinks.png"));
//    	texture_background_right = new Texture(Gdx.files.internal("img/Stage_1/GrassRechts.png"));
//    	texture_background_top = new Texture(Gdx.files.internal("img/Stage_1/GrassOben.png"));
//    	texture_background_bottom = new Texture(Gdx.files.internal("img/Stage_1/GrassUnten.png"));
//    	texture_background_leftright = new Texture(Gdx.files.internal("img/Stage_1/GrassRechtsLinks.png"));
//    	texture_background_topbottom = new Texture(Gdx.files.internal("img/Stage_1/GrassObenUnten.png"));
//    	texture_background_topleft = new Texture(Gdx.files.internal("img/Stage_1/GrassObenLinks.png"));
//    	texture_background_topright = new Texture(Gdx.files.internal("img/Stage_1/GrassObenRechts.png"));
//    	texture_background_bottomleft = new Texture(Gdx.files.internal("img/Stage_1/GrassUntenLinks.png"));
//    	texture_background_bottomright = new Texture(Gdx.files.internal("img/Stage_1/GrassUntenRechts.png"));
    	texture_pillar = new Texture(Gdx.files.internal("img/Stage_1/Saeule.png"));
    	texture_pillarOutside = new Texture(Gdx.files.internal("img/Stage_1/AussenWand.png"));
    	texture_wall = new Texture(Gdx.files.internal("img/Stage_1/Kiste.png"));
    	texture_bomb = new Texture(Gdx.files.internal("img/Stage_1/Bombe.png"));
    	texture_firetrap = new Texture(Gdx.files.internal("img/Stage_1/FeuerFalle.png"));
    	texture_windtrap = new Texture(Gdx.files.internal("img/Stage_1/WindFalle.png"));
    	
    	field = new Sprite[17][13];
    	field = setupField(17, 13);
		
    	player = new Player[4];
    	//insert players
    	
    	for(int i = 0;i<4;i++){
    		player[i].setPos(player_spawns[i]);
    	}
    	
    	rundenTimer = new Timer(300);
    	gui = new GUI(rundenTimer, player[0], player[1], player[2], player[3], gsm, this);
	}

	@Override
	public void update(float dt) {
		if(timer.isFinished()){
			rundenTimer.update(dt);
			
			float posx = sprite_player.getX();
	    	float posy = sprite_player.getY();
	    	
	    	if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
	    		sprite_player.translateX(-speed);
		    	
		    	if(sprite_player.getX() < FIELD_START.getX())
		    		sprite_player.setPosition(FIELD_START.getX(), sprite_player.getY());
		    	if(collision(sprite_player, collision_objects, COLLISION_OFFSET))
		    		sprite_player.setPosition(posx, posy);
	    	}
	    	
	    	if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
	    		sprite_player.translateX(speed);
		    	
		    	if(sprite_player.getX() > FIELD_END.getX())
		    		sprite_player.setPosition(FIELD_END.getX(), sprite_player.getY());
			    if(collision(sprite_player, collision_objects, COLLISION_OFFSET))
		    		sprite_player.setPosition(posx, posy);
	    	}
		    
		    posx = sprite_player.getX();
		    
	    	if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
	    		sprite_player.translateY(-speed);
	    		
		    	if(sprite_player.getY() < FIELD_START.getY())
		    		sprite_player.setPosition(sprite_player.getX(), FIELD_START.getY());
		    	if(collision(sprite_player, collision_objects, COLLISION_OFFSET))
		    		sprite_player.setPosition(posx, posy);
	    	}
	    	
	    	if(Gdx.input.isKeyPressed(Input.Keys.UP)){
		    	sprite_player.translateY(speed);
		    	
		    	if(sprite_player.getY() > FIELD_END.getY())
		    		sprite_player.setPosition(sprite_player.getX(), FIELD_END.getY());
			    if(collision(sprite_player, collision_objects, COLLISION_OFFSET))
		    		sprite_player.setPosition(posx, posy);
	    	}
		}else{
			timer.update(dt);
		}
	}

	@Override
	public void render() {
    	Gdx.gl.glClearColor(1, 1, 1, 1);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	String pos = "";
    
    	//for testing
    	pos = "x/y: " + sprite_player.getX() + " - " + (sprite_player.getX()+sprite_player.getWidth()) + " / " + sprite_player.getY() + " - " + (sprite_player.getY()+sprite_player.getHeight());
    	
    	batch.begin();
    	drawField(field, 17, 13);
    	font.draw(batch, pos, 80, 16);
    	sprite_player.draw(batch);
    	if(!timer.isFinished()){
    		int time = (int)timer.getTime();
    		font_countdown.draw(batch, ""+time, Gdx.graphics.getWidth()/2 - 8, Gdx.graphics.getHeight()/2-20);
    	}
    	gui.render(batch);
    	batch.end();
	}

	@Override
	public void dispose() {
    	batch.dispose();
    	texture_player.dispose();
//    	texture_background_bottom.dispose();
//    	texture_background_bottomleft.dispose();
//    	texture_background_bottomright.dispose();
    	texture_background_center.dispose();
//    	texture_background_left.dispose();
//    	texture_background_leftright.dispose();
//    	texture_background_right.dispose();
//    	texture_background_top.dispose();
//    	texture_background_topbottom.dispose();
//    	texture_background_topleft.dispose();
//    	texture_background_topright.dispose();
    	texture_pillar.dispose();
    	texture_pillarOutside.dispose();
    	texture_wall.dispose();
	}

	private Sprite[][] setupField(int width, int height){
    	
    	Sprite[][] newField = new Sprite[height][width];
    	
    	for(int i=0;i<height;i++){
    		for(int j=0;j<width;j++){
    			int posx = 75 + SPRITESIZE * j;
    			int posy = 0 + SPRITESIZE * i;
    			if(i == 0 || j == 0 || i == height-1 || j == width-1){
    				Sprite pillar = new Sprite(texture_pillarOutside);
    				pillar.setPosition(posx, posy);
    				newField[i][j] = pillar;
    				collision_objects.add(pillar);
				}else if(((i % 2) == 0 && (j % 2) == 0)){
    				Sprite pillar = new Sprite(texture_pillar);
    				pillar.setPosition(posx, posy);
    				newField[i][j] = pillar;
    				collision_objects.add(pillar);
//				}else if(i == 1 && j == 1){
//					Sprite s = new Sprite(texture_background_topleft);
//					s.setPosition(posx, posy);
//					newField[i][j] = s;
//				}else if(i == 1 && j == width-2){
//					Sprite s = new Sprite(texture_background_topright);
//					s.setPosition(posx, posy);
//					newField[i][j] = s;
//				}else if(i == height-2 && j == 1){
//					Sprite s = new Sprite(texture_background_bottomleft);
//					s.setPosition(posx, posy);
//					newField[i][j] = s;
//				}else if(i == height-2 && j == width-2){
//					Sprite s = new Sprite(texture_background_bottomright);
//					s.setPosition(posx, posy);
//					newField[i][j] = s;
//				}else if(i > 1 && i < height-2 && (i % 2) == 1 && j == 1){
//					Sprite s = new Sprite(texture_background_left);
//					s.setPosition(posx, posy);
//					newField[i][j] = s;
//				}else if(i > 1 && i < height-2 && (i % 2) == 1 && j == width-2){
//					Sprite s = new Sprite(texture_background_right);
//					s.setPosition(posx, posy);
//					newField[i][j] = s;
//				}else if(i > 1 && i < height-2 && (i % 2) == 0 && j > 1 && j < width-2 && (j % 2) == 1){
//					Sprite s = new Sprite(texture_background_leftright);
//					s.setPosition(posx, posy);
//					newField[i][j] = s;
//				}else if(i > 1 && i < height-1 && (i % 2) == 1 && j > 1 && j < width-1 && (j % 2) == 0){
//					Sprite s = new Sprite(texture_background_topbottom);
//					s.setPosition(posx, posy);
//					newField[i][j] = s;
//				}else if(i == 1 && j > 1 && j < width-2){
//					Sprite s = new Sprite(texture_background_top);
//					s.setPosition(posx, posy);
//					newField[i][j] = s;
//				}else if(i == height-2 && j > 1 && j < width-2){
//					Sprite s = new Sprite(texture_background_bottom);
//					s.setPosition(posx, posy);
//					newField[i][j] = s;
//				}else if(i > 1 && i < height-2 && j > 1 && j < width-2 && (i % 2) == 1 && (j % 2) == 1){
//					Sprite s = new Sprite(texture_background_center);
//					s.setPosition(posx, posy);
//					newField[i][j] = s;
				}else{
					Sprite s = new Sprite(texture_background_center);
					s.setPosition(posx, posy);
					newField[i][j] = s;
				}
    		}
    	}
    	
    	return newField;
    }

    private void drawField(Sprite[][] sprites, int width, int height) {
    	for(int i=0;i<height;i++){
    		for(int j=0;j<width;j++){
    			Sprite s = sprites[i][j];
    			s.draw(batch);
    		}
    	}
	}
    
    private boolean collision(Sprite s1, List<Sprite> s2, float offset){

    	boolean collision = false;
    	CollisionDetector cd = new CollisionDetector(sprite_player, (int) COLLISION_OFFSET);
    	
    	for(Sprite s : s2){
    		if(cd.collidesWith(s))
    			collision = true;
    	}
    	
    	return collision;
    }
}
