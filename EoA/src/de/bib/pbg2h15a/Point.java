package de.bib.pbg2h15a;

/**
 * @author pbg2h15asu
 * speichert einen Punkt als x und y Koordinate
 */

public class Point {
	
	private float x;
	private float y;

	/**
	 * erstellt einen Punkt unter Angabe von x und y Koordinate
	 * @param x x-Koordinate : float
	 * @param y y-Koordinate : float
	 */
	public Point(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * erstellt eine Kopie eines anderen Punktes
	 * @param p Punkt : Point
	 */
	public Point(Point p) {
		super();
		this.x = p.getX();
		this.y = p.getY();
	}
	
	/**
	 * gibt den Wert der x-Koordinate zurück
	 * @return x-Koordinate
	 */
	public float getX() {
		return x;
	}

	/**
	 * setzt Wert der x-Koordinate
	 * @param x neue x-Koordinate : float
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * gibt Wert der y-Koordinate zurück
	 * @return y-Koordinate : float
	 */
	public float getY() {
		return y;
	}

	/**
	 * setzt Wert der y-Koordinate
	 * @param y neue y-Koordinate : float
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * rechnet Werte auf den Punkt drauf
	 * @param x Addition auf X : float
	 * @param y Addition auf Y : float
	 */
	public void translate(float x, float y) {
		this.x += x;
		this.y += y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	/**
	 * vergleicht einen anderen Punkt mit diesem
	 * @param p Punkt zum vergleichen : Point
	 * @return true wenn die Punkte gleich sind, false wenn nicht
	 */
	public boolean equals(Point p){
		
		return (this.x == p.getX() && this.y == p.getY());
	}

	/**
	 * setzt X und Y
	 * @param x neuer x-Wert : float
	 * @param y neuer y-Wert : float
	 */
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * gibt Distanz zu einem anderen Punkt zurück
	 * @param other anderer Punkt : Point
	 * @return die Distanz zum anderen Punkt
	 */
	public float PointDistance(Point other)
	{
		return (float) Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2)); 
	}
}