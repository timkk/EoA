package de.bib.pbg2h15a;

import java.awt.Point;

public class Stage {

	/**
	 * @author pbg2h15azu
	 */
	
	private GameObject[][] fields;
	private float timeMax;
	private StageType type;
	private Point[] spawns;
	private int rundenAnzahl;
	private Mode mode;
	
	public Stage(GameObject[][] fields, float timeMax, StageType type, Point[] spawns, int rundenAnzahl, Mode mode) {
		super();
		this.fields = fields;
		this.timeMax = timeMax;
		this.type = type;
		this.spawns = spawns;
		this.rundenAnzahl = rundenAnzahl;
		this.mode = mode;
	}

	public GameObject[][] getFields() {
		return fields;
	}
	
	public Object moveObject(GameObject object, int richtung){
		
		return null;
	}
	
	
}
