package de.bib.pbg2h15a.GameComp;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.bib.pbg2h15a.Uitl.Point;

/**
 * Die Oberklasse der PowerUp's die von <code>Collectable</code> erbt.
 * 
 * Hier wird beim Laden der <code>Collectable</code>-Klasse eine zuf�llige Anzahl an PowerUp's
 * mit zuf�lligen <code>PowerUpType</code> generiert.
 * Zus�tzlich wird bei Aufnahme eines PowerUp's die Attribute des <code>Player</code> angepasst.
 * 
 * 
 * @author pbg2h15are
 * @author pbg2h15ake
 * @author pbg2h15awi
 * @version 1.0
 */

public class PowerUp extends Collectable{	
	
	/**
	 * Ein Type aus dem Enum <code>PowerupType</code>.
	 * 
	 * @author pbg2h15are
	 * @author pbg2h15ake
	 * @author pbg2h15awi
	 */
	private PowerUpType type;
	
	/**
	 * Interger der eine Zufallszahl enth�lt um den <code>PowerUpType</code> zu bestimmen.
	 * 
	 * @author pbg2h15are
	 * @author pbg2h15ake
	 * @author pbg2h15awi
	 */
	private int key;
	
	
	/**
	 * Konstruktor der <code>PowerUp</code>-Klasse in dem der <code>PowerUpType</code> gesetzt wird.
	 * 
	 * @author pbg2h15are
	 * @author pbg2h15ake
	 * @author pbg2h15awi
	 * @param p
	 */
	public PowerUp(Point p) {
		super(p);
		setRndmPowerUp();
		//this.spritesheet = new Texture("img/Stage_1/powerdown.png");	//tempor�res Bild
	}
	
	/**
	 * Wird im Konstruktor aufgerufen und setzt anhand der <code>key</code>-Variable den <code>PowerUpType</code> fest.
	 * 
	 * @author pbg2h15are
	 * @author pbg2h15ake
	 * @author pbg2h15awi
	 */
	private void setRndmPowerUp(){
		int zufallszahl = (int)(Math.random()*4) + 0;
		
		this.key = zufallszahl;
		
		/**
		 *@author pbd2h15aho
		 **/
		switch(key){
			case 0:
				spritesheet= PowerUpType.SPEED_UP.getTexture();
				break;
			case 1:
				spritesheet= PowerUpType.BOMB_PLUS.getTexture();
				break;
			case 2:
				spritesheet= PowerUpType.RANGE_PLUS.getTexture();
				break;
			case 3:
				spritesheet= PowerUpType.BOMB_THROW.getTexture();
				break;
			case 4:
				spritesheet= PowerUpType.LIVE_PLUS.getTexture();
				break;
		}	
	}
	
	/**
	 * Ver�ndert die entsprechende Variable ,die das PowerUp beeinflusst, im <code>Player</code>.
	 * 
	 * @author pbg2h15are
	 * @author pbg2h15ake
	 * @author pbg2h15awi
	 * @param player
	 */
	public void setPowerUp(Player player){
		switch(key)
		{
			case 0:
				if(player.hasIllness()){
					if(player.getIllness().getOrgValue() < 10){
						player.getIllness().addToOrgValue(PowerUpType.SPEED_UP.getValue());
					}
				} else
					player.addMoveSpeed(PowerUpType.SPEED_UP.getValue());
				type = PowerUpType.SPEED_UP;
				break;
				
			case 1:
				if(player.hasIllness()){
					player.getIllness().addToOrgValue((int)PowerUpType.BOMB_PLUS.getValue());
				}else
					player.setAnzahlBombenMax(player.getAnzahlBombenMax() + (int)PowerUpType.BOMB_PLUS.getValue());
				type = PowerUpType.BOMB_PLUS;
				break;
				
			case 2:
				if(player.hasIllness()){
					player.getIllness().addToOrgValue(PowerUpType.RANGE_PLUS.getValue());
				} else
					player.increaseBombRadius((int)PowerUpType.RANGE_PLUS.getValue());
				type = PowerUpType.RANGE_PLUS;
				break;
				
			case 3:
				player.setBombThrowable(PowerUpType.BOMB_THROW.isActive());
				type = PowerUpType.BOMB_THROW;
				break;
				
			case 4:
				player.setLife(player.getLife() + (int)PowerUpType.LIVE_PLUS.getValue());
				type = PowerUpType.LIVE_PLUS;
				break;
		}
	}
	

	public PowerUpType getType() {
		
		return type;
	}
	
	/**
	 * Speichert das jeweilige Spritesheet des <code>PowerUpType</code>.
	 * 
	 * @author pbg2h15are
	 * @author pbg2h15ake
	 * @author pbg2h15awi
	 */
	@Override
	public void render(SpriteBatch sb) {
		sb.draw(spritesheet, pos.getX(), pos.getY());
		
	}
	

	@Override
	public void update(float dt) {
		
	}
	

	@Override
	public String toString(){
		return "" + key;
	}
}