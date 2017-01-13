package de.bib.pbg2h15a;

public enum PowerUpType {
	
	SPEED_UP("Speed Up", 1, false),
	BOMB_PLUS("Bombe Plus", 1, false),
	RANGE_PLUS("Range Plus", 1, false),
	BOMB_THROW("Bomb Throw", 0, true),
	BOMB_KICK("Bomb Kick", 0, true),
	LIVE_PLUS("Live Plus", 1, false);
	
	private String description;
	private int value;
	private boolean active;
	
	private PowerUpType(String description, int value, boolean active) {
		this.description = description;
		this.value = value;
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public int getValue() {
		return value;
	}
	
	public boolean isActive() {
		return active;
	}
}