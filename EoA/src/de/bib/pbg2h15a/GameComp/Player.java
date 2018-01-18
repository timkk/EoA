package de.bib.pbg2h15a.GameComp;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.bib.pbg2h15a.Config.InputConfig;
import de.bib.pbg2h15a.Interface.Player_Frames;
import de.bib.pbg2h15a.Uitl.Point;
import de.bib.pbg2h15a.Uitl.Statistic;
import de.bib.pbg2h15a.Uitl.Timer;

/**
 * 
 * Die Klasse Player repr�sentiert den Charakter den man im Spiel spielt  (kommentiert von Dennis Fast)
 *
 */

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
	private Player_Frames sprite;
	private float alivetime=0;
	private Timer invincible;
	/**
	 * @param name
	 *            Players name
	 * @param pos
	 *            Players position
	 *            
	 * @param controls
	 * 			  Beinhaltet die Eingabe des Spielers
	 * @param stage
	 * 			  Beinhaltet die Stage
	 * @param spritesheet
	 * 			  Beinhaltet die Darstellung des Spielers
	 * @author pbg2h15akl
	 */
	public Player(String name, Point pos, Player_Frames spritesheet, InputConfig controls, Stage stage) {
		super(pos, false, spritesheet.getFrame(0));
		this.name = name;
		this.moveSpeed = 4;
		this.bombThrowable = false;
		this.anzahlBomben = 0  ;
		this.anzahlBombenMax = 1;
		this.life = 2;
		this.stats = new Statistic();
		this.illness = null;
		this.bombRadius = 1;
		this.controls = controls;
		this.sprite=spritesheet;
		bombDirection = new Point(0,0);
		this.invincible = new Timer(0);

		
	}
	/**
	* Bombe wird auf dieselbe Position des Spielers gelet (kommeniert von Dennis Fast)
	* @return bombe
	*/
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
		if(this.moveSpeed > 8)
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
	
	/**
	 * @author pbd2h15aho
	 * setzt die Frames f�r jeden Spieler
	 */
	private void setAniTexture(Player_Frames set,int vers){
		this.spritesheet=set.getFrame(vers);
	}
	
	/**
	 * setzt die Textur des Spielers (animation)
	 * @param i Frame der Animation
	 * @param direction Richtung in der der Spieler guckt (kommeniert von Dennis Fast)
	 */
	public void setSprite(int i,String direction){
		switch(i){
		case 0:
			if(direction=="UP"){this.sprite=Player_Frames.P1_MV_UP;}
			if(direction=="DOWN"){this.sprite=Player_Frames.P1_MV_DOWN;}
			if(direction=="LEFT"){this.sprite=Player_Frames.P1_MV_LEFT;}
			if(direction=="RIGHT"){this.sprite=Player_Frames.P1_MV_RIGHT;};
		break;
		case 1:
			if(direction=="UP"){this.sprite=Player_Frames.P2_MV_UP;}
			if(direction=="DOWN"){this.sprite=Player_Frames.P2_MV_DOWN;}
			if(direction=="LEFT"){this.sprite=Player_Frames.P2_MV_LEFT;}
			if(direction=="RIGHT"){this.sprite=Player_Frames.P2_MV_RIGHT;};
		break;
		case 2:
			if(direction=="UP"){this.sprite=Player_Frames.P3_MV_UP;}
			if(direction=="DOWN"){this.sprite=Player_Frames.P3_MV_DOWN;}
			if(direction=="LEFT"){this.sprite=Player_Frames.P3_MV_LEFT;}
			if(direction=="RIGHT"){this.sprite=Player_Frames.P3_MV_RIGHT;};
		break;
		case 3:
			if(direction=="UP"){this.sprite=Player_Frames.P4_MV_UP;}
			if(direction=="DOWN"){this.sprite=Player_Frames.P4_MV_DOWN;}
			if(direction=="LEFT"){this.sprite=Player_Frames.P4_MV_LEFT;}
			if(direction=="RIGHT"){this.sprite=Player_Frames.P4_MV_RIGHT;};
		}
	}

	/**
	 * zeichnet den Spieler und blinkt wenn er getrofen wurde
	 * @param sb SpriteBatch auf der gezeichnet wird (Kommentiert von Dennis Fast)
	 */
	@Override
	public void render(SpriteBatch sb) {
		if(!isInvincible() || (int) (invincible.getTime()*10)%2==0 ){
		sb.draw(this.getSpritesheet(), this.getPos().getX(), this.getPos().getY());
		}
		
	}

	/**
	 * @author pbd2h15aho
	 * 
	 * @param dt Zeit
	 * �berpr�ft ob ein Spieler eine Krankheit hat wenn ja 
	 * wird die Zeit geupdatet und die Methode illnessExpired 
	 * wird von der Klasse Illnes aufgerufen
	 * 
	 * ausf�hrung Animation der Spieler 
	 * 
	 * unverwundbarkeitsTimer wird geudatet (kommentiert von Dennis Fast)
	 */
	@Override
	public void update(float dt) {
		if(hasIllness()){
			illness.update(dt);
			illness.illnessExpired();
		}
		alivetime+=dt;
		if(alivetime%2>=0&&alivetime%2<0.15||alivetime%2>=0.5&&alivetime%2<0.65||alivetime%2>=1&&alivetime%2<1.15||alivetime%2>=1.5&&alivetime%2<1.65){
			setAniTexture(sprite, 1);
		}
		if(alivetime%2>=0.15&&alivetime%2<0.35||alivetime%2>=0.65&&alivetime%2<0.85||alivetime%2>=1.15&&alivetime%2<1.35||alivetime%2>=1.65&&alivetime%2<1.85){
			setAniTexture(sprite, 2);
		}
		if(alivetime%2>=0.35&&alivetime%2<0.5||alivetime%2>=0.85&&alivetime%2<1||alivetime%2>=1.35&&alivetime%2<1.5||alivetime%2>=1.85&&alivetime%2<2){
			setAniTexture(sprite, 0);
		}
		invincible.update(dt);
	}
	
	/**
	 * *@author pbg2h15ary
	 * @return false wenn man verwundbar ist
	 * @return true wenn man unverwundbar ist(komeniert von Dennis Fast)
	 */
	public boolean isInvincible(){
		if (invincible.isFinished()){
			return false;
		}
		return true;
	}
	
	public void setInvincible(){
		invincible.setTime(1.5f);
	}
}

