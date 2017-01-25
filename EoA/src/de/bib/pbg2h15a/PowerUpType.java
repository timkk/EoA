package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;

public enum PowerUpType {
	
	SPEED_UP("Speed Up", 0.5f, false,"img/Stage_1/speed+.png"),
	BOMB_PLUS("Bombe Plus", 1, false,"img/Stage_1/bomb+.png"),
	RANGE_PLUS("Range Plus", 1, false,"img/Stage_1/range+.png"),
	BOMB_THROW("Bomb Throw", 0, true,"img/Stage_1/throw.png"),
	LIVE_PLUS("Live Plus", 1, false,"img/Stage_1/speed+.png");
	
	private String description;
	private float value;
	private boolean active;
	private Texture texture;
	
	private PowerUpType(String description, float value, boolean active,String path) {
		this.description = description;
		this.value = value;
		this.active = active;
		this.texture= new Texture(path);
	}

	public String getDescription() {
		return description;
	}

	public float getValue() {
		return value;
	}
	
	public Texture getTexture(){
		return texture;
	}
	
	public boolean isActive() {
		return active;
	}
}