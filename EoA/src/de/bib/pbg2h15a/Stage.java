package de.bib.pbg2h15a;

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
	
	/**
	 * @author pbg2h15are
	 * @return set Pillars on the Stage
	 */
	public void setStage(){
		
		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < fields[i].length; j++) {
				if(i < fields[i].length - 2 && j < fields.length - 2){
					if(i%2 == 0 && i != 0 && j%2 == 0  && j != 0){
						fields[i][j] = new Pillar(i, j);
					}
				}
			}
			
			fields[0][i] = new Pillar(0, i);
			fields[fields.length - 1][i] = new Pillar(fields.length - 1, i);
			
			if(i != 0 && i < fields.length - 2){
				fields[i][0] = new Pillar(i, 0);
				fields[i][fields[i].length - 1] = new Pillar(i, fields[i].length - 1);
			}
		}
		
	}
}