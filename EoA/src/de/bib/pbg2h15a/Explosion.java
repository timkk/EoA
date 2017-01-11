package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosion extends GameObject {
	
	/**
	 * @author pbg2h15azu
	 */
	private float time = 1.5f;
	private TextureRegion texture;
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

	public Explosion(Point pos, Texture spritesheet, int region) {
		super(pos, true, spritesheet);
		// TODO TextureRegion benötigt Bild
	}
	
	//for testing
	public Explosion(Point pos){
		super(pos, true, new Texture("img/Stage_1/Feuerfalle.png"));
	}

	public Player getPlayer() {
		return player;
	}

	public boolean shouldRemove(){
		return time <= 0;
	}

	/**
	 * 
	 * @author pbg2h15awi
	 */
	@Override
	public void render(SpriteBatch sb) {
		sb.draw(this.getSpritesheet(), this.getPos().getX(), this.getPos().getY());
	}

	@Override
	public void update(float dt) {
		time -= dt;
		
	}

	public float getTime() {
		return time;
	}
	
}