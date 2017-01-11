package de.bib.pbg2h15a;

public class Illness {

	/**
	 * @author pbg2h15ary/pbg2h15ake
	 */
	/**
	 * Represents all illnesses and their effects
	 */
	private IllnessType type;
	private Player player;
	private Bomb bomb;
	private IllnessType[] illness;
	private int key;
	public float time;
	public Timer timer = new Timer(10);
	
	
	public Illness(){
		type = randIllnessType();
	}
	
	public IllnessType getType(){
		return type;
	}
	
	public void setType(IllnessType type){
		this.type = type;
	}
	
	private IllnessType randIllnessType(){
		//Zufallszahl um eine der 5 Krankheiten auszusuchen
		int zufallszahl = (int)(Math.random()*4) + 0;
		
		//Array sollte mit allen Krankheitstypen gefüllt werden
		illness[0] = null;
		
		key = zufallszahl;
		
		return illness[zufallszahl];
	}

	private boolean illnessExpired(){
		boolean expired = false;
		if(timer.isFinished()==true)
		{
			player.setIllness(null);
			expired = true;
		}
		
		return expired;
	}

	private void illnessSet(){
		switch(key)
		{
			//Explosionsradius minimum
			case 0: bomb.setRadius(1);
					break;
			
			//Bombe zufällig legen
			case 1:  ;
					break;
			
			//Bomben legen deaktivieren
			case 2:  ;
					break;
			
			//Geschwindigkeitserhöhung
			case 3:  ;
					break;
			
			//Geschwindigkeitsverringerung
			case 4:  ;
					break;
		}
	}
}