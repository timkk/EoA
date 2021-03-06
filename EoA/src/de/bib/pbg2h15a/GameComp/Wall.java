package de.bib.pbg2h15a.GameComp;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.bib.pbg2h15a.Uitl.Point;

public class Wall extends GameObject {

	/**
	 * @author pbg2h15afa
	 * @author pbg2h15ary
	 * //Wahrscheinlichkeit von Collectable spawn
	 */
	int probability  = 60 ; 
	int probSickness = 90;	
	int probTrap = 95;
			
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
		
		/**
		 * @author pbg2h15ary
		 * "W�rfelt" ob ein Collectable gespawnt wird
		 */
		
		int zufallsZahl = (int) (1 + Math.random() * 99);
		Collectable collect = null;

		if (zufallsZahl < probability) {
			collect = null;
		}else if (zufallsZahl >= probability && zufallsZahl < probSickness ){
			collect = new PowerUp(pos);

		}
		else if(zufallsZahl >= probSickness && zufallsZahl < probTrap){
			if((int) (Math.random() * 2)== 0)
				collect = new FireTrap(pos);
			else{
				collect = new WindTrap(pos);
			}
		}
//		else if (zufallsZahl > 85 && zufallsZahl < 95) {
//			int debuffZufall = (int) (6 + Math.random() * 3);
//			 collect = new PowerUp();
//			 ((PowerUp) collect).setType(PoerUpType.values[debuffZufall]);
//		}
		else {
			 collect = new Illness(pos);
		}

		return collect;

	}

}

