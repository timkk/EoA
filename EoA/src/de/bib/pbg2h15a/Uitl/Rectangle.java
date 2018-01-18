package de.bib.pbg2h15a;

public class Rectangle {

/**
 * @author pbg2h15asu
 * speichert ein Rechteck mit Koordinaten und Seitenlängen
 * Kommentare: Michael Surmund - pbg2h15asu
 */
	
	private float x;
	private float y;
	private float width;
	private float height;

	/**
	 * legt eine neues Rechteck an
	 * @param x x-Koordinate : float
	 * @param y y-Koordinate : float
	 * @param width Breite : float
	 * @param height Höhe : float
	 */
	public Rectangle(float x, float y, float width, float height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * gibt Wert der x-Koordinate zurück
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
	 * @return y-Koordinate
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
	 * gibt Wert der Breite zurück
	 * @return Breite : float
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * setzt Wert der Breite
	 * @param width : float
	 */
	public void setWidth(float width) {
		this.width = width;
	}
	
	/**
	 * gibt Wert der x-Koordinate zurück
	 * @return Höhe : float
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * setzt Wert der Höhe
	 * @param height neue Höhe : float
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Rectangle [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
	}
}