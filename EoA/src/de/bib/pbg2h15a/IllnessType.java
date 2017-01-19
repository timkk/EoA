package de.bib.pbg2h15a;

public enum IllnessType {
	
	/**
	 * @author pbg2h15are
	 * @author pbg2h15asu
	 * Hold every IllnesType in the Game with a Name and Value
	 */
	
	EXPLOSIONSRADIUS_VERKLEINERN("Explosionsradius verkleinern", 1),
	BOMBEN_ZUFAELLIG("Bomben zufällig legen", (int)Math.random()*10),
	BOMBEN_DEAKTIVIEREN("Bomben legen deaktivieren", 0),
	GESCHWINDIGKEITSERHÖHUNG("Geschwindigkeit erhöhen", 10),
	GESCHWINDIGKEITSVERRINGERUNG("Geschwindigkeit verringern", 1),
	STEUERUNG_INVERTIERT("Steuerung Invertiert", -1);
	
	private String bezeichnung;
	private int value;
	
	private IllnessType(String bezeichnung, int value) {
		this.bezeichnung = bezeichnung;
		this.value = value;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}
	
	public int getValue() {
		return value;
	}
}