package anno17_18.ingdelsoftware.progetto.view.builder;

import javax.swing.JOptionPane;

import anno17_18.ingdelsoftware.progetto.view.chainOfResponsability.HelperGUI;

class HelperGUI12 extends HelperGUI {
	
	private final static String TESTO = "Usa le frecce per navigare tra le soluzioni ottenute,\n"
			+ "oppure inserisci l'indice di quella che preferisci visualizzare.\n\n"
			+ "N.B. Il numero di soluzioni ottenute può differire da quello deciso precedentemente.\n"
			+ "		Difatti, se la griglia è stata configurata completamente (e correttamente),\n"
			+ "		si avrà un'unica soluzione.";

	@Override
	public void check ( int nPanel ) {
		if ( nPanel == 11 )
			super.helper.check(nPanel);
		else
			JOptionPane.showMessageDialog (null, TESTO, TITOLO_INFO_BOX, JOptionPane.INFORMATION_MESSAGE);
	}// check

}// HelperGUI12
