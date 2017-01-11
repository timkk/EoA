package de.bib.pbg2h15a;

import java.util.LinkedList;
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
	 * @author pbg2h15asu
	 * 
	 */
	public List<Explosion> explode(Stage stage, List<Wall> walls){
		
		List<Explosion> el = new LinkedList<Explosion>();
		
		GameObject[][] field = stage.getFields();
		
		Point tmp = new Point(this.getPos());
    	int tmpx = (int)tmp.getX();
    	int tmpy = (int)tmp.getY() + 25;
    	
    	tmpx /= 50;
    	tmpy /= 50;
    	
    	tmpx *= 50;
    	tmpy *= 50;
    	
    	Point cp = new Point(tmpx+25, tmpy);
    	
		int a = -1, b = -1;
		int z;

		for(int i=0; i<13;i++){
			for(int j=0;j<17;j++){
				if(cp.equals(field[i][j].getPos())){
					a = i;
					b = j;
				}
			}
		}
		
		if(a >= 0 && b >= 0){
			
			int range = this.player.getBombRadius();
			boolean lb = true, rb = true, ub = true, db = true;
			
			Explosion center = new Explosion(new Point(field[a][b].getPos().getX(), field[a][b].getPos().getY()));
			el.add(center);
			
			z = 0;
			int l = b - 1;
			int r = b + 1;
			int u = a + 1;
			int d = a - 1;
			GameObject left = field[a][l-z];
			GameObject right = field[a][r+z];
			GameObject up = field[u+z][b];
			GameObject down = field[d-z][b];
			
			while(z < range && (lb || rb || ub || db)){
				
				if((left instanceof Pillar))
					lb = false;
				else{
					el.add(new Explosion(left.getPos()));
					left = field[a][l-z];
					for(Wall w : walls){
						if(left.getPos().equals(w.getPos())){
							el.add(new Explosion(left.getPos()));
							lb = false;	
						}
					}
				}
				if((right instanceof Pillar))
					rb = false;
				else{
					el.add(new Explosion(right.getPos()));
					right = field[a][r+z];
					for(Wall w : walls){
						if(right.getPos().equals(w.getPos())){
							el.add(new Explosion(right.getPos()));
							rb = false;
						}
					}
				}
				if((up instanceof Pillar))
					ub = false;
				else{
					el.add(new Explosion(up.getPos()));
					up = field[u+z][b];
					for(Wall w : walls){
						if(up.getPos().equals(w.getPos())){
							el.add(new Explosion(up.getPos()));
							ub = false;
						}
					}
				}
				if((down instanceof Pillar))
					db = false;
				else{
					el.add(new Explosion(down.getPos()));
					down = field[d-z][b];
					for(Wall w : walls){
						if(down.getPos().equals(w.getPos())){
							el.add(new Explosion(down.getPos()));
							db = false;
						}
					}
				}
				z++;
			}
		}
		
		return el;
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
