package de.bib.pbg2h15a;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RoundStatGameState extends GameState {
	/**
	 * @author pbg2h15agu, pbg2h15ala
	 */
	
	private Statistic[] stats;
	private FinalStatGameState finalStats;

	private BitmapFont writer;
	private SpriteBatch batch;
	
	private Timer timer;
	ButtonErstellen next;

	// Label für die Spieler
	texHelper lblSpieler1;
	texHelper lblSpieler3;
	texHelper lblSpieler2;
	texHelper lblSpieler4;

	// Label für die Statistikunterpunkte
	texHelper lblSiege;
	texHelper lblPunkte;
	texHelper lblPlacedBombs;
	texHelper lblKilledEnemies;
	texHelper lblSelfKills;
	texHelper lblPowerUpPickUp;
	texHelper lblIllnessPickUp;
	texHelper lblIllnessTransfer;

	// Mehrdimensionales Array
	// 1. Index: 0-3: Spielernummer
	// 2. Index: 0-15: je 2 => 1 Statistikunterpunkt

	texHelper[][] numbers = new texHelper[4][16];
	
	// TODO
	// Timer
	// zum Spiel zurück
	// nach letzter Runde zu Endstatistik wechseln

	protected RoundStatGameState(GameStateManager gsm, Statistic[] stats) {
		super(gsm);
		
		this.stats = stats;
		// FinalStatGameState - Referenz holen
		finalStats = FinalStatGameState.getInstance(gsm);
		// FinalStatGameState add round;
		finalStats.addRound(stats, getWinner());
		
		init();
	}

	@Override
	public void init() {
		writer = new BitmapFont();
		writer.setColor(Color.BLACK);
		batch = new SpriteBatch();
		
		next = new ButtonErstellen(0,0,"img/GamePrepare/arrow_right.png");

		// Label für die Spieler initialisieren
		lblSpieler1 = new texHelper(400, 175, "img/Stats/spieler1_100_50.png");
		lblSpieler2 = new texHelper(500, 175, "img/Stats/spieler2_100_50.png");
		lblSpieler3 = new texHelper(600, 175, "img/Stats/spieler3_100_50.png");
		lblSpieler4 = new texHelper(700, 175, "img/Stats/spieler4_100_50.png");

		// Label für die Statistikunterpunkte initialisieren
		lblSiege = new texHelper(200, 225, "img/Stats/siege_200x50.png");
		lblPunkte = new texHelper(200, 275, "img/Stats/punkte_200x50.png");
		lblPlacedBombs = new texHelper(200, 325, "img/Stats/bomben_gelegt_200x50.png");
		lblKilledEnemies = new texHelper(200, 375, "img/Stats/gegner_gekillt_200x50.png");
		lblSelfKills = new texHelper(200, 425, "img/Stats/suizide_200x50.png");
		lblPowerUpPickUp = new texHelper(200, 475, "img/Stats/power_ups_200x50.png");
		lblIllnessPickUp = new texHelper(200, 525, "img/Stats/krankheiten_gesammelt_200x50.png");
		lblIllnessTransfer = new texHelper(200, 575, "img/Stats/krankheiten_uebergeben_200x50.png");

		// Zahlenfelder Initialiesieren
		// Position festlegen
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 16; y++) {
				int xOffset = 0;
				int yOffset = 0;
				if (y % 2 == 1)
					xOffset = 50;
				yOffset = (y / 2) * 50;
				numbers[x][y] = new texHelper(400 + x * 100 + xOffset, 225 + yOffset, "img/Stats/0_50x50.png");
			}
			setNumberTo(finalStats.getWins()[x], numbers[x][0], numbers[x][1]);
			setNumberTo(stats[x].getPoints(), numbers[x][2], numbers[x][3]);
			
			setNumberTo(stats[x].getPlacedBombs(), numbers[x][4], numbers[x][5]);
			setNumberTo(stats[x].getKilledEnemies(), numbers[x][6], numbers[x][7]);	
			
			setNumberTo(stats[x].getSelfkills(), numbers[x][8], numbers[x][9]);
			setNumberTo(stats[x].getPowerUpPickUpCount(), numbers[x][10], numbers[x][11]);
			
			setNumberTo(stats[x].getIllnessPickUpCount(), numbers[x][12], numbers[x][13]);
			setNumberTo(stats[x].getIllnessTransferCount(), numbers[x][14], numbers[x][15]);
		}
		
		timer = new Timer(10);
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
		timer.update(dt);
		
		if(timer.isFinished() || next.isClicked()){
			if(lastRound()){
				gsm.setState(gsm.FINAL_STATISTIC);
			}
			else{
				gsm.setState(gsm.GAME);
			}
		}

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		batch.begin();
		
		next.render(batch);
		
		// Label für die Spieler rendern
		lblSpieler1.render(batch);
		lblSpieler2.render(batch);
		lblSpieler3.render(batch);
		lblSpieler4.render(batch);

		// Label für die Statistikunterpunkte rendern
		lblSiege.render(batch);
		lblPunkte.render(batch);
		lblPlacedBombs.render(batch);
		lblKilledEnemies.render(batch);
		lblSelfKills.render(batch);
		lblPowerUpPickUp.render(batch);
		lblIllnessPickUp.render(batch);
		lblIllnessTransfer.render(batch);

		// Label für die Zahlen rendern

		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 16; y++) {
				numbers[x][y].render(batch);

			}
		}

		batch.end();

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	private int getWinner() {
		
		// TODO: Methode zum gewinner wählen festlegen
		int maxPos = 0;
		int maxPoints = 0;
		for (int i = 0; i < stats.length; i++) {
			if (stats[i].getPoints() > maxPoints) {
				maxPoints = stats[i].getPoints();
				maxPos = i;
			}
		}
		return maxPos;
	}

	private class texHelper {

		/**
		 * @author pbg2h15ala
		 */

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

	private void setNumberTo(int number, texHelper value10, texHelper value1) {
		/**
		 * @author pbg2h15ala
		 */
		number %= 100;
		value10.setTexture("img/Stats/" + number / 10 + "_50x50.png");
		value1.setTexture("img/Stats/" + number % 10 + "_50x50.png");
	}
	
	private boolean lastRound(){
		return true;
	}
	
}
