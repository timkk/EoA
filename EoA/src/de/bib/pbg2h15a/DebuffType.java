package de.bib.pbg2h15a;

public enum DebuffType {
	
	SPEED_DOWN("Speed Down", -1),
	BOMB_MINUS("Bomb Minus", -1),
	RANGE_MINUS("Range Minus", -1);
	
	private String description;
	private int value;
	
	private DebuffType(String description, int value) {
		this.description = description;
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}