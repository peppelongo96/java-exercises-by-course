package anno17_18.ingdelsoftware.progetto.model.memento;

import java.io.IOException;
import java.util.ArrayList;

import anno17_18.ingdelsoftware.progetto.controller.GiocaGrattacieli5;

public class GrigliaGrattacieliCT implements CareTaker{
	
	private static GrigliaGrattacieliCT instance; // SINGLETON
	
	private int dim;
	private ArrayList<Memento> listaGrigliaGrattacieliMem;
		
	private GrigliaGrattacieliCT() {
		listaGrigliaGrattacieliMem = new ArrayList<>();
		dim = GiocaGrattacieli5.gq.getDim();
	}// COSTRUTTORE
	
	public static GrigliaGrattacieliCT crea() {
		if ( instance == null )
			instance = new GrigliaGrattacieliCT();
		return instance;
	}// crea

	@Override
	public Memento getMem( int nrSol ) {
		return listaGrigliaGrattacieliMem.get(nrSol-1);
	}// getMem

	@Override
	public void saveMem( Memento m, int nrSol ) throws IOException {
		listaGrigliaGrattacieliMem.add(nrSol-1, m);;
	}// saveMem

	@Override
	public void graficaMemento( int nrSol ) {
		try {
			GrigliaGrattacieliMem ggm = (GrigliaGrattacieliMem)getMem(nrSol);
			for (int i = 0; i < dim; i++) 
				for (int j = 0; j < dim; j++)
					GiocaGrattacieli5.gui.setCellaGUI(i+1, j+1, ggm.getAltezza(i, j));
			GiocaGrattacieli5.gui.aggiornaGrigliaGUI();
		} catch ( Exception e) {e.printStackTrace();}
	}// graficaMemento

}// GrigliaGrattacieliCT
