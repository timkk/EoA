package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;


/**
 * Enum in der die einzelnen PowerUp-Typen aufgelistet sind.
 * 
 * Auflistung der geforderten PowerUp-Typen in einem Enum mit <code>description</code>, <code>value</code>, <code>active</code> und <code>texture</code> Attributen.
 * 
 * @author pbg2h15are
 * @version 1.0
 */
public enum PowerUpType {
	
	/**
	 * Die Enum-Konstanten sind die geforderten PowerUp-Typen
	 * Diese besitzen die Attribute <code>description</code>, <code>value</code>, <code>active</code> und <code>texture</code>.
	 * 
	 * @author pbg2h15are
	 */
	SPEED_UP("Speed Up", 0.5f, false,"img/Stage_1/speed+.png"),
	BOMB_PLUS("Bombe Plus", 1, false,"img/Stage_1/bomb+.png"),
	RANGE_PLUS("Range Plus", 1, false,"img/Stage_1/range+.png"),
	BOMB_THROW("Bomb Throw", 0, true,"img/Stage_1/throw.png"),
	LIVE_PLUS("Live Plus", 1, false,"img/Stage_1/leben+.png");
	
	/**
	 * Dies ist der Name des <code>PowerUp</code>.
	 * 
	 * @author pbg2h15are
	 */
	private String description;
	
	/**
	 * Dies ist der Wert um den die Variable im <code>Player</code> geändert wird.
	 * Die Methode <code>setPowerUp</code> in <code>Player</code> benutzt diesen Wert.
	 * 
	 * @author pbg2h15are
	 */
	private float value;
	
	/**
	 * Dies wird von <b>BOMB_THROW</b> benutzt und wird durch <code>PowerUp</code> im <code>Player</code> geändert.
	 * 
	 * @author pbg2h15are
	 */
	private boolean active;
	
	/**
	 * Dies sind die Texturen die für jeden PowerUp-Typen im Asset-Ordner vorliegen.
	 * 
	 * @author pbg2h15are
	 */
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