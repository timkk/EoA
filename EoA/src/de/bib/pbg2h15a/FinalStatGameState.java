package de.bib.pbg2h15a;

public class FinalStatGameState extends GameState {
	/**
	 * @author pbg2h15agu, pbg2h15ala 
	 */
	private static FinalStatGameState ref = null;
	private Statistic[] stats = null;	
	private int[] wins = null;
	
	//Singelton erstellen/Referen holen
	public static FinalStatGameState  getInstance(GameStateManager gsm){

		if(ref == null)
			ref = new FinalStatGameState(gsm);
		return ref;
	}
	
	//Kostruktor für Singelton
	private FinalStatGameState(GameStateManager gsm) {
		super(gsm);

		init();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	} 
	
	public void addRound(Statistic[] roundStats, int posOfWinner){
		// Stats übernehmen wenn null
		if(stats == null){
			stats = roundStats;
			wins = new int[roundStats.length];
		}
		// sonst dazurechnen
		else{
			for (int i=0;i<roundStats.length;i++){
				stats[i].addStatistic(roundStats[i]);
			}
		}
		wins[posOfWinner]++;
	}
	
	public void resetFinalStats(){
		// Statistik zurücksetzten
		// TODO Methode in SpielEnde aufrufen
		stats = null;	
		wins = null;
	}
}