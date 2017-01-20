package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author pbd2h15aho
 * */

public enum Player_Frames {
	P1_MV_UP	("img/Stage_1/Sprites/P1Back1.png"	,"img/Stage_1/Sprites/P1Back2.png"	,"img/Stage_1/Sprites/P1Back3.png"),
	P2_MV_UP	("img/Stage_1/Sprites/P2Back1.png"	,"img/Stage_1/Sprites/P2Back2.png"	,"img/Stage_1/Sprites/P2Back3.png"),
	P3_MV_UP	("img/Stage_1/Sprites/P3Back1.png"	,"img/Stage_1/Sprites/P3Back2.png"	,"img/Stage_1/Sprites/P3Back3.png"),
	P4_MV_UP	("img/Stage_1/Sprites/P4Back1.png"	,"img/Stage_1/Sprites/P4Back2.png"	,"img/Stage_1/Sprites/P4Back3.png"),
	P1_MV_DOWN	("img/Stage_1/Sprites/P1Front1.png"	,"img/Stage_1/Sprites/P1Front2.png"	,"img/Stage_1/Sprites/P1Front3.png"),
	P2_MV_DOWN	("img/Stage_1/Sprites/P2Front1.png"	,"img/Stage_1/Sprites/P2Front2.png"	,"img/Stage_1/Sprites/P2Front3.png"),
	P3_MV_DOWN	("img/Stage_1/Sprites/P3Front1.png"	,"img/Stage_1/Sprites/P3Front2.png"	,"img/Stage_1/Sprites/P3Front3.png"),
	P4_MV_DOWN	("img/Stage_1/Sprites/P4Front1.png"	,"img/Stage_1/Sprites/P4Front2.png"	,"img/Stage_1/Sprites/P4Front3.png"),
	P1_MV_LEFT	("img/Stage_1/Sprites/P1Left1.png"	,"img/Stage_1/Sprites/P1Left2.png"	,"img/Stage_1/Sprites/P1Left3.png"),
	P2_MV_LEFT	("img/Stage_1/Sprites/P2Left1.png"	,"img/Stage_1/Sprites/P2Left2.png"	,"img/Stage_1/Sprites/P2Left3.png"),
	P3_MV_LEFT	("img/Stage_1/Sprites/P3Left1.png"	,"img/Stage_1/Sprites/P3Left2.png"	,"img/Stage_1/Sprites/P3Left3.png"),
	P4_MV_LEFT	("img/Stage_1/Sprites/P4Left1.png"	,"img/Stage_1/Sprites/P4Left2.png"	,"img/Stage_1/Sprites/P4Left3.png"),
	P1_MV_RIGHT("img/Stage_1/Sprites/P1Right1.png"	,"img/Stage_1/Sprites/P1Right2.png"	,"img/Stage_1/Sprites/P1Right3.png"),
	P2_MV_RIGHT("img/Stage_1/Sprites/P2Right1.png"	,"img/Stage_1/Sprites/P2Right2.png"	,"img/Stage_1/Sprites/P2Right3.png"),
	P3_MV_RIGHT("img/Stage_1/Sprites/P3Right1.png"	,"img/Stage_1/Sprites/P3Right2.png"	,"img/Stage_1/Sprites/P3Right3.png"),
	P4_MV_RIGHT("img/Stage_1/Sprites/P4Right1.png"	,"img/Stage_1/Sprites/P4Right2.png"	,"img/Stage_1/Sprites/P4Right3.png");
	
	private Texture frame1;
	private Texture frame2;
	private Texture frame3;
	
	private Player_Frames(String tx1,String tx2,String tx3){
		this.frame1=new Texture(tx1);
		this.frame2=new Texture(tx2);
		this.frame3=new Texture(tx3);
	}

	public Texture getFrame(int vers) {
		switch (vers){
		case 0:return frame1;
		case 1:return frame2;
		case 2:return frame3;
		default:return null;
		}
	}
}