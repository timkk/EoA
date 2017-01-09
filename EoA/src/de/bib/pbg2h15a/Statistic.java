package de.bib.pbg2h15a;

public class Statistic {

	private int points;
	private int placedBombs;
	private int killedEnemies;
	private int selfkills;
	private int powerUpPickUpCount;
	private int illnessPickUpCount;
	private int illnessTransferCount;

	/**
	 * @author pbg2h15akl
	 */
	public void Statisctis() {
		this.points = 0;
		this.placedBombs = 0;
		this.killedEnemies = 0;
		this.selfkills = 0;
		this.powerUpPickUpCount = 0;
		this.illnessPickUpCount = 0;
		this.illnessTransferCount = 0;
	}

	/**	 
	 * @return Get Current points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * 
	 * @param points Set the current points
	 */
	public void setPoints(int points) {
		this.points = points;
	}


	/**
	 * 
	 * @return Returns the current amount of placed Bombs.
	 */
	public int getPlacedBombs() {
		return placedBombs;
	}

	/**
	 * 
	 * @param placedBombs Set the current amount of placed Bombs.
	 */
	public void setPlacedBombs(int placedBombs) {
		this.placedBombs = placedBombs;
	}

	/**
	 * 
	 * @return Get the amount of killed enemies
	 */
	public int getKilledEnemies() {
		return killedEnemies;
	}

	/**
	 * 
	 * @param placedBombs Set the current amount of killed enemies.
	 */
	public void setKilledEnemies(int killedEnemies) {
		this.killedEnemies = killedEnemies;
	}

	/**
	 * 
	 * @return Get the current amount of selfkills
	 */
	public int getSelfkills() {
		return selfkills;
	}

	/**
	 * 
	 * @param selfkills Set the current amount of selfkills
	 */
	public void setSelfkills(int selfkills) {
		this.selfkills = selfkills;
	}

	/**
	 * 
	 * @return Gets the current amount of picked up power-ups
	 */
	public int getPowerUpPickUpCount() {
		return powerUpPickUpCount;
	}

	/**
	 * 
	 * Set the amount of picked up power-ups
	 */
	public void setPowerUpPickUpCount(int powerUpPickUpCount) {
		this.powerUpPickUpCount = powerUpPickUpCount;
	}

	/**
	 * 
	 * @return Get the current amount of picked up illnesses
	 */
	public int getIllnessPickUpCount() {
		return illnessPickUpCount;
	}

	/**
	 * 
	 * Set the current amount of picked up illnesses
	 */
	public void setIllnessPickUpCount(int illnessPickUpCount) {
		this.illnessPickUpCount = illnessPickUpCount;
	}

	/**
	 * 
	 * @return Get the current amount of transfered illnesses
	 */
	public int getIllnessTransferCount() {
		return illnessTransferCount;
	}

	/**
	 * Set the current amount of transfered illnesses
	 */
	public void setIllnessTransferCount(int illnessTransferCount) {
		this.illnessTransferCount = illnessTransferCount;
	}

	/**
	 * @author pbg2h15asu
	 * @param statistic that will be added to this one
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
