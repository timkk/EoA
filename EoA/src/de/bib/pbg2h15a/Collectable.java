package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;

public abstract class Collectable extends GameObject {

	public Collectable(Point p) {
		super(p, true, null);
	}
}