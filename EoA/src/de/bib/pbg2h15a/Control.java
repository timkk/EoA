package de.bib.pbg2h15a;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Control {
	
	private GameObject gameobject;
	private GUI gui;
	
	public Control(GameObject gameobject,GUI gui) {
		this.gameobject = gameobject;
		this.gui=gui;
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
		
		
		/**
		 * @author pbd2h15aho
		 * Einbindung der GUI Buttons;
		**/
		
		gui.getiBtnUp().addListener(new InputListener() {	
			@Override
			public boolean handle(Event event) {
				if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
					p.translate(0,1);
					gameobject.setPos(p);
					return true;
				}
				return false;
			}
		});
		
		gui.getiBtnDown().addListener(new InputListener() {	
			@Override
			public boolean handle(Event event) {
				if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
					p.translate(0,-1);
					gameobject.setPos(p);
					return true;
				}
				return false;
			}
		});
		
		gui.getiBtnRight().addListener(new InputListener() {	
			@Override
			public boolean handle(Event event) {
				if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
					p.translate(1, 0);
					gameobject.setPos(p);
					return true;
				}
				return false;
			}
		});
		
		gui.getiBtnLeft().addListener(new InputListener() {	
			@Override
			public boolean handle(Event event) {
				if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
					p.translate(-1, 0);
					gameobject.setPos(p);
					return true;
				}
				return false;
			}
		});
				
		
	}
	
	/**
	 * @author pbg2h15are
	 * @return Place a bomb with the Method bombeLegen() of the Type Bomb
	 */
	public void placeBomb(Bomb bombe){
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
			bombe.dropBomb();
		}
		
	/**
	* @author pbd2h15aho
	* Einbindung der GUI Buttons;
	**/
		
		gui.getiBtnBomb().addListener(new InputListener() {	
			@Override	
			public boolean handle(Event event) {
				if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
					bombe.dropBomb();
					return true;
				}
				return false;
			}
		});
		
	}
}
