package de.bib.pbg2h15a;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
	private static final float BOMB_TIMER = 5;
	

	/**
	 * 
	 * @param player Reference of the player who dropped the bomb.	 
	 */
	public Bomb(Player player, Point pos, int radius,  GameObject[][] stage) {
		super(pos, false, null);
		spritesheet = new Texture("img/Stage_1/Bombe.png");
		//Übergibt  den Spieler, der die Bombe gelegt hat
		this.player = player;
		this.radius = radius;
		this.time = BOMB_TIMER;
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
	public List<Explosion> explode(Stage stage){
		
		//explode in update einfügen
		
//		if(time <= 0){
//			Texture explosion = new Texture("img/Stage_1/Feuerfalle.png");
//			Explosion mitte = new Explosion(getPos(), explosion, 0);
//			stage[(int) mitte.getPos().getX()][(int) mitte.getPos().getY()] = mitte; 
//			
//			//Explosion in +y-Richtung
//			for (int i = 1; i <= radius; i++){
//				if(stage[(int) pos.getX()][(int) (pos.getY()+1)] instanceof Wand){
//					//TODO Kollision (Player, Bombe, PowerUp)
//				}
//			}
//			
//			//Explosion in -y-Richtung
//			for (int i = 1; i <= radius; i++){
//				if(stage[(int) pos.getX()][(int) (pos.getY()-1)] instanceof Wand){
//					//TODO Kollision (Player, Bombe, PowerUp)
//				}
//			}
//			
//			//Explosion in +x-Richtung
//			for (int i = 1; i <= radius; i++){
//				if(stage[(int) (pos.getX()+1)][(int) pos.getY()] instanceof Wand){
//					//TODO Kollision (Player, Bombe, PowerUp)
//				}
//			}
//			
//			//Explosion in -x-Richtung
//			for (int i = 1; i <= radius; i++){
//				if(stage[(int) (pos.getX()-1)][(int) pos.getY()] instanceof Wand){
//					//TODO Kollision (Player, Bombe, PowerUp)
//				}
//			}
//		}
		
		return null;
	}

	/**
	 * 
	 * @author pbg2h15awi
	 */
	
		@Override
		public void render(SpriteBatch sb) {
			sb.draw(spritesheet, pos.getX(), pos.getY());
			
		}

		@Override
		public void update(float dt) {
			time -= dt;
			//explode(stage);
		}



		public void dropBomb() {
			// TODO Auto-generated method stub
			
		}

}
