package de.bib.pbg2h15a;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Control {
	
	private GameObject gameobject;
	
	public Control(GameObject gameobject) {
		this.gameobject = gameobject;
	}

	/**
	 * @author pbg2h15are
	 * @return change the Position of the Player
	 */
	public void  movement(){
		Point p = gameobject.getPos();
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			p.translate(-1, 0);
			gameobject.setPos(p);
		}else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			p.translate(1, 0);
			gameobject.setPos(p);
		}else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			p.translate(0,1);
			gameobject.setPos(p);
		}else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			p.translate(0,-1);
			gameobject.setPos(p);
		}
	}
	
	/**
	 * @author pbg2h15are
	 * @return Place a bomb with the Method bombeLegen() of the Type Bomb
	 */
	public void placeBomb(Bomb bombe){
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
			bombe.dropBomb();
		}
	}
}
