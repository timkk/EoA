package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.g2d.Sprite;

import de.bib.pbg2h15a.Rect;

/**
 * 
 * @author pbg2h15asu
 * protoype, not final
 */

public class CollisionDetector {

	float x, y, width, height;
	int os;
	
	public CollisionDetector(Sprite s, int offset){
		this.os = offset;
		this.x = s.getX();
		this.y = s.getY();
		this.width = s.getWidth();
		this.height = s.getHeight();
	}
	
	
	
	public CollisionDetector(float x, float y, float width, float height, int os) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.os = os;
	}



	public boolean collidesWith(Sprite other){
		
		Rect rect1 = new Rect(this.x, this.y, this.width, this.height);
		Rect rect2 = new Rect(other.getX()+os, other.getY()+os, other.getWidth()-os*2, other.getHeight()-os*2);
		
		if (rect1.getX() < rect2.getX() + rect2.getWidth() &&
		        rect1.getX() + rect1.getWidth() > rect2.getX() &&
		        rect1.getY() < rect2.getY() + rect2.getHeight() &&
		        rect1.getHeight() + rect1.getY() > rect2.getY())
			return true;
		
		return false;
	}
	
	public boolean collidesWith(float x, float y, float width, float height){
		
		Rect rect1 = new Rect(this.x, this.y, this.width, this.height);
		Rect rect2 = new Rect(x+os, y+os, width-os*2, height-os*2);
		
		if (rect1.getX() < rect2.getX() + rect2.getWidth() &&
		        rect1.getX() + rect1.getWidth() > rect2.getX() &&
		        rect1.getY() < rect2.getY() + rect2.getHeight() &&
		        rect1.getHeight() + rect1.getY() > rect2.getY())
			return true;
		
		return false;
	}
}