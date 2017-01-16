package de.bib.pbg2h15a;

public class PowerUp {	
	
	/**
	 * @author pbg2h15are
	 * @author pbg2h15ake
	 */
	private PowerUpType type;
	private int key;
	private Player player;
	
	
	public PowerUp() {
		setRndmPowerUp();
	}
	
	/**
	 * Give the Constructor a PowerUp with a Random Type
	 */
	private void setRndmPowerUp(){
		int zufallszahl = (int)(Math.random()*4) + 0;
		
		this.key = zufallszahl;
		
		powerUpSet();
	}
	
	private void powerUpSet(){
		
		switch(key)
		{
			case 0:
				player.addMoveSpeed(PowerUpType.SPEED_UP.getValue());
				break;
				
			case 1:
				player.setAnzahlBomben(player.getAnzahlBomben() + PowerUpType.BOMB_PLUS.getValue());
				break;
				
			case 2:
				player.setBombRadius(player.getBombRadius() + PowerUpType.RANGE_PLUS.getValue());
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
}