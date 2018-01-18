package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;
	
	/**Das Enum Explosion_Frames beinhaltet die Texturen  für die Animation der Explosion. 
 	* 
 	* @author pbd2h15aho
 	* Kommentiert von pbd2h15aho Franziska Hoffmann
 	**/
public enum Explosion_Animation {
	
	EXPLOSION_CENTER("img/Stage_1/ExplodeAni/exp_center1.png",	"img/Stage_1/ExplodeAni/exp_center2.png",	"img/Stage_1/ExplodeAni/exp_center3.png",	"img/Stage_1/ExplodeAni/exp_center4.png"),
	EXPLOSION_TOP(	"img/Stage_1/ExplodeAni/exp_top1.png",		"img/Stage_1/ExplodeAni/exp_top2.png",		"img/Stage_1/ExplodeAni/exp_top3.png",		"img/Stage_1/ExplodeAni/exp_top4.png"),
	EXPLOSION_BOTTOM("img/Stage_1/ExplodeAni/exp_bot1.png",		"img/Stage_1/ExplodeAni/exp_bot2.png",		"img/Stage_1/ExplodeAni/exp_bot3.png",		"img/Stage_1/ExplodeAni/exp_bot4.png"),
	EXPLOSION_LEFT(	"img/Stage_1/ExplodeAni/exp_left1.png",		"img/Stage_1/ExplodeAni/exp_left2.png",		"img/Stage_1/ExplodeAni/exp_left3.png",		"img/Stage_1/ExplodeAni/exp_left4.png"),
	EXPLOSION_RIGHT("img/Stage_1/ExplodeAni/exp_right1.png",	"img/Stage_1/ExplodeAni/exp_right2.png",	"img/Stage_1/ExplodeAni/exp_right3.png",	"img/Stage_1/ExplodeAni/exp_right4.png"),
	EXPLOSION_VERT(	"img/Stage_1/ExplodeAni/exp_ver1.png",		"img/Stage_1/ExplodeAni/exp_ver2.png",		"img/Stage_1/ExplodeAni/exp_ver3.png",		"img/Stage_1/ExplodeAni/exp_ver4.png"),
	EXPLOSION_HORI(	"img/Stage_1/ExplodeAni/exp_hor1.png",		"img/Stage_1/ExplodeAni/exp_hor2.png",		"img/Stage_1/ExplodeAni/exp_hor3.png",		"img/Stage_1/ExplodeAni/exp_hor4.png");
	
	private Texture frame1;
	private Texture frame2;
	private Texture frame3;
	private Texture frame4;
	
	/** Konstruktor fuer das Enum Explosion_Frames
	 * 
	 * @param path1 Dateipfad der ersten Texture
	 * @param path2	Dateipfad der zweiten Texture
	 * @param path3 Dateipfad der dritten Texture
	 * @param path4 Dateipfad der vierten Texture
	 * 
	 **/
	private Explosion_Animation(String path1,String path2,String path3,String path4){
		this.frame1=new Texture(path1);
		this.frame2=new Texture(path2);
		this.frame3=new Texture(path3);
		this.frame4=new Texture(path4);
	}
	
	/** getFrame gibt die Texture des angegebenen index wieder 
	 * 
	 * @param index Indexnummer des frames 
	 * @return Texture des angegebenen index
	 **/
	public Texture getFrame(int vers) {
		switch (vers){
		case 0:return frame1;
		case 1:return frame2;
		case 2:return frame3;
		case 3:return frame4;
		default:return null;
		}
	}
	
}
