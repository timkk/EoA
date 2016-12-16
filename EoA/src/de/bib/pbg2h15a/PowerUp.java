package de.bib.pbg2h15a;

public class PowerUp extends Collectable{
	
	private PowerUpType type;
	
	/**
	 * @author pbg2h15are
	 */
	public PowerUp() {
		randPowerupType();
	}
	
	public PowerUpType getType() {
		return type;
	}
	
	public void setType(PowerUpType type) {
		this.type = type;
	}
	
	/**
	 * @author pbg2h15are
	 * @return Give the Constructor a PowerUp with a Random Type
	 */
	private void randPowerupType(){
		PowerUpType[] types = {PowerUpType.SPEED_UP, PowerUpType.BOMB_PLUS, PowerUpType.RANGE_PLUS,
				PowerUpType.BOMB_THROW, PowerUpType.BOMB_KICK, PowerUpType.LIVE_PLUS};
		int rndNumber = (int)(Math.random()*6);
		this.type = types[rndNumber];
	}	
	
}
