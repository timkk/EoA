package de.bib.pbg2h15a.GameComp;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.bib.pbg2h15a.Uitl.Point;

	/**Explosion dient der Erstellung eines Explosions Objektes
 	* 
 	* @author pbg2h15azu
 	* @author pbd2h15aho
 	* Kommentiert von pbd2h15aho Franziska Hoffmann
 	**/

public class Explosion extends GameObject {
	
	/**Dauer der Explosion*/
	private float time = 1.5f;
	
	/**Enum Element mit den zur Explosion gehoerigen Texturen.*/
	private Explosion_Animation expgr;
	
	/**Player, der die Bombe gelegt und die Explosion ausgeloest hat.*/
	private Player player;
	
	
	/** Konstruktor fuer ein Explosions Objekt
	 * 
	 * @author pbg2h15azu
	 * @param player	Referenz auf den Player, der die Bombe gelegt und die Explosion ausgeloest hat.	 
	 * @param pos	Referenz auf die Position der Explosion.
	 * @param frameSet	Referenz auf das Enum mit den zur Explosion gehoerigen Texturen.
	 */
	public Explosion(Player player, Point pos, Explosion_Animation frameSet) {
		super(pos, true, frameSet.getFrame(0));
		this.expgr = frameSet;
		this.player = player;
	}
	
	
	/** Konstruktor fuer ein Explosions Objekt
	 * 
	 * @author pbg2h15azu
	 * @param player	Referenz auf den Player, der die Bombe gelegt und die Explosion ausgeloest hat.	 
	 * @param frameSet	Referenz auf das Enum mit den zur Explosion gehoerigen Texturen.
	 */
	public Explosion(Point pos, Explosion_Animation frameSet) {
		super(pos, true, frameSet.getFrame(0));
		this.expgr = frameSet;
	}
	

	public Player getPlayer() {
		return player;
	}
	
	
	/** shouldRemove gibt zuruek ob die Explosion entfernt werden soll.
	 * 
	 * @author pbg2h15azu
	 * @return false wenn die Zeit groe�er gleich 0 ist.
	 * @return true wenn die Zeit kleine 0 ist.
	 **/
	public boolean shouldRemove(){
		return time <= 0;
	}
	
	
	/**setAniTexture ersetzt die Texture.
	 * 
	 * @author pbd2h15aho
	 * @param frames Referenz auf das Enum mit den Texturen.
	 * @param vers Position der Texture im Enum.
	 **/
	private void setAniTexture(Explosion_Animation frames,int vers){
		this.spritesheet=frames.getFrame(vers);
	}
	
	
	/** rendert die Texture
	 * 
	 * @author pbg2h15awi
	 */
	@Override
	public void render(SpriteBatch sb) {
		sb.draw(this.getSpritesheet(), this.getPos().getX(), this.getPos().getY());
	}

	
	/** update ersetzt nach einer bestimmten Zeit die Texture, sodass eine Animation entsteht. 
	 * 
	 * @author pbd2h15aho
	 */
	@Override
	public void update(float dt) {
		time -= dt;
		if(time>=0&&time<0.1){
			 setAniTexture(this.expgr, 0);
			}
			if(time>=0.1&&time<0.2){
				 setAniTexture(this.expgr,1);
				}
			if(time>=0.2&&time<0.3){
				 setAniTexture(this.expgr,2);
				}
			if(time>=0.3&&time<1.2){
				 setAniTexture(this.expgr,3);
				}
			if(time>=1.2&&time<1.3){
				 setAniTexture(this.expgr,2);
				}
			if(time>=1.3&&time<1.4){
				 setAniTexture(this.expgr,1);
				}
			if(time>=1.4){
				 setAniTexture(this.expgr, 0);
				}
	}

	public float getTime() {
		return time;
	}
	
}