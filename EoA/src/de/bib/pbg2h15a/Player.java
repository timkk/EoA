package de.bib.pbg2h15a;

import java.awt.Point;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//TODO Extends GameObject
public class Player extends GameObject {

	private int bombRadius;
	private int moveSpeed;
	private boolean bombKickable;
	private boolean bombThrowable;
	private int anzahlBomben;
	private int anzahlBombenMax;
	private int life;
	private Statistic stats;
	private Illness illness;
	private String name;
	private Stage stage;

	protected Point pos;
	protected boolean passable;
	protected Texture spritesheet;

	/**
	 *
	 * @param name
	 *            Players name
	 * @param pos
	 *            Players position
	 * 
	 * @author pbg2h15akl
	 */
	public Player(String name, Point pos, Texture spritesheet, Stage stage) {
		super(pos, false, spritesheet);
		this.name = name;
		this.moveSpeed = 2;
		this.bombKickable = false;
		this.bombThrowable = false;
		this.anzahlBomben = 1;
		this.anzahlBombenMax = 10;
		this.life = 1;
		this.stats = new Statistic();
		this.illness = null;
		this.stage = stage;
	}

	
	public Bomb dropBomb() {

		Bomb bombe = new Bomb(this, pos, this.bombRadius, stage.getFields());
		Player player = new Player("", new Point(), null);

		bombe.setPos(player.getPos());

		return bombe;
	}

	public int getBombRadius() {
		return bombRadius;
	}

	public void setBombRadius(int bombRadius) {
		this.bombRadius = bombRadius;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	/**
	 * Adds the value to the original movespeed. To decrease commit a negative
	 * number.
	 * 
	 * @param value
	 *            Value gets added to the original movesspeed.
	 * @author pbg2h15akl
	 */
	public void addMoveSpeed(int value) {
		this.moveSpeed += value;
	}

	public boolean isBombKickable() {
		return bombKickable;
	}

	public void setBombKickable(boolean bombKickable) {
		this.bombKickable = bombKickable;
	}

	public boolean isBombThrowable() {
		return bombThrowable;
	}

	public void setBombThrowable(boolean bombThrowable) {
		this.bombThrowable = bombThrowable;
	}

	public int getAnzahlBomben() {
		return anzahlBomben;
	}

	public void setAnzahlBomben(int anzahlBomben) {
		this.anzahlBomben = anzahlBomben;
	}

	public int getAnzahlBombenMax() {
		return anzahlBombenMax;
	}

	public void setAnzahlBombenMax(int anzahlBombenMax) {
		this.anzahlBombenMax = anzahlBombenMax;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public Illness getIllness() {
		return illness;
	}

	public void setIllness(Illness illness) {
		this.illness = illness;
	}

	/**
	 * @author pbg2h15akl
	 * @return If the player has an illness.
	 */
	public boolean hasIllness() {
		return this.illness != null;
	}

	public String getName() {
		return name;
	}

	public Statistic getStats() {
		return stats;
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(spritesheet, pos.x, pos.y);
	}

	@Override
	public void update(float dt) {
	}

}
