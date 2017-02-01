package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;

/***
 * 
 * (kommentiert von Fortmeier)
 * @author pbg2h15afo,pbg2h15awi, pbg2h15ast
 * 
 */
public class WindTrap extends Illness{
/**
 * Konstruktor dür die WindTrap
 * @param pos die position der Falle
 */
	public WindTrap(Point pos) {
		super(pos);
		time = 3;
		this.spritesheet = new Texture("img/Stage_1/WindFalle.png");
	}
	/**
	 * Verhindert das der Spieler sich bewegen kann
	 */
	@Override
	public void illnessSet(Player p) {
		this.player = p;
		p.setPos(pos);
		this.orgValue = player.getMoveSpeed();
		player.setMoveSpeed(0);
	}
	/**
	 * gibt dem spieler seine alte bewegungsgeschwindigkeit
	 */
	@Override
	public void undoIllness() {
		player.setMoveSpeed(orgValue);
	}

}
