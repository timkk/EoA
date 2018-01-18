package de.bib.pbg2h15a.GameComp;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.bib.pbg2h15a.GameState.GameStateManager;
import de.bib.pbg2h15a.GameState.LocalGameState;
import de.bib.pbg2h15a.Uitl.Point;

/**
 * @author pbg2h15ary
 * @author pbg2h15ake
 * @author pbg2h15asu
 * Eine Krankheit die einen negativen Effekt auf einen Spieler hat 
 * (Kommentiert von Felix Kellmereit pbg2h15ake)
 */
public class Illness extends Collectable{

	
	protected Player player;
	private IllnessType type = null;
	protected float orgValue;
	private int key;
	private boolean rndDrop = false;
	public float time;
	
	public Illness(Point p){
		super(p);
		setRndmIllnes();
		this.time = (float) ((Math.random() * 6) + 5);
		this.spritesheet = new Texture("img/Stage_1/Krankheit.png");
	}
	
	private void setRndmIllnes(){
		//Zufallszahl um eine der 6 Krankheiten auszusuchen
		int zufallszahl = (int)(Math.random()*6);
		
		this.key = zufallszahl;
		//TODO illness bilder
	}

	public boolean illnessExpired(){
		if(time <= 0)
		{
			undoIllness();
			player.setIllness(null);
			return true;
		}
		
		return false;
	}

	/**
	 * @author pbg2h15ake
	 * @author pbg2h15asu
	 * �ndert abh�ngig vom IllnesType die Krankheit auf dem Spieler
	 */
	public void illnessSet(Player p){
		this.player = p;
		
		switch(key)
		{
			//Explosionsradius minimum
			case 0:
				this.orgValue = player.getBombRadius();
				player.setBombRadius(IllnessType.EXPLOSIONSRADIUS_VERKLEINERN.getValue());
				type = IllnessType.EXPLOSIONSRADIUS_VERKLEINERN;
				break;
			
			//Bombe zuf�llig legen
			case 1:
				this.rndDrop = true;
				type = IllnessType.BOMBEN_ZUFAELLIG;
				break;
			
			//Bomben legen deaktivieren
			case 2:
				this.orgValue = player.getAnzahlBombenMax();
				player.setAnzahlBombenMax(IllnessType.BOMBEN_DEAKTIVIEREN.getValue());
				type = IllnessType.BOMBEN_DEAKTIVIEREN;
				break;
			
			//Geschwindigkeitserh�hung
			case 3:
				this.orgValue = player.getMoveSpeed();
				player.setMoveSpeed(IllnessType.GESCHWINDIGKEITSERHÖHUNG.getValue());
				type = IllnessType.GESCHWINDIGKEITSERHÖHUNG;
				break;
			
			//Geschwindigkeitsverringerung
			case 4:
				this.orgValue = player.getMoveSpeed();
				player.setMoveSpeed(IllnessType.GESCHWINDIGKEITSVERRINGERUNG.getValue());
				type = IllnessType.GESCHWINDIGKEITSVERRINGERUNG;
				break;
			
			//Invertierte Steuerung
			case 5:
				this.orgValue = player.getMoveSpeed();
				player.setMoveSpeed(player.getMoveSpeed() * IllnessType.STEUERUNG_INVERTIERT.getValue());
				type = IllnessType.STEUERUNG_INVERTIERT;
				break;
		}
	}
	
	/**
	 * @author pbg2h15asu
	 * Macht den Effekt der Krankheit r�ckg�ngig
	 */
	public void undoIllness(){

		switch(key)
		{
			//Explosionsradius minimum
			case 0:
				player.setBombRadius((int)this.orgValue);
				break;
			
			//Bombe zuf�llig legen
			case 1:
				this.rndDrop = false;
				break;
			
			//Bomben legen deaktivieren
			case 2:
				player.setAnzahlBombenMax((int)this.orgValue);
				break;
			
			//Geschwindigkeitserh�hung
			case 3:
				player.setMoveSpeed(this.orgValue);
				break;
			
			//Geschwindigkeitsverringerung
			case 4:
				player.setMoveSpeed(this.orgValue);
				break;
			
			//Invertierte Steuerung
			case 5:
				player.setMoveSpeed(this.orgValue);
				break;
		}
	}

	/**
	 * @author pbg2h15asu
	 * @author pbg2h15awi
	 * @param dt
	 * Z�hlt den Timer der Krankheit runter und legt zuf�llig Bomben wenn die zuf�llig Bomben legen Krankheit aktiv ist
	 */
	public void update(float dt) {
		time -= dt;
		if(this.rndDrop && player.getAnzahlBomben() < player.getAnzahlBombenMax()){
			int rnd = (int) Math.random() * 100;
			if(rnd == 0){
				LocalGameState lgs = (LocalGameState)GameStateManager.gameState;
				lgs.newBomb(player.dropBomb());
			}
		}
	}
	
	/**
	 * @author pbg2h15awi
	 * Werte werden unabh�ngig von der Krankheit hochgez�hlt
	 */
	public void addToOrgValue(float value){
		this.orgValue += value;
	}

	public float getOrgValue() {
		return orgValue;
	}

	public void setOrgValue(float orgValue) {
		this.orgValue = orgValue;
	}

	/**
	 * Rendert die Krankheiten
	 */
	@Override
	public void render(SpriteBatch sb) {
		sb.draw(spritesheet, pos.getX(), pos.getY());
		
	}
	
	public IllnessType getType(){
		return this.type;
	}
	
	@Override
	public String toString(){
		return "" + key;
	}
}