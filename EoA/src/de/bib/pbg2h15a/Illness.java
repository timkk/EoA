package de.bib.pbg2h15a;

public class Illness {

	/**
	 * @author pbg2h15ary
	 */
	private IllnessType type;
	public float time;
	public Timer timer = new Timer(0);
	
	public Illness()
	{
		randIllnessType();
		
	}
	
	public IllnessType getType()
	{
	
		return type;
	}
	
	public void setType(IllnessType type)
	{
		this.type = type;
	}
	
	private void randIllnessType()
	{
		
	}

}
