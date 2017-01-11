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

public class LocalGameState extends GameState {

	private BitmapFont font;
	private BitmapFont font_countdown;

	private SpriteBatch batch;

	private Texture texture_player;

	private List<Player> player;

	protected final InputConfig[] input = {
			new InputConfig(Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S, Input.Keys.Q),
			new InputConfig(Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP, Input.Keys.DOWN, Input.Keys.PAGE_DOWN),
			new InputConfig(Input.Keys.J, Input.Keys.L, Input.Keys.I, Input.Keys.K, Input.Keys.SPACE),
			new InputConfig(Input.Keys.NUMPAD_1, Input.Keys.NUMPAD_3, Input.Keys.NUMPAD_5, Input.Keys.NUMPAD_2,
					Input.Keys.NUMPAD_0) };;

	private final Point FIELD_START = new Point(125f, 50f);
	private final Point FIELD_END = new Point(875f, 600f);
	private final int SPRITESIZE = 50;

	protected final Point[] player_spawns = { new Point(FIELD_START.getX(), FIELD_END.getY() - SPRITESIZE),
			new Point(FIELD_END.getX() - SPRITESIZE, FIELD_START.getY()),
			new Point(FIELD_START.getX(), FIELD_START.getY()),
			new Point(FIELD_END.getX() - SPRITESIZE, FIELD_END.getY() - SPRITESIZE) };

	private final float COLLISION_OFFSET = 1f;

	private Stage stage;
	private List<GameObject> collision_objects;
	private List<Explosion> explosions;
	private List<Bomb> bombs;
	private List<Wall> walls;
	private List<Collectable> collectables;

	private Timer timer = new Timer(1);

	private GUI gui;
	private Timer rundenTimer;

	private float directionX = 0;
	private float directionY = 0;
	private boolean throwbomb;

	protected LocalGameState(GameStateManager gsm) {
		super(gsm);
		init();
	}

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
		stage = new Stage((GameObject[][]) field, 300, StageType.STANDARD, player_spawns, 3, Mode.LAST_MAN_STANDING);
		walls = generateWalls(17, 13);

		texture_player = new Texture("img/Stage_1/Windfalle.png");

		player = new LinkedList<Player>();
		for (int i = 0; i < 4; i++) {
			player.add(new Player("Player " + i, player_spawns[i], texture_player, input[i], stage));
		}

		rundenTimer = new Timer(300);

		gui = new GUI(rundenTimer, player.get(0), player.get(1), player.get(2), player.get(3), gsm, this);

		Tunes.MUSIC_GAME_BACKGROUND.Play();

	}

	@Override
	public void update(float dt) {

		if (timer.isFinished()) {
			rundenTimer.update(dt);

			// spieler input
			for (int i = 0; i < player.size(); i++) {

				InputConfig playerinput = player.get(i).getControls();

				Point pos = player.get(i).getPos();

				// bewegung auf x

				if (Gdx.input.isKeyPressed(playerinput.getKeyLeft())) {
					directionX = -100;
					directionY = 0;
					Point tmp = new Point(player.get(i).getPos());
					tmp.translate(-player.get(i).getMoveSpeed(), 0);
					if (!collision(tmp, collision_objects)) {
						player.get(i).setPos(tmp);
					} else {
						if (player.get(i).isBombKickable()) {
							kick(-1, 0);
						}
					}

				}
				if (Gdx.input.isKeyPressed(playerinput.getKeyRight())) {
					directionX = 100;
					directionY = 0;
					Point tmp = new Point(player.get(i).getPos());
					tmp.translate(player.get(i).getMoveSpeed(), 0);
					if (!collision(tmp, collision_objects)) {
						player.get(i).setPos(tmp);
					} else {
						if (player.get(i).isBombKickable()) {
							kick(1, 0);
						}
					}

				}

				pos.setX(player.get(i).getPos().getX());

				// bewegung auf y

				if (Gdx.input.isKeyPressed(playerinput.getKeyUp())) {
					directionX = 0;
					directionY = 100;
					Point tmp = new Point(player.get(i).getPos());
					tmp.translate(0, player.get(i).getMoveSpeed());
					if (!collision(tmp, collision_objects)) {
						player.get(i).setPos(tmp);
					} else {
						if (player.get(i).isBombKickable()) {
							kick(0, 1);
						}
					}
				}
				if (Gdx.input.isKeyPressed(playerinput.getKeyDown())) {
					directionX = 0;
					directionY = -100;
					Point tmp = new Point(player.get(i).getPos());
					tmp.translate(0, -player.get(i).getMoveSpeed());
					if (!collision(tmp, collision_objects)) {
						player.get(i).setPos(tmp);
					} else {
						if (player.get(i).isBombKickable()) {
							kick(0, -1);
						}
					}
				}
				/**
				 * @author pbg2h15aln,pbg2h15ago,pbg2h15afa,pbg2h15aza
				 */
				// bombe werfen
				if (Gdx.input.isKeyJustPressed(playerinput.getKeyBomb())) {

					throwbomb = false;
					if (player.get(i).isBombThrowable()) {
						for (Bomb bomb : bombs) {
							if (collisionWithTwoGameObjects(bomb, (GameObject) player.get(i))) {
								throwbomb = true;
								bomb.setPos(new Point(bomb.getPos().getX() + directionX,
										bomb.getPos().getY() + directionY));
							}
						}
					}

				}
				/**
				 * @author pbg2h15aln,pbg2h15ago,pbg2h15afa,pbg2h15aza
				 */
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

				List<GameObject> list = new LinkedList<GameObject>();

				for (Explosion e : explosions)
					list.add((GameObject) e);

				if (collision(p.getPos(), list)) {
					p.setLife(p.getLife() - 1); // player killed
					Sounds.EFFECT_PLAYER_DIES.Play();
					int i = (int) (Math.random() * 4);
					p.setPos(player_spawns[i]);
				}
			}

			// bomben verwalten
			List<Bomb> delBomb = new LinkedList<Bomb>();
			List<Bomb> delCollBomb = new LinkedList<Bomb>();
			for (Bomb b : bombs) {
				b.update(dt);
				if (!collision_objects.contains(b)) {

					List<GameObject> list = new LinkedList<GameObject>();

					for (Player p : player)
						list.add((GameObject) p);

					if (!collision(new Point(b.getPos()), list))
						collision_objects.add(b);
				}

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

		} else {
			timer.update(dt);
		}

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		drawField(stage);

		for (Wall w : walls) {
			w.render(batch);
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
		texture_player.dispose();

	}

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

		for (int i = 0; i < 20; i++) {
			int x = (int) (Math.random() * newList.size());
			collision_objects.remove(newList.get(x));
			newList.remove(x);
		}

		return newList;
	}

	private void drawField(Stage s) {
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 17; j++) {
				GameObject g = s.getFields()[i][j];
				g.render(batch);
			}
		}
	}

	private void newBomb(Bomb b) {
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
	 * @author pbg2h15aln,pbg2h15ago,pbg2h15afa
	 */
	private boolean collisionWithTwoGameObjects(GameObject g1, GameObject g2) {
		CollisionDetector cd = new CollisionDetector(g1, COLLISION_OFFSET);
		return cd.collidesWith(g2);
	}
	/**
	 * @author pbg2h15aln,pbg2h15ago,pbg2h15afa
	 */
	private void kick(float directionX, float directionY) {

		for (GameObject object : collision_objects) {
			if (object.getClass().getName().equals(Bomb.class.getName())) {
				System.out.println("ist eine Bombe!");
				List<GameObject> gameObjectsOhneBomb = collision_objects;
				gameObjectsOhneBomb.remove(object);
				while (!collision(object.getPos(), gameObjectsOhneBomb)) {
					System.out.println("Panos");
					object.setPos(new Point(object.pos.getX() + directionX, object.pos.getY() + directionY));
				}
			}
		}
	}

}
