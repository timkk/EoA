package de.bib.pbg2h15a;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author pbg2h15asu
 * @author pbg2h15aza: timer & gamestate
 * @author pbg2h15awi
 */

public class LocalGameState extends GameState{

	private BitmapFont font;
	private BitmapFont font_countdown;
	
	private SpriteBatch batch;
	
	private Texture texture_player;
	
	private Player[] player;
	
	protected final InputConfig[] input =
		  {new InputConfig(Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S, Input.Keys.Q),
			new InputConfig(Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP, Input.Keys.DOWN, Input.Keys.PAGE_DOWN),
			new InputConfig(Input.Keys.J, Input.Keys.L, Input.Keys.I, Input.Keys.K, Input.Keys.SPACE),
			new InputConfig(Input.Keys.NUMPAD_1, Input.Keys.NUMPAD_3, Input.Keys.NUMPAD_5, Input.Keys.NUMPAD_2, Input.Keys.NUMPAD_0)};;
	
	private final Point FIELD_START = new Point(125f, 50f);
	private final Point FIELD_END = new Point(875f, 600f);
	private final int SPRITESIZE = 50;
	
	protected final Point[] player_spawns =
		{new Point(FIELD_START.getX(), FIELD_END.getY()-SPRITESIZE), 
		new Point(FIELD_END.getX()-SPRITESIZE,FIELD_START.getY()),
		new Point(FIELD_START.getX(), FIELD_START.getY()),
		new Point(FIELD_END.getX()-SPRITESIZE,FIELD_END.getY()-SPRITESIZE)};
	
	private final float COLLISION_OFFSET = 1f;
	
	private Stage stage;
	private List<GameObject> collision_objects;
	private List<GameObject> collision_explosion;
	private List<Bomb> bombs;
	
	private Timer timer = new Timer(6);
	
	private GUI gui;
	private Timer rundenTimer;
	
	protected LocalGameState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
    	batch = new SpriteBatch();
    	
    	collision_objects = new LinkedList<GameObject>();
    	collision_explosion = new LinkedList<GameObject>();
    	bombs = new LinkedList<Bomb>();

    	font = new BitmapFont();
    	font.setColor(Color.BLACK);
    	font_countdown = new BitmapFont();
    	font_countdown.setColor(Color.FIREBRICK);
    	font_countdown.getData().setScale(2);

    	Object[][] field = setupField(17, 13);
    	stage = new Stage((GameObject[][]) field, 300, StageType.STANDARD, player_spawns, 3, Mode.LAST_MAN_STANDING);
		
    	texture_player = new Texture("img/Stage_1/Windfalle.png");
    	
    	player = new Player[4];
    	for(int i = 0;i < 4;i++){
    		player[i] = new Player("Player " + i, player_spawns[i], texture_player, input[i], stage);
    	}
    	
    	rundenTimer = new Timer(300);

	}

	@Override
	public void update(float dt) {
		
		if(timer.isFinished()){
			rundenTimer.update(dt);
			
	    	//spieler input
	    	for(int i=0;i<4;i++){
	    		
		    	InputConfig playerinput = player[i].getControls();
		    	
				Point pos = player[i].getPos();
		    	
		    	//bewegung auf x
		    	
		    	if(Gdx.input.isKeyPressed(playerinput.getKeyLeft())){
		    		
		    		Point tmp = new Point(player[i].getPos());
		    		tmp.translate(-player[i].getMoveSpeed(), 0);
			    	if(!collision(tmp, collision_objects))
			    		player[i].setPos(tmp);
		    		
		    	}
		    	if(Gdx.input.isKeyPressed(playerinput.getKeyRight())){

		    		Point tmp = new Point(player[i].getPos());
		    		tmp.translate(player[i].getMoveSpeed(), 0);
			    	if(!collision(tmp, collision_objects))
			    		player[i].setPos(tmp);
		    	}
		    	
		    	pos.setX(player[i].getPos().getX());
		    	
		    	//bewegung auf y

		    	if(Gdx.input.isKeyPressed(playerinput.getKeyUp())){

		    		Point tmp = new Point(player[i].getPos());
		    		tmp.translate(0, player[i].getMoveSpeed());
			    	if(!collision(tmp, collision_objects))
			    		player[i].setPos(tmp);
		    	}
		    	if(Gdx.input.isKeyPressed(playerinput.getKeyDown())){

		    		Point tmp = new Point(player[i].getPos());
		    		tmp.translate(0, -player[i].getMoveSpeed());
			    	if(!collision(tmp, collision_objects))
			    		player[i].setPos(tmp);
		    	}
		    	
		    	//bombe legen
		    	
		    	if(Gdx.input.isKeyPressed(playerinput.getKeyBomb()) && player[i].getAnzahlBomben() < player[i].getAnzahlBombenMax()){
		    		bombs.add(player[i].dropBomb());
		    	}
	    	}
	    	
	    	for(Bomb b : bombs){
	    		b.update(dt);
	    	}
	    	
		}else{
			timer.update(dt);
		}
	}

	@Override
	public void render() {
    	Gdx.gl.glClearColor(1, 1, 1, 1);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    	batch.begin();
    	drawField(stage);
    	for(Bomb b : bombs){
    		b.render(batch);
    	}
    	for(Player p : player){
    		p.render(batch);
    	}
    	if(!timer.isFinished()){
    		int time = (int)timer.getTime();
    		font_countdown.draw(batch, ""+time, Gdx.graphics.getWidth()/2 - 8, Gdx.graphics.getHeight()/2-20);
    	}
    	batch.end();
	}

	@Override
	public void dispose() {
    	batch.dispose();
    	texture_player.dispose();
    	
	}

	private Object[][] setupField(int width, int height){
    	
    	GameObject[][] newField = new GameObject[height][width];
    	
    	for(int i=0;i<height;i++){
    		for(int j=0;j<width;j++){
    			
    			int posx = 75 + SPRITESIZE * j;
    			int posy = 0 + SPRITESIZE * i;
    			
    			if(i == 0 || j == 0 || i == height-1 || j == width-1){
    				Pillar pillar = new Pillar(new Point(posx, posy), false);
    				newField[i][j] = (GameObject)pillar;
    				collision_objects.add(pillar);
				}else if(((i % 2) == 0 && (j % 2) == 0)){
    				Pillar pillar = new Pillar(new Point(posx, posy), true);
    				newField[i][j] = (GameObject)pillar;
    				collision_objects.add(pillar);
				}else{
					Background background = new Background(new Point(posx, posy));
					newField[i][j] = (GameObject)background;
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

	private boolean collision(Player p, List<GameObject> objects) {
		
		boolean collision = false;
    	CollisionDetector cd = new CollisionDetector((GameObject) p, COLLISION_OFFSET);
    	
    	for(GameObject o : objects){
    		if(cd.collidesWith(o))
    			collision = true;
    	}
    	
    	return collision;
    }

	private boolean collision(Point p, List<GameObject> objects) {
		
		boolean collision = false;
    	CollisionDetector cd = new CollisionDetector(p, 50f, 50f, COLLISION_OFFSET);
    	
    	for(GameObject o : objects){
    		if(cd.collidesWith(o))
    			collision = true;
    	}
    	
    	return collision;
    }
}
