package de.bib.pbg2h15a;

/**
 * @author pbg2h15asu
 */

public class Point {
	
	private float x;
	private float y;

	public Point(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Point(Point p) {
		super();
		this.x = p.getX();
		this.y = p.getY();
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void translate(float x, float y) {
		this.x += x;
		this.y += y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
	public boolean equals(Point p){
		
		return (this.x == p.getX() && this.y == p.getY());
	}

	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
}