package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Object der Klasse <code>Death</code> wird an die Todesstelle eines Spieler gesetzt.
 * 
 * Das Objekt ersetzt den Spieler wenn er von einer Bombenexplosion getroffn wird.
 * Mit dem Objekt wird eine Textur einer png-Datei übergeben was auf dem Spielfeld gerendert wird.
 * Dies ist ein Kindobjekt von <code>Gameobject</code>.
 * 
 * @author pbg2h15are
 * @version 1.0
 */

public class Death extends GameObject{
	
	/**
	 * Letzte Position des Spielers als Todesposition
	 * 
	 * @author pbg2h15are
	 */
	private Point pos;
	
	/**
	 * Textur für ein Zeichen der Position
	 * 
	 * @author pbg2h15are
	 */
	private static Texture deathSign = new Texture("img/Stage_1/deathP1.png");
	
	/**
	 * Intitialisiert ein Objekt der Klasse <code>Death</code>
	 * 
	 * Mit Übergabewert der letzten Position des Spielers bevor er von einer Bombenexplosion getroffen wird.
	 * Die Textur benutzt den Konstruktor der Klasse <code>Gameobject</code> und nimmt die png-Datei aus den Assets
	 * 
	 * @author pbg2h15are
	 * @param pos
	 */
	public Death(Point pos)
	{
		super(pos, true, deathSign);
		this.pos = pos;
	}
	
	/**
	 * In Bearbeitung!
	 * 
	 * Render-Methode für die <code>LocalGameState</code>
	 * 
	 * @author pbg2h15are
	 */
	@Override
	public void render(SpriteBatch sb) {
		// TODO Texture Rendern
		//sb.draw(deathSign, , y, originX, originY, width, height, scaleX, scaleY, rotation);
	}
	
	/**
	 * In Bearbeitung!
	 * 
	 * Update-Methode für die <code>LocalGameState</code>
	 * 
	 * @author pbg2h15are
	 */
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

}
