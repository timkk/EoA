package de.bib.pbg2h15a;

public class Timer {

	private float time; 
	
	/**
	 * @param Amount of time; deltaTime will be subtracted from time. 
	 * @author pbg2h15akl
	 */
	public Timer(float time)
	{
		this.time = time;
	}
	
	/** 
	 * @param dt(DeltaTime) is given from the Main-Update.
	 */
	public void update(float dt)
	{
		this.time -= dt;
	}
	
	/**
	 * @return Returns if timer is expired.
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
