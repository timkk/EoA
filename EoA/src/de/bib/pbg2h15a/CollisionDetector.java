package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import de.bib.pbg2h15a.Point;
import de.bib.pbg2h15a.Rectangle;

/**
 * 
 * @author pbg2h15asu
 */

public class CollisionDetector {

	float x, y, width, height, os;
	
	public CollisionDetector(Sprite s, float os){
		this.os = os;
		this.x = s.getX();
		this.y = s.getY();
		this.width = s.getWidth();
		this.height = s.getHeight();
	}

	public CollisionDetector(Point point, float width, float height, float os) {
		super();
		this.x = point.getX();
		this.y = point.getY();
		this.width = width;
		this.height = height;
		if(os < 1)
			this.os = 1;
		else
			this.os = os;
	}
	
	public CollisionDetector(GameObject g, float os){
		super();
		this.x = g.getPos().getX();
		this.y = g.getPos().getY();
		this.width = g.spritesheet.getWidth();
		this.height = g.spritesheet.getHeight();
		if(os < 1)
			this.os = 1;
		else
			this.os = os;
	}

	public boolean collidesWith(GameObject other){
		
		Rectangle rect1 = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle rect2 = new Rectangle(other.getPos().getX()+os, other.getPos().getY()+os, other.spritesheet.getWidth()-os*2, other.spritesheet.getHeight()-os*2);

		return (rect1.getX() < rect2.getX() + rect2.getWidth() &&
		        rect1.getX() + rect1.getWidth() > rect2.getX() &&
		        rect1.getY() < rect2.getY() + rect2.getHeight() &&
		        rect1.getHeight() + rect1.getY() > rect2.getY());
	}
	
	public boolean collidesWith(Sprite other){
		
		Rectangle rect1 = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle rect2 = new Rectangle(other.getX()+os, other.getY()+os, other.getWidth()-os*2, other.getHeight()-os*2);
		
		return (rect1.getX() < rect2.getX() + rect2.getWidth() &&
		        rect1.getX() + rect1.getWidth() > rect2.getX() &&
		        rect1.getY() < rect2.getY() + rect2.getHeight() &&
		        rect1.getHeight() + rect1.getY() > rect2.getY());
			
	}
	
	public boolean collidesWith(float x, float y, float width, float height){
		
		Rectangle rect1 = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle rect2 = new Rectangle(x+os, y+os, width-os*2, height-os*2);
		
		return (rect1.getX() < rect2.getX() + rect2.getWidth() &&
		        rect1.getX() + rect1.getWidth() > rect2.getX() &&
		        rect1.getY() < rect2.getY() + rect2.getHeight() &&
		        rect1.getHeight() + rect1.getY() > rect2.getY());
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getOs() {
		return os;
	}
}