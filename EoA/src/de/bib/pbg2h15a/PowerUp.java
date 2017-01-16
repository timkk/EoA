package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PowerUp extends Collectable{	
	
	/**
	 * @author pbg2h15are
	 * @author pbg2h15ake
	 * @author pbg2h15awi
	 */
	private PowerUpType type;
	private int key;
	
	public PowerUp(Point p) {
		super(p);
		setRndmPowerUp();
		this.spritesheet = new Texture("img/Stage_1/Feuerfalle.png");	//temporäres Bild
	}
	
	/**
	 * Give the Constructor a PowerUp with a Random Type
	 */
	private void setRndmPowerUp(){
		int zufallszahl = (int)(Math.random()*4) + 0;
		
		this.key = zufallszahl;
		//TODO individuelle Power-Up-Bilder
		//powerUpSet();
	}
	
	public void setPowerUp(Player player){
		switch(key)
		{
			case 0:
				player.addMoveSpeed(PowerUpType.SPEED_UP.getValue());
				break;
				
			case 1:
				player.setAnzahlBombenMax(player.getAnzahlBomben() + PowerUpType.BOMB_PLUS.getValue());
				break;
				
			case 2:
				player.increaseBombRadius(PowerUpType.RANGE_PLUS.getValue());
				break;
				
			case 3:
				player.setBombThrowable(PowerUpType.BOMB_THROW.isActive());
				break;
				
			case 4:
				player.setLife(player.getLife() + PowerUpType.LIVE_PLUS.getValue());
				break;
		}
	}
	
	public PowerUpType getType() {
		
		return type;
	}
	
	public void setType(PowerUpType type) {
		
		this.type = type;
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(spritesheet, pos.getX(), pos.getY());
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString(){
		return "" + key;
	}
}