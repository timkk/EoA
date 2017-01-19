package de.bib.pbg2h15a;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class PlayerUIList {

	/**
	 * @author pbg2h15are
	 * @author pbg2h15aro
	 *yeahyeah
	 */
	
	private Vector<PlayerUI> list;
	private Skin skinName;
	private int playerAmount;
	
	TextField[] txtFields;
	
	
	public PlayerUIList(int playerAmount) {
		this.playerAmount = playerAmount;
		this.skinName = new Skin(Gdx.files.internal("img/GamePrepare/uiskin.json"));
		initTxtFields(playerAmount);
		init();
	}

	private void initTxtFields(int pA){
		for (int i = 0; i < pA; i++) {
			txtFields[i] = new TextField("", skinName);
		}
	}
	
	private void init(){
		for (int i = playerAmount; i > 0; i--) {
			
			txtFields[playerAmount-i].setPosition((Gdx.graphics.getWidth() / 2) + 5, (Gdx.graphics.getHeight()-(Gdx.graphics.getHeight() / playerAmount)*i));
			txtFields[playerAmount-i].setSize(200, 50);
			
			list.add(new PlayerUI(new ButtonErstellen((Gdx.graphics.getWidth() / 2) - 225, (Gdx.graphics.getHeight()-(Gdx.graphics.getHeight() / playerAmount)*i), "img/GamePrepare/name.png"),
					txtFields[playerAmount-i]));
		}
	}

	/**
	 * @return the list
	 */
	public Vector<PlayerUI> getList() {
		return list;
	}


	/**
	 * @param list the list to set
	 */
	public void setList(Vector<PlayerUI> list) {
		this.list = list;
	}


	/**
	 * @return the skinName
	 */
	public Skin getSkinName() {
		return skinName;
	}


	/**
	 * @param skinName the skinName to set
	 */
	public void setSkinName(Skin skinName) {
		this.skinName = skinName;
	}


	/**
	 * @return the playerAmount
	 */
	public int getPlayerAmount() {
		return playerAmount;
	}


	/**
	 * @param playerAmount the playerAmount to set
	 */
	public void setPlayerAmount(int playerAmount) {
		this.playerAmount = playerAmount;
	}
	
	
}
