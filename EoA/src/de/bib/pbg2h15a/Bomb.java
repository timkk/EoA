package de.bib.pbg2h15a;

import java.awt.Point;
import java.awt.RenderingHints.Key;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javafx.scene.input.KeyCode;

/**
 * 
 * @author pbg2h15ast
 *
 */

public class Bomb extends GameObject{

	private int radius;
	private float time;
	private Player player;
	private GameObject[][] stage;
	

	/**
	 * 
	 * @param player Reference of the player who dropped the bomb.	 
	 */
	public Bomb(Player player, Point pos, boolean passable, Texture spritesheet, int radius, float time, GameObject[][] stage) {
		super(pos, passable, spritesheet);
		//Übergibt  den Spieler, der die Bombe gelegt hat
		this.player = player;
		this.radius = radius;
		this.time = time;
		this.stage = stage;
	}

	
	
	public Player getPlayer() {
		return player;
	}



	public int getRadius() {
		
		return radius;
	}

	public void setRadius(int radius) {
		
		this.radius = radius;
	}

	public float getTime() {
		
		return time;
	}

	public void setTime(float time) {
		
		this.time = time;
	}

	/**
	 * 
	 * @author pbg2h15azu
	 */
	public void explode(GameObject[][] stage){
		


		
		if(time <= 0){
			Texture explosion = new Texture("");
			Explosion mitte = new Explosion(getPos(), explosion, 0);
			stage[mitte.getPos().x][mitte.getPos().y] = mitte; 
			
			//Explosion in +y-Richtung
			for (int i = 1; i <= radius; i++){
				if(stage[pos.x][pos.y+1] instanceof Wand){
					//TODO Kollision (Player, Bombe, PowerUp)
				}
			}
			
			//Explosion in -y-Richtung
			for (int i = 1; i <= radius; i++){
				if(stage[pos.x][pos.y-1] instanceof Wand){
					//TODO Kollision (Player, Bombe, PowerUp)
				}
			}
			
			//Explosion in +x-Richtung
			for (int i = 1; i <= radius; i++){
				if(stage[pos.x+1][pos.y] instanceof Wand){
					//TODO Kollision (Player, Bombe, PowerUp)
				}
			}
			
			//Explosion in -x-Richtung
			for (int i = 1; i <= radius; i++){
				if(stage[pos.x-1][pos.y] instanceof Wand){
					//TODO Kollision (Player, Bombe, PowerUp)
				}
			}
		}
		
		
	}
	

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(spritesheet, pos.x, pos.y);
		
	}

	@Override
	public void update(float dt) {
		time -= dt;
		explode(stage);
	}

}
