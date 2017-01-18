package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends GameObject {

	private int bombRadius;
	private float moveSpeed;
	private boolean bombThrowable;
	private int anzahlBomben;
	private int anzahlBombenMax;
	private int life;
	private Statistic stats;
	private Illness illness;
	private String name;
	private InputConfig controls;
	private Point bombDirection;
	
	

	protected Point pos;
	protected boolean passable;
	protected Texture spritesheet;

	/**
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
		this.bombThrowable = false;
		this.anzahlBomben = 0  ;
		this.anzahlBombenMax = 1;
		this.life = 1;
		this.stats = new Statistic();
		this.illness = null;
		this.bombRadius = 1;
		this.controls = controls;
	}

	public Bomb dropBomb() {

		Bomb bombe = new Bomb(this, this.getPos(), this.getBombRadius());
		this.anzahlBomben++;

		return bombe;
	}
	/**
	 * 
	 * @author pbg2h15aln,pbg2h15ago
	 */
	public Point getBombDirection() {
		return bombDirection;
	}

	public void setBombDirection(Point bombDirection) {
		this.bombDirection = bombDirection;
	}
	
	
	
	
	public int getBombRadius() {
		return bombRadius;
	}

	public void setBombRadius(int bombRadius) {
		this.bombRadius = bombRadius;
	}
	
	public void increaseBombRadius(int amount) {
		this.bombRadius += amount;
	}

	public float getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(float moveSpeed) {
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
	 * @param f
	 *            Value gets added to the original movesspeed.
	 * @author pbg2h15akl
	 */
	public void addMoveSpeed(float f) {
		this.moveSpeed += f;
		if(this.moveSpeed > 10)
			this.moveSpeed = 10;
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

	public void setAnzahlBombenMax(int f) {
		this.anzahlBombenMax = f;
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
		if(hasIllness()){
			illness.update(dt);
			illness.illnessExpired();
		}
	}
}