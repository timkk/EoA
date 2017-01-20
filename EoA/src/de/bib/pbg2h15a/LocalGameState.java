package de.bib.pbg2h15a;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author pbg2h15asu
 * @author pbg2h15aza: timer & gamestate
 * @author pbg2h15awi
 */

public class LocalGameState extends GameState {

	private BitmapFont font;
	private BitmapFont font_countdown;

	private SpriteBatch batch;

	private String player1 = "Player 1";
	private String player2 = "Player 2";
	
	private List<Player> player;
	private List<KI> ai;

	protected final InputConfig[] input = {
			new InputConfig(Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S, Input.Keys.SPACE), // Q->Space
			new InputConfig(Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP, Input.Keys.DOWN, Input.Keys.NUMPAD_0), //PAGE_DOWN->NUMPAD_0
			new InputConfig(Input.Keys.J, Input.Keys.L, Input.Keys.I, Input.Keys.K, Input.Keys.SPACE),
			new InputConfig(Input.Keys.NUMPAD_1, Input.Keys.NUMPAD_3, Input.Keys.NUMPAD_5, Input.Keys.NUMPAD_2, Input.Keys.NUMPAD_0)
	};

	private final Point FIELD_START = new Point(125f, 50f);
	private final Point FIELD_END = new Point(875f, 600f);
	private final int SPRITESIZE = 50;

	protected final Point[] player_spawns = {
			new Point(FIELD_START.getX(), FIELD_END.getY() - SPRITESIZE),
			new Point(FIELD_END.getX() - SPRITESIZE, FIELD_START.getY()),
			new Point(FIELD_START.getX(), FIELD_START.getY()),
			new Point(FIELD_END.getX() - SPRITESIZE, FIELD_END.getY() - SPRITESIZE)
	};

	private final float COLLISION_OFFSET = 5f;

	private Stage stage;
	private List<GameObject> collision_objects;
	private List<Explosion> explosions;
	private List<Bomb> bombs;
	private List<Wall> walls;
	private List<Collectable> collectables;

	private Timer timer = new Timer(6);
	private Timer rundenTimer;

	private GUI gui;
	
	private float maxTime;
	private int rounds;

	
	private boolean throwbomb;

	/**
	 * @author pbg2h15aza
	 * @author pbg2h15asu
	 * @param gsm GameStateManager
	 */
	protected LocalGameState(GameStateManager gsm, String name_player1, String name_player2, float time, int rounds) {
		super(gsm);
		player1 = name_player1;
		player2 = name_player2;
		maxTime = time;
		this.rounds = rounds;
		init();
	}

	/**
	 * @author pbg2h15asu
	 */
	@Override
	public void init() {
		batch = new SpriteBatch();

		collision_objects = new LinkedList<GameObject>();
		explosions = new LinkedList<Explosion>();
		bombs = new LinkedList<Bomb>();
		walls = new LinkedList<>();
		collectables = new LinkedList<Collectable>();

		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font_countdown = new BitmapFont();
		font_countdown.setColor(Color.FIREBRICK);
		font_countdown.getData().setScale(2);

		Object[][] field = setupField(17, 13);
		stage = new Stage((GameObject[][]) field, maxTime, StageType.STANDARD, player_spawns, rounds, Mode.LAST_MAN_STANDING);
		walls = generateWalls(17, 13);

		//texturen todo
		player = new LinkedList<Player>();
		ai = new LinkedList<KI>();
		player.add(new Player(player1, player_spawns[0], Player_Frames.P1_MV_DOWN, input[0], stage));
		player.add(new Player(player2, player_spawns[1], Player_Frames.P2_MV_DOWN, input[1], stage));
		ai.add(new KI("", player_spawns[2], Player_Frames.P3_MV_DOWN, input[2], stage));
		ai.add(new KI("", player_spawns[3], Player_Frames.P4_MV_DOWN, input[3], stage));

		rundenTimer = new Timer(maxTime);

		gui = new GUI(rundenTimer, player.get(0), player.get(1), ai.get(0), ai.get(1), gsm, this);

		Tunes.MUSIC_GAME_BACKGROUND.Play();
	}

	/**
	 * @author pbg2h15asu,pbd2h15aho
	 */
	@Override
	public void update(float dt) {

		if (timer.isFinished()) {
			rundenTimer.update(dt);

			// spieler input
			for (int i = 0; i < player.size(); i++) {

				InputConfig playerinput = player.get(i).getControls();

				Point pos = player.get(i).getPos();

				// bewegung auf x
				//links
				if (Gdx.input.isKeyPressed(playerinput.getKeyLeft())) {
					player.get(i).setSprite(i,"LEFT");
					player.get(i).getBombDirection().set(-100, 0);
					Point tmp = new Point(player.get(i).getPos());
					tmp.translate(-player.get(i).getMoveSpeed(), 0);
					if (!collision(tmp, collision_objects))
						player.get(i).setPos(tmp);
				}
				//rechts
				if (Gdx.input.isKeyPressed(playerinput.getKeyRight())) {
					player.get(i).setSprite(i,"RIGHT");
					player.get(i).getBombDirection().set(100, 0);
					Point tmp = new Point(player.get(i).getPos());
					tmp.translate(player.get(i).getMoveSpeed(), 0);
					if (!collision(tmp, collision_objects))
						player.get(i).setPos(tmp);
				}

				pos.setX(player.get(i).getPos().getX());

				// bewegung auf y

				//oben
				if (Gdx.input.isKeyPressed(playerinput.getKeyUp())) {
					player.get(i).setSprite(i,"UP");
					player.get(i).getBombDirection().set(0, 100);
					Point tmp = new Point(player.get(i).getPos());
					tmp.translate(0, player.get(i).getMoveSpeed());
					if (!collision(tmp, collision_objects))
						player.get(i).setPos(tmp);
				}
				//unten
				if (Gdx.input.isKeyPressed(playerinput.getKeyDown())) {
					player.get(i).setSprite(i,"DOWN");
					player.get(i).getBombDirection().set(0, -100);
					Point tmp = new Point(player.get(i).getPos());
					tmp.translate(0, -player.get(i).getMoveSpeed());
					if (!collision(tmp, collision_objects))
						player.get(i).setPos(tmp);
				}
				
				/**
				 * @author pbg2h15aln,pbg2h15ago,pbg2h15afa,pbg2h15aza,pbg2h15asu
				 */
				// bombe werfen
				
				if (Gdx.input.isKeyJustPressed(playerinput.getKeyBomb())) {
					List<GameObject> collisionObjectsWithPlayer= new LinkedList<>(collision_objects);  
					boolean collisionWithBombowner = false;
					collisionObjectsWithPlayer.addAll(player);
					throwbomb = false;
					if (player.get(i).isBombThrowable()) {
						for (Bomb bomb : bombs) {
							if (collisionWithTwoGameObjects(bomb, (GameObject) player.get(i))) {
								throwbomb = true;
								Point p = new Point(bomb.getPos().getX() + player.get(i).getBombDirection().getX(),
										bomb.getPos().getY() + player.get(i).getBombDirection().getY());
								fixBombPos(p);					
								while(collision(p, collisionObjectsWithPlayer) && !collisionWithBombowner){
										p.set(p.getX()+player.get(i).getBombDirection().getX()/2, p.getY()+player.get(i).getBombDirection().getY()/2);
										fixBombPos(p);
										if(collisionWith(p, player.get(i))){
											collisionWithBombowner = true;
										}
											
								}
						
								fixBombPos(p);
								bomb.setPos(p);		
								collisionObjectsWithPlayer.removeAll(player);
							}
							
						}
					}

				}
				

				// bombe legen
				if (Gdx.input.isKeyJustPressed(playerinput.getKeyBomb()) && player.get(i).getAnzahlBomben() < player.get(i).getAnzahlBombenMax()) {
					if (!throwbomb) {
						newBomb(player.get(i).dropBomb());
						Sounds.EFFECT_BOMB_DROPPED.Play();
					}
				}
			}

			// spieler verwalten
			for (Player p : player) {
				p.update(dt);
				
				List<GameObject> list = new LinkedList<GameObject>();

				for (Explosion e : explosions)
					list.add((GameObject) e);

				//Kollision Player + Explosion
				if (collision(p.getPos(), list)) {
					p.setLife(p.getLife() - 1); // player killed
					Sounds.EFFECT_PLAYER_DIES.Play();
					if(p.getLife() < 1)
						p.setPos(new Point(300, -300));
					else{
						int i = (int) (Math.random() * 4);
						p.setPos(player_spawns[i]);
					}
				}
				//Kollision Player + Collectable
				Collectable c = collisionWith(p.getPos(), collectables);
				if(c != null){
					if(c instanceof Illness){
						if(!p.hasIllness()){
							((Illness) c).illnessSet(p);
							p.setIllness((Illness)c);
							Sounds.EFFECT_ILLNESS_COLLECTED.Play();
							System.out.println(((Illness) c).getType().toString());
						}
						collectables.remove(c);
					}else{
						((PowerUp) c).setPowerUp(p);
						Sounds.EFFECT_POWERUP_COLLECTED.Play();
						collectables.remove(c);
						System.out.println(((PowerUp) c).getType().toString());
					}
				}
			}
			
			//ai verwalten
			for (KI a : ai){
				a.update(dt);
				List<GameObject> list = new LinkedList<GameObject>();

				for (Explosion e : explosions)
					list.add((GameObject) e);

				//Kollision ai + Explosion
				if (collision(a.getPos(), list)) {
					a.setLife(a.getLife() - 1); // ai killed
					Sounds.EFFECT_PLAYER_DIES.Play();
					if(a.getLife() < 1)
						a.setPos(new Point(300, -300));
					else{
						int i = (int) (Math.random() * 4);
						a.setPos(player_spawns[i]);
					}
				}

				//Kollision ai + Collectable
				Collectable c = collisionWith(a.getPos(), collectables);
				if(c != null){
					if(c instanceof Illness && !a.hasIllness()){
						((Illness) c).illnessSet(a);
						a.setIllness((Illness)c);
						collectables.remove(c);
					}else{
						((PowerUp) c).setPowerUp(a);
						collectables.remove(c);
					}
				}
			}

			// bomben verwalten
			List<Bomb> delBomb = new LinkedList<Bomb>();
			List<Bomb> delCollBomb = new LinkedList<Bomb>();
			for (Bomb b : bombs) {
				b.update(dt);
				
				//ermöglicht von Bombe runter gehen nach platzieren
				if (!collision_objects.contains(b)) {

					List<GameObject> list = new LinkedList<GameObject>();

					for (Player p : player)
						list.add((GameObject) p);

					if (!collision(new Point(b.getPos()), list))
						collision_objects.add(b);
				}

				//Zeit aufgelaufen ? Explosion : Kollision mit Explosion ? Explosion
				if (b.getTime() <= 0) {
					delBomb.add(b);
					explosions.addAll(b.explode(stage, walls));
					if (collision_objects.contains(b))
						delCollBomb.add(b);
					b.getPlayer().setAnzahlBomben(b.getPlayer().getAnzahlBomben() - 1);
				} else {

					List<GameObject> list = new LinkedList<GameObject>();

					for (Explosion e : explosions)
						list.add((GameObject) e);

					if (collision(new Point(b.getPos()), list) && bombs.contains(b)) {
						explosions.addAll(b.explode(stage, walls));
						delBomb.add(b);
						if (collision_objects.contains(b))
							delCollBomb.add(b);
						b.getPlayer().setAnzahlBomben(b.getPlayer().getAnzahlBomben() - 1);
					}
				}
			}
			//Explosdierte Bomben entfernen
			bombs.removeAll(delBomb);
			collision_objects.removeAll(delCollBomb);

			// explosionen verwalten
			List<Explosion> delExplosion = new LinkedList<Explosion>();
			for (Explosion e : explosions) {
				e.update(dt);

				if (e.getTime() <= 0)
					delExplosion.add(e);
			}
			explosions.removeAll(delExplosion);

			// kisten verwalten
			List<Wall> delWall = new LinkedList<Wall>();
			for (Wall w : walls) {

				List<GameObject> list = new LinkedList<GameObject>();

				for (Explosion e : explosions)
					list.add((GameObject) e);

				//Kollision mit Explosion -> entfernen und mögliches Collectable platzieren
				if (collision(w.getPos(), list)) {
					if (w.getContent() != null) {
						Collectable c = w.getContent();
						c.setPos(w.getPos());
						collectables.add(c);
					}
					Sounds.EFFECT_WALL_DESTROYED.Play();
					delWall.add(w);
				}
			}
			walls.removeAll(delWall);
			collision_objects.removeAll(delWall);
			
			/**
			 * @author pbg2h15ani
			 */
			if(spielVorbei()){
				Tunes.MUSIC_GAME_FINISHED.Play();
				Statistic[] stats = new Statistic[4];
				int tmp = 0;
				List<Player> all = new LinkedList<>();
				all.addAll(player);
				all.addAll(ai);
				for(Player p: all){
					stats[tmp] = p.getStats();
					++tmp;
				}
				/**
				 * @author pbg2h15ake
				 */
				Player winner;
				if(player.get(0).getLife()>0){
					winner = player.get(0);
				}else{
					winner = player.get(1);
				}
				
				gsm.setState(GameStateManager.TMPENDSCREENTDOT, winner);
				
				//gsm.setState(gsm.ROUND_STATISTIC, stats);
			}

		} else {
			timer.update(dt);
		}

	}

	/**
	 * @author pbg2h15aln,pbg2h15ago
	 */
	private void fixBombPos(Point p) {
		if(p.getX() < FIELD_START.getX()){
			p.set(FIELD_END.getX()-SPRITESIZE,p.getY());
		}else if(p.getX() > FIELD_END.getX()){
			p.set(FIELD_START.getX(),p.getY());
		}else if(p.getY() > FIELD_END.getY()){
			p.set(p.getX(),FIELD_START.getY());
		}else if(p.getY() < FIELD_START.getY()){
			p.set(p.getX(),FIELD_END.getY()-SPRITESIZE);
			
		}
	}

	/**
	 * @author pbg2h15asu
	 * @return rendert das Spielfeld: Spieler > Explosionen > Bomben > PowerUps/Illness > Kisten > Spielfeld
	 */
	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		drawField(stage);

		for (Wall w : walls) {
			w.render(batch);
		}
		for (Collectable c : collectables){
			c.render(batch);
		}
		for (Bomb b : bombs) {
			b.render(batch);
		}
		for (Explosion e : explosions) {
			e.render(batch);
		}
		for (Player p : player) {
			p.render(batch);
		}
		if (!timer.isFinished()) {
			int time = (int) timer.getTime();
			font_countdown.draw(batch, "" + time, Gdx.graphics.getWidth() / 2 - 8, Gdx.graphics.getHeight() / 2 - 20);
		}
		gui.render(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	/**
	 * @author pbg2h15asu
	 * @param width breite des Spielfelds
	 * @param height höhe des Spielfelds
	 * @return Spielfeld als 2dim Array
	 */
	private Object[][] setupField(int width, int height) {

		GameObject[][] newField = new GameObject[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

				int posx = 75 + SPRITESIZE * j;
				int posy = 0 + SPRITESIZE * i;

				if (i == 0 || j == 0 || i == height - 1 || j == width - 1) {
					Pillar pillar = new Pillar(new Point(posx, posy), false);
					newField[i][j] = (GameObject) pillar;
					collision_objects.add(pillar);
				} else if (((i % 2) == 0 && (j % 2) == 0)) {
					Pillar pillar = new Pillar(new Point(posx, posy), true);
					newField[i][j] = (GameObject) pillar;
					collision_objects.add(pillar);
				} else {
					Background background = new Background(new Point(posx, posy));
					newField[i][j] = (GameObject) background;
				}
			}
		}

		return newField;
	}

	/**
	 * @author pbg2h15awi
	 * @param width briete des felds
	 * @param height höhe des felds
	 * @return
	 */
	private List<Wall> generateWalls(int width, int height) {

		List<Wall> newList = new LinkedList<>();

		for (int i = 1; i < height - 1; i++) {
			for (int j = 1; j < width - 1; j++) {

				int posx = 75 + SPRITESIZE * j;
				int posy = 0 + SPRITESIZE * i;

				if (!(((i % 2) == 0 && (j % 2) == 0) || i < 3 && j < 3 || i > height - 4 && j > width - 4
						|| i < 3 && j > width - 4 || i > height - 4 && j < 3)) {
					Wall wall = new Wall(new Point(posx, posy));
					newList.add(wall);
					collision_objects.add(wall);
				}
			}
		}

		for (int i = 0; i < 40; i++) {
			int x = (int) (Math.random() * newList.size());
			collision_objects.remove(newList.get(x));
			newList.remove(x);
		}

		return newList;
	}

	/**
	 * @author pbg2h15asu
	 * @param s Stage
	 */
	private void drawField(Stage s) {
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 17; j++) {
				GameObject g = s.getFields()[i][j];
				g.render(batch);
			}
		}
	}

	/**
	 * @author pbg2h15asu
	 * @param b neue Bombe
	 * @return positioniert die Bombe mittig auf dem Feld
	 */
	public void newBomb(Bomb b) {
		Point tmp = new Point(b.getPos());
		int tmpx = (int) tmp.getX();
		int tmpy = (int) tmp.getY() + 25;

		tmpx /= SPRITESIZE;
		tmpy /= SPRITESIZE;

		tmpx *= SPRITESIZE;
		tmpy *= SPRITESIZE;

		Point newPoint = new Point(tmpx + 25, tmpy);

		b.setPos(newPoint);
		bombs.add(b);
	}

	/**
	 * @author pbg2h15asu
	 * @param p Position des Objects
	 * @param objects Objekte die auf Kollision überprüft werden sollen
	 * @return true wenn Kollision erfolgt
	 */
	private boolean collision(Point p, List<GameObject> objects) {

		boolean collision = false;
		CollisionDetector cd = new CollisionDetector(p, SPRITESIZE, SPRITESIZE, COLLISION_OFFSET);

		for (GameObject o : objects) {
			if (cd.collidesWith(o))
				collision = true;
		}

		return collision;
	}
	
	private Collectable collisionWith(Point p, List<Collectable> list) {

		CollisionDetector cd = new CollisionDetector(p, SPRITESIZE, SPRITESIZE, COLLISION_OFFSET);

		for (Collectable c : list) {
			if (cd.collidesWith(c))
				return c;
		}
		
		return null;
	}

	/**
	 * @author pbg2h15aln,pbg2h15ago,pbg2h15afa
	 */
	private boolean collisionWithTwoGameObjects(GameObject g1, GameObject g2) {
		CollisionDetector cd = new CollisionDetector(g1, COLLISION_OFFSET);
		return cd.collidesWith(g2);
	}
	
	private boolean collisionWith(Point p, GameObject g) {

		CollisionDetector cd = new CollisionDetector(p, SPRITESIZE, SPRITESIZE, COLLISION_OFFSET);
		return cd.collidesWith(g);
	}
	
	/**
	 * @author pbg2h15ani
	 */
	public boolean spielVorbei()
	{
		int anzahlLebenderSpieler = 0;
		
		for (Player p:player) {
			if(p.getLife() > 0)
			{
				anzahlLebenderSpieler++;
			}
		}
		
		return anzahlLebenderSpieler < 2;
	}

}