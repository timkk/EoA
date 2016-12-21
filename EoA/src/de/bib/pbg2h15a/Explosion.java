package de.bib.pbg2h15a;

import java.awt.Point;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosion extends GameObject {
	/**
	 * @author pbg2h15azu
	 */
	private float time;
	private TextureRegion texture;

	
	
	public Explosion(Point pos, Texture spritesheet, int region) {
		super(pos, true, spritesheet);
		// TODO TextureRegion benötigt Bild
	}

	private Player player;
	
	/**
	 * 
	 * @param player Reference of the player who dropped the bomb and caused the explosion.	 
	 */
	public Explosion(Player player, Point pos, Texture spritesheet, int region) {
		super(pos, true, spritesheet);
		this.player = player;
		// TODO TextureRegion benötigt Bild
	}

	
	
	public Player getPlayer() {
		return player;
	}


	public boolean shouldRemove(){
		return time <= 0;
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x, pos.y);
	}

	@Override
	public void update(float dt) {
		time -= dt;
		
	}
	
}