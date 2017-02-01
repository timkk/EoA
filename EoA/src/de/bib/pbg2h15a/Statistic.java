package de.bib.pbg2h15a;

/**
 * Statistikklasse zur Erfassung der Statistiken
 * @author pbg2h15akl
 * 
 */

public class Statistic {

	private int points;
	private int placedBombs;
	private int killedEnemies;
	private int selfkills;
	private int powerUpPickUpCount;
	private int illnessPickUpCount;
	private int illnessTransferCount;

	
	/**
	 * Initialisiert eine leere Statistikklasse
	 */
	
	public Statistic() {
		this.points = 0;
		this.placedBombs = 0;
		this.killedEnemies = 0;
		this.selfkills = 0;
		this.powerUpPickUpCount = 0;
		this.illnessPickUpCount = 0;
		this.illnessTransferCount = 0;
	}


	public int getPoints() {
		return points;
	}


	public void setPoints(int points) {
		this.points = points;
	}


	public int getPlacedBombs() {
		return placedBombs;
	}


	public void setPlacedBombs(int placedBombs) {
		this.placedBombs = placedBombs;
	}


	public int getKilledEnemies() {
		return killedEnemies;
	}


	public void setKilledEnemies(int killedEnemies) {
		this.killedEnemies = killedEnemies;
	}


	public int getSelfkills() {
		return selfkills;
	}


	public void setSelfkills(int selfkills) {
		this.selfkills = selfkills;
	}


	public int getPowerUpPickUpCount() {
		return powerUpPickUpCount;
	}


	public void setPowerUpPickUpCount(int powerUpPickUpCount) {
		this.powerUpPickUpCount = powerUpPickUpCount;
	}


	public int getIllnessPickUpCount() {
		return illnessPickUpCount;
	}


	public void setIllnessPickUpCount(int illnessPickUpCount) {
		this.illnessPickUpCount = illnessPickUpCount;
	}


	public int getIllnessTransferCount() {
		return illnessTransferCount;
	}


	public void setIllnessTransferCount(int illnessTransferCount) {
		this.illnessTransferCount = illnessTransferCount;
	}

	/**
	 * Hängt Statistiken an vorhandene an, z.B.: Ergebnisse von Runde 1 und Runde 2 werden zusammengefasst.
	 * @author pbg2h15asu
	 * @param s Statistik, die hinzugefügt wird, Werte werden übernommen
	 */
	
	public void addStatistic(Statistic s) {
		this.points += s.points;
		this.selfkills += s.selfkills;
		this.powerUpPickUpCount += s.powerUpPickUpCount;
		this.placedBombs += s.placedBombs;
		this.killedEnemies += s.killedEnemies;
		this.illnessTransferCount += s.illnessTransferCount;
		this.illnessPickUpCount += s.illnessPickUpCount;
	}
}
