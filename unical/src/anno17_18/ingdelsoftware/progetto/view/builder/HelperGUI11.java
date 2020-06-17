package anno17_18.ingdelsoftware.progetto.view.builder;

import javax.swing.JOptionPane;

import anno17_18.ingdelsoftware.progetto.view.chainOfResponsability.HelperGUI;

class HelperGUI11 extends HelperGUI {

	private final static String TESTO = "Trattasi di un risolutore per il gioco Grattacieli.\n"
			+ "La griglia rappresenta il quartiere di una città.\n"
			+ "Sapendo che in ciascuna riga o colonna non vi sono grattacieli della stessa altezza,\n"
			+ "definire il numero di grattacieli visibili dai punti di vista (PV) di righe e\\o colonne\n"
			+ "(i grattacieli più alti nascondono quelli più bassi).\n"
			+ "\n"
			+ "L'algoritmo lavora su backtracking.\n"
			+ "E' necessario definire il numero massimo di soluzioni su cui poter poi navigare.\n"
			+ "Una volta definiti i PV, premere START per avviare la simulazione.\n"
			+ "Il tasto RESET permette l'avvio di una sessione, in qualsiasi momento.";

	@Override
	public void check ( int nPanel ) {
		if ( nPanel == 12 )
			super.helper.check(nPanel);
		else
			JOptionPane.showMessageDialog (null, TESTO, TITOLO_INFO_BOX, JOptionPane.INFORMATION_MESSAGE);
	}// check

}// HelperGUI11
