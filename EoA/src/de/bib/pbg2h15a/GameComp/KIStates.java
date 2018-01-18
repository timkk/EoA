package de.bib.pbg2h15a.GameComp;

/**
 * (Kommentiert von Julian Zameit / pbg2h15aza) Ein Enum mit den m�glichen
 * States der KI. Jede State enth�lt eine Nummer um sie zu identifizieren.
 * 
 * @author pbg2h15aza
 * @author pbg2h15asu
 */

public enum KIStates {

	WALK_STATE(0), ESCAPE_STATE(1), CHECK_STATE(2), ATTACK_STATE(3), GOTO_STATE(4);

	private int value;

	private KIStates(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
