package de.bib.pbg2h15a;

public class Debuff {	
	private DebuffType type;
	
	/**
	 * @author pbg2h15are
	 */
	public Debuff() {
		randDebuffType();
	}

	public DebuffType getType() {
		return type;
	}

	public void setType(DebuffType type) {
		this.type = type;
	}
	
	private void randDebuffType(){
		DebuffType[] types = {DebuffType.SPEED_DOWN, DebuffType.BOMB_MINUS, DebuffType.RANGE_MINUS};
		int rndNumber = (int)(Math.random()*3);
		this.type = types[rndNumber];
	}

}