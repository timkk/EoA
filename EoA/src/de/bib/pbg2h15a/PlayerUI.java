package de.bib.pbg2h15a;


import com.badlogic.gdx.scenes.scene2d.ui.TextField;


/**
 * Erstellt die Eingabefelder der Namenseingabe für jeden Spieler
 * 
 * Erstellt für jeden Spieler ein Eingabefeld für den Spielernamen,
 * nachdem der Benutzer die Spieleinstellungen abgeschlossen hat
 * 
 * @author pbg2h15are
 * @author pbg2h15aro
 * 
 * (Kommentiert von Marco Struck pbg2h15ast)
 */
public class PlayerUI {

	private ButtonErstellen btn;
	private TextField txf;
	
	/** 
	 * Konstruktor der Klasse PlayerUI
	 * 
	 * @param btn
	 * @param txf
	 */
	
	public PlayerUI(ButtonErstellen btn, TextField txf) {
		super();
		this.btn = btn;
		this.txf = txf;
	}

	/**
	 * @return the btn
	 */
	public ButtonErstellen getBtn() {
		return btn;
	}


	/**
	 * @param btn the btn to set
	 */
	public void setBtn(ButtonErstellen btn) {
		this.btn = btn;
	}


	/**
	 * @return the txf
	 */
	public TextField getTxf() {
		return txf;
	}


	/**
	 * @param txf the txf to set
	 */
	public void setTxf(TextField txf) {
		this.txf = txf;
	}

	
	
	
	
}
