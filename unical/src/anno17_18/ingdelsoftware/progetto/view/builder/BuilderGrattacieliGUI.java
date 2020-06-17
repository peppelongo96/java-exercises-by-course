package anno17_18.ingdelsoftware.progetto.view.builder;

import java.awt.BorderLayout;
import java.awt.Container;

public class BuilderGrattacieliGUI {
		
	private static final int X_SIZE_FINESTRA = 600;
	private static final int Y_SIZE_FINESTRA = 700;
	private static final String TITOLO = "Grattacieli Solver";
	
	private int dim;
	private Finestra finestra;
	private Container cp;
	
	public BuilderGrattacieliGUI( int dim ) {
		this.dim = dim;
	}// COSTRUTTORE
	
	public void buildFinestra() {
		finestra = new Finestra(TITOLO,X_SIZE_FINESTRA,Y_SIZE_FINESTRA);
		cp = finestra.getContentPane();
		cp.setLayout(new BorderLayout());
	}// buildFinestra
	
	public void aggiornaFinestra() {
		finestra.aggiornaFinestra();
	}// aggiornaFinestra
	
	public void buildNord() {
		cp.add(new Nord(),BorderLayout.NORTH);
	}// buildNord
	
	public CentroGriglia buildCentro() {
		CentroGriglia centroFinestra = new CentroGriglia(dim);
		cp.add(centroFinestra,BorderLayout.CENTER);
		return centroFinestra;
	}// buildCentro
	
	public Sud buildSud() {
		Sud sudFinestra = new Sud();
		cp.add(sudFinestra, BorderLayout.SOUTH);
		return sudFinestra;
	}// buildSud
	
}// BuilderGrattacieli
