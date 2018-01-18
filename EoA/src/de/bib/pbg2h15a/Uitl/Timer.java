package de.bib.pbg2h15a;

/**
 * Klasse Timer
 * @author pbg2h15akl
 * (Kommentiert von Stefan Niesel / pbg2h15ani)
 *
 */


public class Timer {

	private float time; 
	
	/**
	 * Erstellt einen ablaufenden Timer;
	 * @param time Zeit, die heruntergezählt wird
	 */
	public Timer(float time)
	{
		this.time = time;
	}
	
	/**Zeit, die durch die Update Methode abgezogen wird um die Zeit des Timers zu berechnen
	 * @param dt Update-Frequenz
	 */
	public void update(float dt)
	{
		this.time -= dt;
	}
	
	/**
	 * Abfrage, ob der Timer fertig ist
	 * @return Gibt true zurück, wenn der Timer abgelaufen ist
	 */
	public boolean isFinished()
	{
		return time <= 0;
	}

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return String.format("%f", (double) time);
	}

}