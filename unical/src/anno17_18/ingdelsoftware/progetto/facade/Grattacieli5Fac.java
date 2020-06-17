package anno17_18.ingdelsoftware.progetto.facade;

import javax.swing.UnsupportedLookAndFeelException;

import anno17_18.ingdelsoftware.progetto.model.GrigliaQuadrata;
import anno17_18.ingdelsoftware.progetto.model.abstractFactory.Grattacieli5Factory;
import anno17_18.ingdelsoftware.progetto.model.memento.GrigliaGrattacieliCTSer;
import anno17_18.ingdelsoftware.progetto.model.memento.GrigliaGrattacieliCT;
import anno17_18.ingdelsoftware.progetto.view.GrattacieliGUI;
import anno17_18.ingdelsoftware.progetto.view.builder.GUI;

public class Grattacieli5Fac {
	
	private static final int DIM = 5;
	
	public static GrigliaQuadrata griglia() {
		return new Grattacieli5Factory().creaGriglia();
	}// creaGriglia5
	
	public static GrigliaGrattacieliCTSer CTSer() {
		return GrigliaGrattacieliCTSer.crea();
	}// creaCT
	
	public static GrigliaGrattacieliCT CT() {
		return GrigliaGrattacieliCT.crea();
	}// creaCT
	
	public static GrattacieliGUI GUI() throws ClassNotFoundException, 
		InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		return GUI.crea(DIM);
	}// creaGUI

}// Grattacieli5Fac
