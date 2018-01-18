package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import de.bib.pbg2h15a.Point;
import de.bib.pbg2h15a.Rectangle;

/**
 * @author pbg2h15asu
 * dient zur Erfassung von Kollision zwischen zwei Objekten unter Angabe von Punkt sowie Länge und Breite
 * Kommentare: Michael Surmund - pbg2h15asu
 */

public class CollisionDetector {

	float x, y, width, height, os;
	
	/**
	 * Erstellt Kollider für einen Sprite
	 * @param s Sprite(beinhaltet Punkt sowie Länge und Höhe) : Sprite
	 * @param os Kollisionstoleranz von mindestens 1 : float > 1
	 */
	public CollisionDetector(Sprite s, float os){
		this.os = os;
		this.x = s.getX();
		this.y = s.getY();
		this.width = s.getWidth();
		this.height = s.getHeight();
	}

	/**
	 * Erstellt Kollider für einen Punkt
	 * @param point Der Punkt : Point
	 * @param width Breite : float
	 * @param height Höhe : float
	 */
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

	/**
	 * Erstellt Kollider für ein GameObjekt
	 * @param g Das GameObjekt : GameObject
	 * @param os Kollisionstoleranz von mindestens 1
	 */
	public CollisionDetector(GameObject g, float os){
		super();
		this.x = g.getPos().getX();
		this.y = g.getPos().getY();
		this.width = g.getSpritesheet().getWidth();
		this.height = g.getSpritesheet().getHeight();
		if(os < 1)
			this.os = 1;
		else
			this.os = os;
	}

	/**
	 * überprüft Kollision mit einem anderen GameObjekt
	 * @param other anderes GameObject : Gamebject
	 * @return true wenn Kollision erfolgt ist, false wenn nicht
	 */
	public boolean collidesWith(GameObject other){
		
		Point tmp = other.getPos();
		Texture ttmp = other.getSpritesheet();
		
		Rectangle rect1 = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle rect2 = new Rectangle(tmp.getX()+os, tmp.getY()+os, ttmp.getWidth()-os*2, ttmp.getHeight()-os*2);

		return (rect1.getX() < rect2.getX() + rect2.getWidth() &&
		        rect1.getX() + rect1.getWidth() > rect2.getX() &&
		        rect1.getY() < rect2.getY() + rect2.getHeight() &&
		        rect1.getHeight() + rect1.getY() > rect2.getY());
	}
	
	/**
	 * überprüft Kollision mit einem anderen Rechteck unter Angabe von Punkt, Breite und Höhe
	 * @param other Punkt : Point
	 * @param width Breite : float
	 * @param height Höhe : float
	 * @return true wenn Kollision erfolgt ist, false wenn nicht
	 */
	public boolean collidesWith(Point other, float width, float height){
		
		Rectangle rect1 = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle rect2 = new Rectangle(other.getX()+os, other.getY()+os, width-os*2, height-os*2);
		
		return (rect1.getX() < rect2.getX() + rect2.getWidth() &&
		        rect1.getX() + rect1.getWidth() > rect2.getX() &&
		        rect1.getY() < rect2.getY() + rect2.getHeight() &&
		        rect1.getHeight() + rect1.getY() > rect2.getY());
			
	}
	
	/**
	 * überprüft Kollision mit einem anderen Rechteck unter Angabe von Punkt Koordinaten x und y, Breite und Höhe 
	 * @param x x-Koordinate des Punktes : float
	 * @param y y-Koordinate des Punktes : float
	 * @param width Breite : float
	 * @param height Höhe : float
	 * @return true wenn Kollision erfolgt ist, false wenn nicht
	 */
	public boolean collidesWith(float x, float y, float width, float height){
		
		Rectangle rect1 = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle rect2 = new Rectangle(x+os, y+os, width-os*2, height-os*2);
		
		return (rect1.getX() < rect2.getX() + rect2.getWidth() &&
		        rect1.getX() + rect1.getWidth() > rect2.getX() &&
		        rect1.getY() < rect2.getY() + rect2.getHeight() &&
		        rect1.getHeight() + rect1.getY() > rect2.getY());
	}

	/**
	 * gibt Wert der x-Koordinate zurück
	 * @return x-Koordinate
	 */
	public float getX() {
		return x;
	}

	/**
	 * gibt Wert der y-Koordinate zurück
	 * @return y-Koordinate
	 */
	public float getY() {
		return y;
	}

	/**
	 * gibt Wert der Breite zurück
	 * @return Breite
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * gibt Wert der Höhe zurück
	 * @return Höhe
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * gibt Wert der Kollisionstoleranz zurück
	 * @return Kollisionstoleranz
	 */
	public float getOs() {
		return os;
	}
}