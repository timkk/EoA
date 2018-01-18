package de.bib.pbg2h15a;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Übergeordnete, abstrakte Klasse von denen alle im Spiel vorkommenden Objekte erben
 * (Kommentiert von Stefan Niesel / pbg2h15ani)
 * @author pbg2h15arm
 */
public abstract class GameObject {

	protected Point pos;
	protected boolean passable;
	protected Texture spritesheet;
	protected Animation animation;
	
	/**
	 * Erstellt ein GameObject
	 * @param pos Pixelkoordinaten auf dem Spielfeld
	 * @param passable Legt fest, ob das Element durchlässig ist
	 * @param spritesheet Legt die Textur fest
	 */	
	public GameObject(Point pos, boolean passable, Texture spritesheet){
		this.pos=pos;
		this.passable=passable;
		this.spritesheet=spritesheet;
	}
	
	/**
	 * Abstrakte Render Methode, die mit einem SpriteBatch befüllt werden muss
	 * @param sb Legt das SpriteBatch fest
	 */
	public abstract void render(SpriteBatch sb);
	
	/**
	 * Abstrakte Update Methode, die mit der deltaTime befüllt wird
	 * @param dt Legt die Zeit fest
	 */
	public abstract void update(float dt);

	public Point getPos() {
		return pos;
	}
	
	public boolean isPassable(){
		return passable;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public Animation getAnimation() {
		return animation;
	}

	public Texture getSpritesheet() {
		return this.spritesheet;
	}
}