package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;

public enum Explosion_Animation {
	
	/**
	 * @author pbd2h15aho
	 **/
	
	EXPLOSION_CENTER("img/Stage_1/ExplodeAni/exp_center1.png",	"img/Stage_1/ExplodeAni/exp_center2.png",	"img/Stage_1/ExplodeAni/exp_center3.png",	"img/Stage_1/ExplodeAni/exp_center4.png"),
	EXPLOSION_TOP(	"img/Stage_1/ExplodeAni/exp_top1.png",		"img/Stage_1/ExplodeAni/exp_top2.png",		"img/Stage_1/ExplodeAni/exp_top3.png",		"img/Stage_1/ExplodeAni/exp_top4.png"),
	EXPLOSION_BOTTOM("img/Stage_1/ExplodeAni/exp_bot1.png",		"img/Stage_1/ExplodeAni/exp_bot2.png",		"img/Stage_1/ExplodeAni/exp_bot3.png",		"img/Stage_1/ExplodeAni/exp_bot4.png"),
	EXPLOSION_LEFT(	"img/Stage_1/ExplodeAni/exp_left1.png",		"img/Stage_1/ExplodeAni/exp_left2.png",		"img/Stage_1/ExplodeAni/exp_left3.png",		"img/Stage_1/ExplodeAni/exp_left4.png"),
	EXPLOSION_RIGHT("img/Stage_1/ExplodeAni/exp_right1.png",	"img/Stage_1/ExplodeAni/exp_right2.png",	"img/Stage_1/ExplodeAni/exp_right3.png",	"img/Stage_1/ExplodeAni/exp_right4.png"),
	EXPLOSION_VERT(	"img/Stage_1/ExplodeAni/exp_ver1.png",		"img/Stage_1/ExplodeAni/exp_ver2.png",		"img/Stage_1/ExplodeAni/exp_ver3.png",		"img/Stage_1/ExplodeAni/exp_ver4.png"),
	EXPLOSION_HORI(	"img/Stage_1/ExplodeAni/exp_hor1.png",		"img/Stage_1/ExplodeAni/exp_hor2.png",		"img/Stage_1/ExplodeAni/exp_hor3.png",		"img/Stage_1/ExplodeAni/exp_hor4.png");
	
	private Texture tx1;
	private Texture tx2;
	private Texture tx3;
	private Texture tx4;
	private Explosion_Animation(String path1,String path2,String path3,String path4){
		this.tx1=new Texture(path1);
		this.tx2=new Texture(path2);
		this.tx3=new Texture(path3);
		this.tx4=new Texture(path4);
	}
	public Texture getTexture(int vers) {
		switch (vers){
		case 0:return tx1;
		case 1:return tx2;
		case 2:return tx3;
		case 3:return tx4;
		default:return null;
		}
	}
	
}
