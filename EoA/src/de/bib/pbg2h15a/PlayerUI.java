package de.bib.pbg2h15a;


import com.badlogic.gdx.scenes.scene2d.ui.TextField;


/**
 * @author pbg2h15are
 * @author pbg2h15aro
 * just for fun
 *
 */
public class PlayerUI {

	private ButtonErstellen btn;
	private TextField txf;
	
	
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
