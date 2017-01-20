package de.bib.pbg2h15a;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Explosion extends GameObject {
	
	/**
	 * @author pbg2h15azu
	 */
	private float time = 1.5f;
	private Explosion_Animation expgr;
	private Player player;
	
	/**
	 * @param frameSet sets the set of frames used for the animation
	 * @param player Reference of the player who dropped the bomb and caused the explosion.	 
	 */
	public Explosion(Player player, Point pos, Explosion_Animation frameSet, int region) {
		super(pos, true, frameSet.getFrame(0));
		this.expgr = frameSet;
		this.player = player;
	}

	public Explosion(Point pos, Explosion_Animation frameSet) {
		super(pos, true, frameSet.getFrame(0));
		this.expgr = frameSet;
	}
	
//	//for testing
//	public Explosion(Point pos){
//		super(pos, true, new Texture("img/Stage_1/Feuerfalle.png"));
//	}

	public Player getPlayer() {
		return player;
	}

	public boolean shouldRemove(){
		return time <= 0;
	}
	
	/**
	 * @author pbd2h15aho
	 **/
	private void setAniTexture(Explosion_Animation set,int vers){
		this.spritesheet=set.getFrame(vers);
	}
	

	/**
	 * 
	 * @author pbg2h15awi
	 */
	@Override
	public void render(SpriteBatch sb) {
		sb.draw(this.getSpritesheet(), this.getPos().getX(), this.getPos().getY());
	}

	/**
	 * @author pbd2h15aho
	 */
	
	@Override
	public void update(float dt) {
		time -= dt;
		if(time>=0&&time<0.1){
			 setAniTexture(this.expgr, 0);
			}
			if(time>=0.1&&time<0.2){
				 setAniTexture(this.expgr,1);
				}
			if(time>=0.2&&time<0.3){
				 setAniTexture(this.expgr,2);
				}
			if(time>=0.3&&time<1.2){
				 setAniTexture(this.expgr,3);
				}
			if(time>=1.2&&time<1.3){
				 setAniTexture(this.expgr,2);
				}
			if(time>=1.3&&time<1.4){
				 setAniTexture(this.expgr,1);
				}
			if(time>=1.4){
				 setAniTexture(this.expgr, 0);
				}
	}

	public float getTime() {
		return time;
	}
	
}