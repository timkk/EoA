package de.bib.pbg2h15a.Config;

/**
 * Helferklasse f�r den Input der Spieler.
 * H�lt die Keys(Tasten) f�r die Bewegung und das Bombenlegen fest
 * 
 * @author pbg2h15asu
 * @kommentiertVon pbg2h15aro
 */

public class InputConfig {

	private int left;
	private int right;
	private int up;
	private int down;
	private int bomb;
	
	/**
	 * 
	 * @param left move left
	 * @param right move right
	 * @param up move up
	 * @param down move down
	 * @param bomb place bomb
	 */
	
	public InputConfig(int left, int right, int up, int down, int bomb) {

		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
		this.bomb = bomb;
	}

	public int getKeyLeft() {
		return left;
	}

	public int getKeyRight() {
		return right;
	}

	public int getKeyUp() {
		return up;
	}

	public int getKeyDown() {
		return down;
	}

	public int getKeyBomb() {
		return bomb;
	}
}