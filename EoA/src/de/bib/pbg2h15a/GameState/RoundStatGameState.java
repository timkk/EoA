package de.bib.pbg2h15a.GameState;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import de.bib.pbg2h15a.GameComp.Player;
import de.bib.pbg2h15a.Uitl.ButtonErstellen;
import de.bib.pbg2h15a.Uitl.Statistic;
import de.bib.pbg2h15a.Uitl.Timer;

public class RoundStatGameState extends GameState {
	/**
	 * @author pbg2h15agu, pbg2h15ala, pbg2h15ast
	 */
	
	private Statistic[] stats;
	private FinalStatGameState finalStats;

	private BitmapFont writer;
	private SpriteBatch batch;
	
	private Timer timer;
	ButtonErstellen next;
	
	private List<Player> player;
	int playerAmount;
	int rounds;

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

	// Mehrdimensionales Array
	// 1. Index: 0-3: Spielernummer
	// 2. Index: 0-15: je 2 => 1 Statistikunterpunkt

	texHelper[][] numbers = new texHelper[4][16];
	
	// TODO
	// Timer
	// zum Spiel zur�ck
	// nach letzter Runde zu Endstatistik wechseln

	protected RoundStatGameState(GameStateManager gsm, Statistic[] stats, List<Player> player,int playerAmount) {
		super(gsm);
		
		this.stats = stats;
		// FinalStatGameState - Referenz holen
		finalStats = FinalStatGameState.getInstance(gsm, player);
		// FinalStatGameState add round;
		finalStats.addRound(stats, getWinner());
		
		this.player = player;
		this.playerAmount = playerAmount;
		init();
	}

	@Override
	public void init() {
		writer = new BitmapFont();
		writer.setColor(Color.BLACK);
		batch = new SpriteBatch();
		
		next = new ButtonErstellen(0,-10,"img/GamePrepare/weiter.png");

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
		lblSiege = new texHelper(100, 150, "img/Stats/siege_200x50.png"); //y=225 // x �berall 200
		lblPunkte = new texHelper(100, 225, "img/Stats/punkte_200x50.png");//y=275
		lblPlacedBombs = new texHelper(100, 300, "img/Stats/bomben_gelegt_200x50.png");//y=325				  //PlacedBombs nicht n�tig in der Statistik
		lblKilledEnemies = new texHelper(100, 375, "img/Stats/gegner_gekillt_200x50.png");//y=375
		lblSelfKills = new texHelper(100, 450, "img/Stats/suizide_200x50.png");//y=425						  //SelfKills nicht n�tig in der Statistik
		lblPowerUpPickUp = new texHelper(100, 525, "img/Stats/power_ups_200x50.png");//y=475
		lblIllnessPickUp = new texHelper(100, 600, "img/Stats/krankheiten_gesammelt_200x50.png");//y=525
		lblIllnessTransfer = new texHelper(100, 675, "img/Stats/krankheiten_uebergeben_200x50.png");//y=575         //Illness Transfer nicht n�tig in der Statistik, Abfrage ist schwierig

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
			if(lastRound(LocalGamePrepareState.getWinRounds())){
				gsm.setState(gsm.FINAL_STATISTIC, player);
			}
			else{
				gsm.setState(gsm.GAME,player.get(0).getName(),player.get(1).getName(),player.get(2).getName(),player.get(3).getName(),playerAmount);
			}
		}

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		batch.begin();
		
		next.render(batch);
		
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

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	private int getWinner() {
		
		// TODO: Methode zum gewinner w�hlen festlegen
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
	
	private boolean lastRound(int neededWins){
		for(int i = 0; i < stats.length; ++i){
			if(finalStats.getWins()[i] == neededWins){
				return true;
			}
		}
		return false;
	}
	
}
