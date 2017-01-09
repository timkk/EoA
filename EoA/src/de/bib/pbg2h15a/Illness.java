package de.bib.pbg2h15a;

public class Illness {

	/**
	 * @author pbg2h15ary/pbg2h15ake
	 */
	private IllnessType type;
	private Player player;
	public float time;
	public Timer timer = new Timer(10);
	
	public Illness()
	{
		type = randIllnessType();
	}
	
	public IllnessType getType()
	{
		return type;
	}
	
	public void setType(IllnessType type)
	{
		this.type = type;
	}
	
	private IllnessType randIllnessType()
	{
		//Zufallszahl um eine der 5 Krankheiten auszusuchen
		int zufallszahl = (int)(Math.random()*4) + 0;
		
		//Array sollte mit allen Krankheitstypen gefüllt werden
		IllnessType Illnesses[] = null;
		
		Illnesses[0] = null;
		
		return Illnesses[zufallszahl];
	}
	
	private boolean illnessExpired()
	{
		boolean expired = false;
		if(timer.isFinished()==true)
		{
			player.setIllness(null);
			expired = true;
		}
		
		return expired;
	}
	
	private void illnessReset()
	{
		//Switch-case für jede Art Illness.
		//Alle Eigenschaften des Spielers müssen abhängig von der Krankheit
		//geresetet werden !
	}
}
