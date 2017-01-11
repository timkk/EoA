package de.bib.pbg2h15a;

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
	private InputConfig controls;
	
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
	public Player(String name, Point pos, Texture spritesheet, InputConfig controls, Stage stage) {
		super(pos, false, spritesheet);
		this.name = name;
		this.moveSpeed = 2;
		this.bombKickable = false;
		this.bombThrowable = false;
		this.anzahlBomben = 0  ;
		this.anzahlBombenMax = 1;
		this.life = 1;
		this.stats = new Statistic();
		this.illness = null;
		this.stage = stage;
		this.controls = controls;
	}

	
	public Bomb dropBomb() {

		Bomb bombe = new Bomb(this, this.getPos(), this.getBombRadius(), this.getStage().getFields());
		this.anzahlBomben++;

		return bombe;
	}

	private Stage getStage() {
		return this.stage;
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
	 * @author pbg2h15asu
	 * @return input config for this player
	 */
	public InputConfig getControls() {
		return controls;
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
		sb.draw(this.getSpritesheet(), this.getPos().getX(), this.getPos().getY());
	}

	@Override
	public void update(float dt) {
		
	}
}