package de.bib.pbg2h15a;

import java.util.Random;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Wall extends GameObject {

	/**
	 * @author pbg2h15afa
	 * 
	 */

	
	private Collectable content;

	public Wall(int x, int y) {
		super(new Point(x, y), false, null);
		this.content = generateCollectable();
		this.spritesheet = new Texture("img/Stage_1/Kiste.png");

	}
	
	/**
	 * @author pbg2h15asu
	 * @param p Position
	 */
	public Wall(Point p){
		super(p, false, null);
		this.content = generateCollectable();
		this.spritesheet = new Texture("img/Stage_1/Kiste.png");
	}

	public Collectable getContent() {
		return content;

	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		sb.draw(this.getSpritesheet(), this.getPos().getX(), this.getPos().getY());
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub

	}

	private Collectable generateCollectable() {

		int zufallsZahl = (int) (1 + Math.random() * 99);
		Collectable collect = null;

		if (zufallsZahl < 50) {
			collect = null;
		}
		if (zufallsZahl > 50 && zufallsZahl < 85) {
			int powerUpZufall = (int) (Math.random() * 6);
			// collect = new PowerUp();
			// ((PowerUp) collect).setType(PoerUpType.values[PowerUpZufall]);

		} else if (zufallsZahl > 85 && zufallsZahl < 95) {
			int debuffZufall = (int) (6 + Math.random() * 3);
			// collect = new PowerUp();
			// ((PowerUp) collect).setType(PoerUpType.values[debuffZufall]);
		} else {
			// collect = new Illness();
		}

		return collect;

	}

}

