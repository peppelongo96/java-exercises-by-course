package anno17_18.ingdelsoftware.progetto.model.abstractFactory;

import anno17_18.ingdelsoftware.progetto.model.Cella;

class CellaGrattacieli implements Cella {
	
	private int riga, colonna, altezza;
	
	CellaGrattacieli ( int riga, int colonna, int altezza ) {
		this.riga = riga;
		this.colonna = colonna;
		this.altezza = altezza;
	}// COSTRUTTORE
	
	public int getRiga() {
		return this.riga;
	}// getRiga
	
	public int getColonna() {
		return this.colonna;
	}// getColonna
	
	public int getValore() {
		return this.altezza;
	}// getValore
	
	public void setValore ( int valore ) {
		this.altezza = valore;
	}// setValore
	
}// CellaGrattacieli
