package anno17_18.ingdelsoftware.progetto.controller;

import javax.swing.UnsupportedLookAndFeelException;
import anno17_18.ingdelsoftware.progetto.facade.Grattacieli5Fac;
import anno17_18.ingdelsoftware.progetto.model.Gioco;
import anno17_18.ingdelsoftware.progetto.model.GrigliaQuadrata;
import anno17_18.ingdelsoftware.progetto.model.memento.GrigliaGrattacieliCTSer;
import anno17_18.ingdelsoftware.progetto.model.memento.GrigliaGrattacieliCT;
import anno17_18.ingdelsoftware.progetto.view.GrattacieliGUI;

@SuppressWarnings("unused")
public class GiocaGrattacieli5 {
	
	public static Gioco g;
	public static GrigliaQuadrata gq;
	public static GrigliaGrattacieliCT ggCT; 
	//public static GrigliaGrattacieliCTSer ggCT; // Versione Serializable
	public static GrattacieliGUI gui;
	
	public static void main(String[] args) throws ClassNotFoundException, 
		InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		gq = Grattacieli5Fac.griglia();
		ggCT = Grattacieli5Fac.CT(); 
		//ggCT = Grattacieli5Fac.CTSer();	// Versione Serializable
		g = (Gioco)gq;
		gui = Grattacieli5Fac.GUI();

	}// main

}// Gioca
