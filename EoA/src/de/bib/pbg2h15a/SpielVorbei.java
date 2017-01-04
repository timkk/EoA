package de.bib.pbg2h15a;

public class SpielVorbei {

	
	/**
	 * @author pbg2h15ani
	 * @param spieler
	 */
	
	public boolean spielVorbei(Player[] spieler)
	{
		int anzahlLebenderSpieler = 0;
		
		for (int i = 0; i < spieler.length; i++) {
			if(spieler[i].getLife() > 0)
			{
				anzahlLebenderSpieler++;
			}
		}
		
		return anzahlLebenderSpieler < 2;
		
		
	}
	
}
