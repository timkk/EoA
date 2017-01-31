package de.bib.pbg2h15a;

public enum KIStates {
	
	/**
	 * @author pbg2h15aza
	 * @author pbg2h15asu
	 */
	
	WALK_STATE(0),
	ESPACE_STATE(1),
	CHECK_STATE(2),
	ATTACK_STATE(3),
	GOTO_STATE(4);
	
	private int value;
	
	private KIStates(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	
}
