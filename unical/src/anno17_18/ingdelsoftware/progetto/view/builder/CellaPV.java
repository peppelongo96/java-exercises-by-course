package anno17_18.ingdelsoftware.progetto.view.builder;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;

import anno17_18.ingdelsoftware.progetto.controller.GiocaGrattacieli5;

class CellaPV extends CellaGUI{

	private static final long serialVersionUID = 1L;
		
	private int riga, colonna;
	private int dim, altMax;
	private String tempPrecText;
	private String tempCurText;

	CellaPV( int riga, int colonna, int altMax ) {
		super(riga, colonna);
		this.riga = riga;
		this.colonna = colonna;
		this.altMax = altMax;
		this.dim = altMax;
		this.setForeground(Color.RED);
		this.setEditable(true);
		this.setOpaque(false);
		setBordo();
		tempPrecText = getText();
		tempCurText = tempPrecText;
		this.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				tempCurText = getText();
				if ( !tempPrecText.equals(tempCurText) && ammissibile() == false ) {
					setText("");
					GiocaGrattacieli5.gui.showOnInfoBox("PV inserito non valido");
					tempPrecText = tempCurText;
				}
			}
		});
	}// COSTRUTTORE
	
	private void setBordo() {
		if ( riga==0 ) 
			setBorder(BorderFactory.createMatteBorder(0, 0, DIM_BORDO_CELLA, 0, Color.BLACK));
		else if ( riga==dim+1 ) 
			setBorder(BorderFactory.createMatteBorder(DIM_BORDO_CELLA, 0, 0, 0, Color.BLACK));
		else if ( colonna==0 ) 
			setBorder(BorderFactory.createMatteBorder(0, 0, 0, DIM_BORDO_CELLA, Color.BLACK));
		else if ( colonna == dim+1 ) 
			setBorder(BorderFactory.createMatteBorder(0, DIM_BORDO_CELLA, 0, 0, Color.BLACK));
	}// setBordo

	boolean ammissibile() { 
		boolean check = tempCurText.matches(GUI.REGEX_SOLO_N_INT_POS);
		if ( check==false )
			return false;
		try { 
			int altezza = super.getValore();
			if ( altezza <= CELLA_VUOTA || altezza > altMax )
				throw new NumberFormatException();
		} catch ( NumberFormatException nfe ) {
			return false;
		}
		return true;
	}// ammissibile
	
}// CellaPV
