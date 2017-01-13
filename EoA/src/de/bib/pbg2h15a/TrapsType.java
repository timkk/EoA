package de.bib.pbg2h15a;

public enum TrapsType {
	
	/**
	 * @author pbg2h15ake
	 * @author pbg2h15are
	 */
	
	STORMTRAP("Windfalle",5),
	ICETRAP("Eisfalle",(int)Math.random()*10);
	
	private String bezeichnung;
	private int value ;
	
	private TrapsType(String bezeichnung, int value) {
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
