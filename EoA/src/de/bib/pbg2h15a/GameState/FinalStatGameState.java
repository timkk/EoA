package de.bib.pbg2h15a.GameState;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.bib.pbg2h15a.GameComp.Player;
import de.bib.pbg2h15a.Uitl.ButtonErstellen;
import de.bib.pbg2h15a.Uitl.Statistic;

/**
 * Anzeige und DatenModdell der finalen Statisitk
 * 
 * @author pbg2h15agu, pbg2h15ala
 * 
 *  (Kommentiert von Johnny Gunko pbg2h15agu)
 */

public class FinalStatGameState extends GameState {

	private static FinalStatGameState ref = null;
	private Statistic[] stats = null;	
	private int[] wins = null;
	
	private BitmapFont writer;
	private SpriteBatch batch;
	
	List<Player> player;

	// Label f�r die Spieler
	texHelper lblSpieler1;
	texHelper lblSpieler3;
	texHelper lblSpieler2;
	texHelper lblSpieler4;
	
	BitmapFont lbSpieler1Name;
	BitmapFont lbSpieler2Name;
	BitmapFont lbSpieler3Name;
	BitmapFont lbSpieler4Name;

	// Label f�r die Statistikunterpunkte
	texHelper lblSiege;
	texHelper lblPunkte;
	texHelper lblPlacedBombs;
	texHelper lblKilledEnemies;
	texHelper lblSelfKills;
	texHelper lblPowerUpPickUp;
	texHelper lblIllnessPickUp;
	texHelper lblIllnessTransfer;
	
	// Zur�ck Button

	ButtonErstellen zurueck;
	
	// Mehrdimensionales Array
	// 1. Index: 0-3: Spielernummer
	// 2. Index: 0-15: je 2 => 1 Statistikunterpunkt
	// the numbers? what do they mean?

	texHelper[][] numbers = new texHelper[4][16];
	
	
	/**
	 * Singelton erstellen/Referenz hohlen
	 * 
	 * @param gsm GameStateManager
	 * @return die Referenz der finalen Statistik (Singelton)
	 */
	public static FinalStatGameState  getInstance(GameStateManager gsm, List<Player> player){

		if(ref == null)
			ref = new FinalStatGameState(gsm, player);
		return ref;
	}
	
	
	/**
	 * Konstruktor f�r Singelton
	 * 
	 * @param gsm GameStateManager
	 */
	private FinalStatGameState(GameStateManager gsm, List<Player> player) {
		super(gsm);
		this.player = player;
	}

	
	/**
	 * Initalisierung der Variablen
	 */
	@Override
	public void init() {
		writer = new BitmapFont();
		writer.setColor(Color.BLACK);
		batch = new SpriteBatch();
		
		// Zur�ck Button
		zurueck = new ButtonErstellen(810, -10, "img/GamePrepare/zuruck.png");		

		// Label f�r die Spieler initialisieren
		lblSpieler1 = new texHelper(350, 75, "img/Stats/spieler1_100_50.png");
		lblSpieler2 = new texHelper(475, 75, "img/Stats/spieler2_100_50.png");
		lblSpieler3 = new texHelper(600, 75, "img/Stats/spieler3_100_50.png");
		lblSpieler4 = new texHelper(725, 75, "img/Stats/spieler4_100_50.png");
		
		lbSpieler1Name = new BitmapFont();
		lbSpieler1Name.setColor(Color.WHITE);
		
		lbSpieler2Name = new BitmapFont();
		lbSpieler2Name.setColor(Color.WHITE);
		
		lbSpieler3Name = new BitmapFont();
		lbSpieler3Name.setColor(Color.WHITE);
		
		lbSpieler4Name = new BitmapFont();
		lbSpieler4Name.setColor(Color.WHITE);

		// Label f�r die Statistikunterpunkte initialisieren
		lblSiege = new texHelper(100, 150, "img/Stats/siege_200x50.png");
		lblPunkte = new texHelper(100, 225, "img/Stats/punkte_200x50.png");
		lblPlacedBombs = new texHelper(100, 300, "img/Stats/bomben_gelegt_200x50.png");
		lblKilledEnemies = new texHelper(100, 375, "img/Stats/gegner_gekillt_200x50.png");
		lblSelfKills = new texHelper(100, 450, "img/Stats/suizide_200x50.png");
		lblPowerUpPickUp = new texHelper(100, 525, "img/Stats/power_ups_200x50.png");
		lblIllnessPickUp = new texHelper(100, 600, "img/Stats/krankheiten_gesammelt_200x50.png");
		lblIllnessTransfer = new texHelper(100, 675, "img/Stats/krankheiten_uebergeben_200x50.png");

		// Zahlenfelder Initialiesieren
		// Position festlegen
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 16; y++) {
				int xOffset = 0;
				int yOffset = 0;
				if (y % 2 == 1)
					xOffset = 50;
				yOffset = (y / 2) * 75;
				numbers[x][y] = new texHelper(350 + x * 125 + xOffset, 150 + yOffset, "img/Stats/0_50x50.png");
			}
			setNumberTo(getWins()[x], numbers[x][0], numbers[x][1]);
			setNumberTo(stats[x].getPoints(), numbers[x][2], numbers[x][3]);
			
			setNumberTo(stats[x].getPlacedBombs(), numbers[x][4], numbers[x][5]);
			setNumberTo(stats[x].getKilledEnemies(), numbers[x][6], numbers[x][7]);	
			
			setNumberTo(stats[x].getSelfkills(), numbers[x][8], numbers[x][9]);
			setNumberTo(stats[x].getPowerUpPickUpCount(), numbers[x][10], numbers[x][11]);
			
			setNumberTo(stats[x].getIllnessPickUpCount(), numbers[x][12], numbers[x][13]);
			setNumberTo(stats[x].getIllnessTransferCount(), numbers[x][14], numbers[x][15]);
		}
		
	}

	
	/**
	 * Wird jeden Frame aufgerufen um Aktionen auszuf�hren
	 * @param dt ist die DeltaTime
	 */
	@Override
	public void update(float dt) {
		if(zurueck.isClicked()){
			resetFinalStats();
			gsm.setState(gsm.MAIN);
		}
	}

	
	/**
	 * Rendert alle f�r die State ben�tigten Grafiken
	 */
	@Override
	public void render() {
		batch.begin();
		
		zurueck.render(batch);
		
		// Label f�r die Spieler rendern
		lblSpieler1.render(batch);
		lblSpieler2.render(batch);
		lblSpieler3.render(batch);
		lblSpieler4.render(batch);

		// Label f�r die Statistikunterpunkte rendern
		lblSiege.render(batch);
		lblPunkte.render(batch);
		lblPlacedBombs.render(batch);
		lblKilledEnemies.render(batch);
		lblSelfKills.render(batch);
		lblPowerUpPickUp.render(batch);
		lblIllnessPickUp.render(batch);
		lblIllnessTransfer.render(batch);

		// Label f�r die Zahlen rendern

		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 16; y++) {
				numbers[x][y].render(batch);

			}
		}

		lbSpieler1Name.draw(batch, player.get(0).getName(), 355, Gdx.graphics.getHeight()-45);
		lbSpieler2Name.draw(batch, player.get(1).getName(), 485, Gdx.graphics.getHeight()-45);
		lbSpieler2Name.draw(batch, player.get(2).getName(), 605, Gdx.graphics.getHeight()-45);
		lbSpieler2Name.draw(batch, player.get(3).getName(), 735, Gdx.graphics.getHeight()-45);
		
		batch.end();
	}
	
	
	/**
	 * F�gt die Ergebnisse der letzten Runde der Endstatistik hinzu
	 * 
	 * @param roundStats die Statistiken der letzen Runde
	 * @param posOfWinner der Gewinner der letzen Runde
	 */
	public void addRound(Statistic[] roundStats, int posOfWinner){
		// Stats �bernehmen wenn null
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
		init();
	}
	
	
	/**
	 * Setzt die Endstatistik zur�ck
	 */
	public void resetFinalStats(){
		stats = null;	
		wins = null;
	}
	public int[] getWins(){
		return wins;
	}
	
	
	/**
	 * Einfachere ausf�rhung der Platzierung und austtausch von Grafiken
	 * @author pbg2h15ala
	 */
	private class texHelper {

		private Texture texture;
		private float x;
		private float y;

		private texHelper(float x, float y, String pfad) {
			texture = new Texture(pfad);
			this.x = x;
			this.y = 750 - y;

		}

		public void render(SpriteBatch batch) {
			batch.draw(texture, x, y);

		}

		public void setTexture(String pfad) {
			texture = new Texture(pfad);
		}
	}
	
	/**
	 * Sorgt daf�r das die Grafik, die Ziffer ist die Dargestellt werden soll
	 * 
	 * @param number die darzustellende Zahl
	 * @param value10 die Zehnerstelle der Zahl
	 * @param value1 die Einerstelle der zahl
	 */
	
	private void setNumberTo(int number, texHelper value10, texHelper value1) {
		number %= 100;
		value10.setTexture("img/Stats/" + number / 10 + "_50x50.png");
		value1.setTexture("img/Stats/" + number % 10 + "_50x50.png");
	}
	
	/**
	 * Gibt Ressourcen frei
	 */
	@Override
	public void dispose() {
		batch.dispose();
	}
}
