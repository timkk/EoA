package de.bib.pbg2h15a;

public enum IllnessType {
	
	/**
	 * @author pbg2h15are
	 * Hold every IllnesType in the Game with a Name and Value
	 */
	
	EXPLOSIONSRADIUS_verkleinern("Explosionsradius verkleinern", 1),
	BOMBEN_ZUFAELLIG("Bomben zufällig legen", (int)Math.random()*10),
	BOMBEN_DEAKTIVIEREN("Bomben legen deaktivieren", 0),
	GESCHWINDIGKEITSERHÖHUNG("Geschwindigkeit erhöhen", 10),
	GESCHWINDIGKEITSVERRINGERUNG("Geschwindigkeit verringern", -10);
	
	private String bezeichnung;
	private int value;
	
	
	private IllnessType(String bezeichnung, int value) {
		this.bezeichnung = bezeichnung;
		this.value = value;
	}


	public String getBezeichnung() {
		return bezeichnung;
	}


	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}
	
	
	
}
