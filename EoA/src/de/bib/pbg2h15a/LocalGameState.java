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
 * Beinhaltet das Spielgeschehen
 * 
 * @author pbg2h15asu
 * @author pbg2h15aza: timer & gamestate
 * @author pbg2h15awi
 * (Kommentiert von David Langen/pbg2h15aln & Daniel Roser/pbg2h15aro)
 */

public class LocalGameState extends GameState {

	private BitmapFont font;
	private BitmapFont font_countdown;

	private SpriteBatch batch;

	private String player1 = "Dumbledore";
	private String player2 = "Gandalf";
	private String player3 = "Merlin";
	private String player4 = "Oz";

	private List<Player> player;
	private List<KI> ai;

	protected final InputConfig[] input = {
			new InputConfig(Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S, Input.Keys.SPACE), // Q->Space
			new InputConfig(Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP, Input.Keys.DOWN, Input.Keys.NUMPAD_0), // PAGE_DOWN->NUMPAD_0
			new InputConfig(Input.Keys.J, Input.Keys.L, Input.Keys.I, Input.Keys.K, Input.Keys.SPACE),
			new InputConfig(Input.Keys.NUMPAD_1, Input.Keys.NUMPAD_3, Input.Keys.NUMPAD_5, Input.Keys.NUMPAD_2,
					Input.Keys.NUMPAD_0) };

	private final Point FIELD_START = new Point(125f, 50f);
	private final Point FIELD_END = new Point(875f, 600f);
	private final int SPRITESIZE = 50;

	protected final Point[] player_spawns = { new Point(FIELD_START.getX(), FIELD_END.getY() - SPRITESIZE),
			new Point(FIELD_END.getX() - SPRITESIZE, FIELD_START.getY()),
			new Point(FIELD_START.getX(), FIELD_START.getY()),
			new Point(FIELD_END.getX() - SPRITESIZE, FIELD_END.getY() - SPRITESIZE) };

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

	private boolean pausiert;
	
	private Death death;
	
	private int playerAmount;

	/**
	 * 
	 * Spieler Initialisierung im Konstruktor.
	 * 
	 * 
	 * @author pbg2h15aza
	 * @author pbg2h15asu
	 * @param gsm GameStateManager
	 * @param name_player1 Name des ersten Spielers
	 * @param name_player2 Name des zweiten Spielers
	 * @param name_player3 Name des dritten Spielers
	 * @param name_player4 Name des vierten Spielers
	 * @param time
	 * @param rounds 
	 */
	protected LocalGameState(GameStateManager gsm, String name_player1, String name_player2, String name_player3, String name_player4, float time, int playerAmount) {
		super(gsm);
		/**
		 * @author pbg2h15agu, pbg2h15afa
		 */
		this.playerAmount = playerAmount;
		if(!name_player1.equals("")){
			player1 = name_player1;
		}
		if(name_player2 != null && !name_player2.equals("")){
			player2 = name_player2;
		}
		if(name_player3 != null && !name_player3.equals("")){
			player3 = name_player3;
		}
		if(name_player4 != null && !name_player4.equals("")){
			player4 = name_player4;
		}
		/**
		 * 
		 */
		maxTime = time;
		init();
	}

	/**
	 * 
	 * Setzt die Attribute auf vordefinierte Werte
	 * 
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
		stage = new Stage((GameObject[][]) field, maxTime, StageType.STANDARD, player_spawns, rounds,
				Mode.LAST_MAN_STANDING);
		walls = generateWalls(17, 13);

		
		player = new LinkedList<Player>();
		ai = new LinkedList<KI>();
		player.add(new Player(player1, player_spawns[0], Player_Frames.P1_MV_DOWN, input[0], stage));
		if(playerAmount>1){
			player.add(new Player(player2, player_spawns[1], Player_Frames.P2_MV_DOWN, input[1], stage));
		}
		else{
			ai.add(new KI(player2, player_spawns[1], Player_Frames.P2_MV_DOWN, input[1], stage));
		}
		if(playerAmount>2){
			player.add(new Player(player3, player_spawns[2], Player_Frames.P3_MV_DOWN, input[2], stage));
		}
		else{
			ai.add(new KI(player3, player_spawns[2], Player_Frames.P3_MV_DOWN, input[2], stage));
		}
		if(playerAmount>3){
			player.add(new Player(player4, player_spawns[3], Player_Frames.P4_MV_DOWN, input[3], stage));
		}
		else{
			ai.add(new KI(player4, player_spawns[3], Player_Frames.P4_MV_DOWN, input[3], stage));
		}

		rundenTimer = new Timer(maxTime);

		switch (playerAmount){
		case 1:gui = new GUI(rundenTimer, player.get(0), ai.get(0), ai.get(1), ai.get(2), gsm, this); break;
		case 2:gui = new GUI(rundenTimer, player.get(0), player.get(1), ai.get(0), ai.get(1), gsm, this); break;
		case 3:gui = new GUI(rundenTimer, player.get(0), player.get(1), player.get(2), ai.get(0), gsm, this); break;
		case 4:gui = new GUI(rundenTimer, player.get(0), player.get(1), player.get(2), player.get(3), gsm, this); break;
		
		}

		Tunes.MUSIC_GAME_BACKGROUND.Play();
	}

	/**
	 * Greift die Eingaben des Users auf und bewegt das Spielerobjekt bzw. platziert/wirft Bombenobjekte dementsprechend.
	 * Zudem werden Bomben und deren Explosionen verwaltet, wobei die Kollision zwischen Spieler und die Explosionsanimation behandelt wird.
	 * PowerUps bzw. Debuffs und Krankheiten können durch eine Kollision vom Spieler eingesammelt werden.
	 * Und die KI Logik der KI-Klasse wird aufgerufen um den computergesteuerten Gegenspieler zum Leben zu erwecken.
	 * Außerdem werden Bomben, die sich auf den Spielfeld befinden, nach einer bestimmtent Zeit(Timer) entfernt und erzeugen anschließend an ihrer Stelle eine Explosionsanimation(mit Explosionsradius).
	 * Kollisionen zwischen Kisten und Explosionsanimationen werden abgehandelt und daraufhin werden die entsprechenden Kisten vom Spielfeld entfernt.
	 * Sobald die Spielzeit kleiner 11 beträgt, wird ein Countdown gestartet und eingeblendet.
	 * 
	 * @author pbg2h15asu,pbd2h15aho
	 */
	@Override
	public void update(float dt) {

		if (timer.isFinished() && !pausiert) {
			rundenTimer.update(dt);

			// spieler input
			for (int i = 0; i < player.size(); i++) {

				InputConfig playerinput = player.get(i).getControls();

				Point pos = player.get(i).getPos();

				// bewegung auf x
				// links
				if (Gdx.input.isKeyPressed(playerinput.getKeyLeft())) {
					player.get(i).setSprite(i, "LEFT");
					player.get(i).getBombDirection().set(-3.125f, 0);
					Point tmp = new Point(player.get(i).getPos());
					tmp.translate(-player.get(i).getMoveSpeed(), 0);
					if (!collision(tmp, collision_objects))
						player.get(i).setPos(tmp);
				}
				// rechts
				if (Gdx.input.isKeyPressed(playerinput.getKeyRight())) {
					player.get(i).setSprite(i, "RIGHT");
					player.get(i).getBombDirection().set(3.125f, 0);
					Point tmp = new Point(player.get(i).getPos());
					tmp.translate(player.get(i).getMoveSpeed(), 0);
					if (!collision(tmp, collision_objects))
						player.get(i).setPos(tmp);
				}

				pos.setX(player.get(i).getPos().getX());

				// bewegung auf y

				// oben
				if (Gdx.input.isKeyPressed(playerinput.getKeyUp())) {
					player.get(i).setSprite(i, "UP");
					player.get(i).getBombDirection().set(0, 3.125f);
					Point tmp = new Point(player.get(i).getPos());
					tmp.translate(0, player.get(i).getMoveSpeed());
					if (!collision(tmp, collision_objects))
						player.get(i).setPos(tmp);
				}
				// unten
				if (Gdx.input.isKeyPressed(playerinput.getKeyDown())) {
					player.get(i).setSprite(i, "DOWN");
					player.get(i).getBombDirection().set(0, -3.125f);
					Point tmp = new Point(player.get(i).getPos());
					tmp.translate(0, -player.get(i).getMoveSpeed());
					if (!collision(tmp, collision_objects))
						player.get(i).setPos(tmp);
				}

				/**
				 * @author pbg2h15aln,pbg2h15ago,pbg2h15afa,pbg2h15aza,
				 *         pbg2h15asu
				 * 
				 **/
				// bombe werfen
				if (Gdx.input.isKeyJustPressed(playerinput.getKeyBomb())) {
					throwbomb = false;
					if (player.get(i).isBombThrowable()) {
						for (Bomb bomb : bombs) {
							if (collisionWithTwoGameObjects(bomb, (GameObject) player.get(i))) {
								Point poi = player.get(i).getBombDirection();
								bomb.setDirection(new Point(poi));
								throwbomb = true;
								Point p = new Point(bomb.getPos().getX() + bomb.getDirection().getX() * 32,
										bomb.getPos().getY() + bomb.getDirection().getY() * 32);

								if (collision(p, new LinkedList<GameObject>(bombs))) {
									p.set(p.getX() + bomb.getDirection().getX() * 16,
											p.getY() + bomb.getDirection().getY() * 16);
								}

								fixBombPos(p);
								bomb.setPos(p); 
							}
						}
					}
				
				}
			

				for (Bomb b : bombs) {
					List<GameObject> collisionObjectsWithoutBomb = new LinkedList<>(collision_objects);
					collisionObjectsWithoutBomb.remove(b);
					collisionObjectsWithoutBomb.addAll(player);
					collisionObjectsWithoutBomb.remove(player.get(i));
					if (collision(b.getPos(), collisionObjectsWithoutBomb)
							&&!collisionWithTwoGameObjects(b, b.getPlayer())) {
						
						Point p = new Point(b.getPos().getX() + b.getDirection().getX(),
								b.getPos().getY() + b.getDirection().getY());
						fixBombPos(p);
						b.setPos(p);
						
					} else if(collision(b.getPos(), new LinkedList<>(player))){
						collision_objects.remove(b);
					
					}else {
						/**
						 * 
						 * @author pbg2h15awi
						 */
						b.setDirection(new Point(0, 0));
					}
				}


				// bombe legen
				if (Gdx.input.isKeyJustPressed(playerinput.getKeyBomb())
						&& player.get(i).getAnzahlBomben() < player.get(i).getAnzahlBombenMax()) {
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

				// Kollision Player + Explosion
				// if (collision(p.getPos(), list)) {
				// p.setLife(p.getLife() - 1); // player killed
				// Sounds.EFFECT_PLAYER_DIES.Play();
				// if(p.getLife() < 1)
				// p.setPos(new Point(300, -300));
				// else{
				// int i = (int) (Math.random() * 4);
				// p.setPos(player_spawns[i]);
				// }
				// }
				/**
				 * @author pbg2h15ary
				 */
				if (!p.isInvincible() && collision(p.getPos(), list)) {
					if (p.getLife() == 1) {
						p.setLife(p.getLife() - 1); // player killed
						death = new Death(p.getPos());
						Sounds.EFFECT_PLAYER_DIES.Play();
					} else {
						p.setInvincible();

						// Timer outTimer = new Timer(1.5f);
						// int i = (int) (Math.random() * 4);
						// Point outsidePos = new Point (300,-300);
						// Point curPos = new Point
						// (p.getPos().getX(),p.getPos().getY());
						// p.setPos(outsidePos);
						p.setLife(p.getLife() - 1);
						// outTimer.update(dt);
						// if (outTimer.isFinished()){
						// p.setPos(curPos);
						// }

					}
			
				}
				// Kollision Player + Collectable
				Collectable c = collisionWith(p.getPos(), collectables);
				if(c != null){
					if(c instanceof Illness){
						if(!p.hasIllness()){
							((Illness) c).illnessSet(p);
							p.setIllness((Illness)c);
							Sounds.EFFECT_ILLNESS_COLLECTED.Play();
							//System.out.println(((Illness) c).getType().toString());
						}
						collectables.remove(c);
					}
					/**
					 * @author pbg2h15afo
					 */
					else if(c instanceof FireTrap){
						bombs.add(((FireTrap) c).activate());
						collectables.remove(c);
					}
					else{
						((PowerUp) c).setPowerUp(p);
						Sounds.EFFECT_POWERUP_COLLECTED.Play();
						collectables.remove(c);
						//System.out.println(((PowerUp) c).getType().toString());
					}
				}
			}

			// ai verwalten
			for (KI a : ai) {
				a.update(dt);
				List<GameObject> list = new LinkedList<GameObject>();

				for (Explosion e : explosions)
					list.add((GameObject) e);

				// Kollision ai + Explosion
				if (collision(a.getPos(), list)) {
					a.setLife(a.getLife() - 1); // ai killed
					Sounds.EFFECT_PLAYER_DIES.Play();
					if (a.getLife() < 1)
						a.setPos(new Point(300, -300));
					else {
						int i = (int) (Math.random() * 4);
						a.setPos(player_spawns[i]);
					}
				}

				// Kollision ai + Collectable
				Collectable c = collisionWith(a.getPos(), collectables);
				if (c != null) {
					if (c instanceof Illness && !a.hasIllness()) {
						((Illness) c).illnessSet(a);
						a.setIllness((Illness) c);
						collectables.remove(c);
					} else {
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

				// ermöglicht von Bombe runter gehen nach platzieren
				if (!collision_objects.contains(b)) {

					List<GameObject> list = new LinkedList<GameObject>();

					for (Player p : player)
						list.add((GameObject) p);

					if (!collision(new Point(b.getPos()), list))
						collision_objects.add(b);
				}

				// Zeit aufgelaufen ? Explosion : Kollision mit Explosion ?
				// Explosion
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
			// Explosdierte Bomben entfernen
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

				// Kollision mit Explosion -> entfernen und mögliches
				// Collectable platzieren
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
			if (spielVorbei()) {
				Tunes.MUSIC_GAME_FINISHED.Play();
				Statistic[] stats = new Statistic[4];
				int tmp = 0;
				List<Player> all = new LinkedList<>();
				all.addAll(player);
				all.addAll(ai);
				for (Player p : all) {
					stats[tmp] = p.getStats();
					++tmp;
				}
				/**
				 * @author pbg2h15ake
				 */
				/*
				 * Player winner; if(player.get(0).getLife()>0){ winner =
				 * player.get(0); }else{ winner = player.get(1); }
				 * 
				 * gsm.setState(GameStateManager.TMPENDSCREENTDOT, winner);
				 * //tdot
				 */
				gsm.setState(gsm.ROUND_STATISTIC, stats, all, playerAmount);
			}

		} else {
			timer.update(dt);
		}

	}
	
	//Ende Update

	/**
	 * Sobald ein Punkt (insbesondere der einer Bombe) über den Spielfeldrand gelangt wird es auf die gegenüberliegende Seite gesetzt.
	 * @author pbg2h15aln,pbg2h15ago
	 */
	private void fixBombPos(Point p) {
		if (p.getX() < FIELD_START.getX()) {
			p.set(FIELD_END.getX() - SPRITESIZE, p.getY());
		} else if (p.getX() > FIELD_END.getX()) {
			p.set(FIELD_START.getX(), p.getY());
		} else if (p.getY() > FIELD_END.getY()) {
			p.set(p.getX(), FIELD_START.getY());
		} else if (p.getY() < FIELD_START.getY()) {
			p.set(p.getX(), FIELD_END.getY() - SPRITESIZE);

		}
	}

	

	/**
	 * Zeichnet alle Spielelemente
	 * @author pbg2h15ani
	 * @author pbg2h15asu
	 * @return rendert das Spielfeld: Spieler > Explosionen > Bomben >
	 *         PowerUps/Illness > Kisten > Spielfeld
	 * @return Warnung bei 10 Sekunden verbleibend eingefügt
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
		for (Collectable c : collectables) {
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
		if (rundenTimer.getTime() < 11 && rundenTimer.getTime() >= 9) {
			// int rTime = (int) timer.getTime();
			font_countdown.draw(batch, "Achtung, nur noch 10 Sekunden verbleiben!", Gdx.graphics.getWidth() / 2 - 260,
					Gdx.graphics.getHeight() / 2 - 35);
		}
//		TODO Death rendern
//		if(death != null)
//		{
//			death.render()
//		}		
		gui.render(batch);
		batch.end();
	}
	
	
	/**
	 * Gibt Resourcen frei. 
	 * #LibGdxClass
	 */
	@Override
	public void dispose() {
		batch.dispose();
	}

	/**
	 * @author pbg2h15asu
	 * @param width
	 *            breite des Spielfelds
	 * @param height
	 *            höhe des Spielfelds
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
	 * Generiert die Wände, die die Spielbegrenzung und die sich im Spielfeld befindenden Säulen darstellen.
	 * 
	 * @author pbg2h15awi
	 * @param width
	 *            breite des felds
	 * @param height
	 *            höhe des felds
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
	 * Zeichnet das Spielfeld
	 * @author pbg2h15asu
	 * @param s
	 *            Stage
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
	 * 
	 * Erlaubt eine Bombe mittig auf einen Spielfeld-Feld zu platzieren.
	 * @author pbg2h15asu
	 * @param b
	 *            neue Bombe
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
	 * Überprüft eine Liste GameObjekten, ob sie mit einem Punkt kolliedieren
	 * 
	 * @author pbg2h15asu
	 * @param p
	 *            Position des Objects
	 * @param objects
	 *            Objekte die auf Kollision überprüft werden sollen
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

	/**
	 * 
	 * Überprüft eine Liste PowerUps/Debuffs bzw Illness, ob sie mit einem Punkt kolliedieren
	 * 
	 * @param p der Punkt!!!
	 * @param list die Liste an PowerUps/Debuffs bzw. Krankheiten
	 * @return
	 */
	private Collectable collisionWith(Point p, List<Collectable> list) {

		CollisionDetector cd = new CollisionDetector(p, SPRITESIZE, SPRITESIZE, COLLISION_OFFSET);

		for (Collectable c : list) {
			if (cd.collidesWith(c))
				return c;
		}

		return null;
	}

	/**
	 * Überprüft die Kollison zwischen zwei GameObjekten
	 * @param g1 erstes GameObjekt
	 * @param g2 zweites GameObjekt
	 * @author pbg2h15aln,pbg2h15ago,pbg2h15afa
	 */
	private boolean collisionWithTwoGameObjects(GameObject g1, GameObject g2) {
		CollisionDetector cd = new CollisionDetector(g1, COLLISION_OFFSET);
		return cd.collidesWith(g2);
	}

	/**
	 * Überprüft, ob das Spiel vorbei ist
	 * 
	 * 
	 * @author pbg2h15ani
	 */
	public boolean spielVorbei() {
		int anzahlLebenderSpieler = 0;

		for (Player p : player) {
			if (p.getLife() > 0) {
				anzahlLebenderSpieler++;
			}
		}

		return anzahlLebenderSpieler < 2 || rundenTimer.getTime() <= 0;
	}

	/***
	 * 
	 * Ändert den Spielzeitzusatnd in den entgegengesetzten Zustand
	 * 
	 * @author pbg2h15afo
	 */
	public void tooglePause() {

		pausiert = !pausiert;

	}

}