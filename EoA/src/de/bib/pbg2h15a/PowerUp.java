package de.bib.pbg2h15a;

public class PowerUp {	
	
	private PowerUpType type;
	
	/**
	 * @author pbg2h15are
	 */
	public PowerUp() {
		randPowerUpType();
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
	private void randPowerUpType(){
		PowerUpType[] types = PowerUpType.values();
		int rndNumber = (int)(Math.random()*6);
		this.type = types[rndNumber];
	}
}