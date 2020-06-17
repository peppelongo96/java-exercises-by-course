package anno17_18.ingdelsoftware.progetto.view.builder;


import javax.swing.JTextField;

import anno17_18.ingdelsoftware.progetto.model.Cella;

class CellaGUI extends JTextField implements Cella {
	
	private static final long serialVersionUID = 1L;
	
	protected static final int CELLA_VUOTA = 0;
	protected static final int DIM_BORDO_CELLA = 2;
	
	private int riga, colonna;
	private String testo;
		
	CellaGUI( int riga, int colonna ) {
		super("");
		this.riga = riga;
		this.colonna = colonna;
		this.setHorizontalAlignment(JTextField.CENTER);
		this.setEditable(false);
		this.setFont(GUI.NUMBER_FONT);
	}// COSTRUTTORE
	
	@Override
	public int getRiga() {
		return riga;
	}// getRiga

	@Override
	public int getColonna() {
		return colonna;
	}// getColonna

	@Override
	public int getValore() {
		testo = this.getText().trim();
		if ( testo.equals("") )
			return CELLA_VUOTA;
		return Integer.parseInt(testo);
	}// getValore

	@Override
	public void setValore( int valore ) {
		this.setText(Integer.toString(valore));
	}// setValore

}// CellaGUI
