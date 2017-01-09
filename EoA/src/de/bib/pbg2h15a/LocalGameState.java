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
	
	private Stage stage;
	private List<Object> collision_objects;
	private List<Object> collision_explosion;
	
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
    	
    	collision_objects = new LinkedList<Object>();
    	collision_explosion = new LinkedList<Object>();

    	font = new BitmapFont();
    	font.setColor(Color.BLACK);
    	font_countdown = new BitmapFont();
    	font_countdown.setColor(Color.FIREBRICK);
    	font_countdown.getData().setScale(2);
    	
    	texture_player = new Texture(Gdx.files.internal("img/Stage_1/WindFalle.png"));//to do
    	sprite_player = new Sprite(texture_player);
    	sprite_player.setPosition(FIELD_START.getX(), FIELD_START.getY());
    	
    	Object[][] field = setupField(17, 13);
    	stage = new Stage((GameObject[][]) field, 300, StageType.STANDARD, player_spawns, 3, Mode.LAST_MAN_STANDING);
		
    	player = new Player[4];
    	for(int i = 0;i < 4;i++){
    		player[i] = new Player("Player " + i, null, texture_player, stage);
    	}
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
    	drawField(stage);
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
    	
	}

	private Object[][] setupField(int width, int height){
    	
    	Object[][] newField = new GameObject[height][width];
    	
    	for(int i=0;i<height;i++){
    		for(int j=0;j<width;j++){
    			
    			int posx = 75 + SPRITESIZE * j;
    			int posy = 0 + SPRITESIZE * i;
    			
    			if(i == 0 || j == 0 || i == height-1 || j == width-1){
    				Pillar pillar = new Pillar(new Point(posx, posy));
    				newField[i][j] = pillar;
    				collision_objects.add(pillar);
				}else if(((i % 2) == 0 && (j % 2) == 0)){
					Wall wall = new Wall(new Point(posx, posy));
    				newField[i][j] = wall;
    				collision_objects.add(wall);
				}else{
					Background background = new Background(new Point(posx, posy));
					newField[i][j] = background;
				}
    		}
    	}
    	
    	return newField;
    }

    private void drawField(Stage s) {
    	for(int i=0;i<13;i++){
    		for(int j=0;j<17;j++){
    			GameObject g = s.getFields()[i][j];
    			g.render(batch);
    		}
    	}
	}

	private boolean collision(Sprite s, List<Object> objects, float os) {
		
		boolean collision = false;
    	CollisionDetector cd = new CollisionDetector(sprite_player, COLLISION_OFFSET);
    	
    	for(Object o : objects){
    		if(cd.collidesWith((GameObject) o))
    			collision = true;
    	}
    	
    	return collision;
	}
}
