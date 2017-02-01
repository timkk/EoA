package de.bib.pbg2h15a;

/**
 * @author pbg2h15are
 * @author pbg2h15asu
 * Enthält alle Arten von Krankheiten mit ihrem Namen und einem Wert 
 * (Kommentiert von Felix Kellmereit pbg2h15ake)
 * 
 */
public enum IllnessType {
	
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