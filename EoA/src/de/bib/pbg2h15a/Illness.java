package de.bib.pbg2h15a;

public class Illness{

	/**
	 * @author pbg2h15ary
	 * @author pbg2h15ake
	 * @author pbg2h15asu
	 */
	/**
	 * represents an Illness on a Player
	 */
	private Player player;
	private int orgValue;
	private int key;
	private boolean rndDrop = false;
	public float time;
	
	public Illness(Player player){
		this.player = player;
		setRndmIllnes();
		this.time = (float) ((Math.random() * 6) + 5);
		
	}
	
	private void setRndmIllnes(){
		//Zufallszahl um eine der 6 Krankheiten auszusuchen
		int zufallszahl = (int)(Math.random()*5) + 0;
		
		this.key = zufallszahl;
		
		illnessSet();
	}

	public boolean illnessExpired(){
		if(time <= 0)
		{
			undoIllness();
			player.setIllness(null);
			return true;
		}
		
		return false;
	}

	/**
	 * @author pbg2h15ake
	 * @author pbg2h15asu
	 */
	private void illnessSet(){
		
		switch(key)
		{
			//Explosionsradius minimum
			case 0:
				this.orgValue = player.getBombRadius();
				player.setBombRadius(IllnessType.EXPLOSIONSRADIUS_VERKLEINERN.getValue());
				break;
			
			//Bombe zufällig legen
			case 1:
				this.rndDrop = true;
				break;
			
			//Bomben legen deaktivieren
			case 2:
				this.orgValue = player.getAnzahlBombenMax();
				player.setAnzahlBombenMax(IllnessType.BOMBEN_DEAKTIVIEREN.getValue());;
				break;
			
			//Geschwindigkeitserhöhung
			case 3:
				this.orgValue = player.getMoveSpeed();
				player.setMoveSpeed(IllnessType.GESCHWINDIGKEITSERHÖHUNG.getValue());
				break;
			
			//Geschwindigkeitsverringerung
			case 4:
				this.orgValue = player.getMoveSpeed();
				player.setMoveSpeed(IllnessType.GESCHWINDIGKEITSVERRINGERUNG.getValue());
				break;
			
			//Invertierte Steuerung
			case 5:
				this.orgValue = player.getMoveSpeed();
				player.setMoveSpeed(player.getMoveSpeed() * IllnessType.STEUERUNG_INVERTIERT.getValue());
				break;
		}
	}
	
	/**
	 * @author pbg2h15asu
	 */
	private void undoIllness(){

		switch(key)
		{
			//Explosionsradius minimum
			case 0:
				player.setBombRadius(this.orgValue);
				break;
			
			//Bombe zufällig legen
			case 1:
				this.rndDrop = false;
				break;
			
			//Bomben legen deaktivieren
			case 2:
				player.setAnzahlBombenMax(this.orgValue);
				break;
			
			//Geschwindigkeitserhöhung
			case 3:
			
			//Geschwindigkeitsverringerung
			case 4:
			
			//Invertierte Steuerung
			case 5:
				player.setMoveSpeed(this.orgValue);
				break;
		}
	}

	/**
	 * @author pbg2h15asu
	 * @author pbg2h15awi
	 * @param dt
	 */
	public void update(float dt) {
		time -= dt;
		if(this.rndDrop && player.getAnzahlBomben() < player.getAnzahlBombenMax()){
			int rnd = (int) Math.random() * 100;
			if(rnd == 0){
				LocalGameState lgs = (LocalGameState)GameStateManager.gameState;
				lgs.newBomb(player.dropBomb());
			}
		}
	}
}