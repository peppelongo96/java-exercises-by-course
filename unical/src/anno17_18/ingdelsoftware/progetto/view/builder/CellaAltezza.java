package anno17_18.ingdelsoftware.progetto.view.builder;

import java.awt.Color;

import javax.swing.BorderFactory;

class CellaAltezza extends CellaGUI{
	
	private static final long serialVersionUID = 1L;

	public CellaAltezza( int riga, int colonna ) {
		super(riga,colonna);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK,DIM_BORDO_CELLA));
	}// COSTRUTTORE

}// CellaAltezza
