package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * 
 * @author pbg2h15arm
 * 
 */
public abstract class GameObject {
	
	
	protected Point pos;
	protected boolean passable;
	protected Texture spritesheet;
	protected Animation animation;
	
	public GameObject(Point pos, boolean passable, Texture spritesheet){
		this.pos=pos;
		this.passable=passable;
		this.spritesheet=spritesheet;
	}
	
	public abstract void render(SpriteBatch sb);
	public abstract void update(float dt);

	public Point getPos() {
		return pos;
	}
	
	public boolean isPassable(){
		return passable;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public Animation getAnimation() {
		return animation;
	}

	public Texture getSpritesheet() {
		return this.spritesheet;
	}
}
