package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Collectable extends GameObject {

	public Collectable(int x, int y, boolean passable, Texture spritesheet) {
		super(new Point(x, y), true, null);
		// TODO Auto-generated constructor stub
	}

}

