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
		
	}
	
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