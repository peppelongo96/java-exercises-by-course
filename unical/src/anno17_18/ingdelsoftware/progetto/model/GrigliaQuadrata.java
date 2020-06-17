package anno17_18.ingdelsoftware.progetto.model;

public interface GrigliaQuadrata {
	
	public int getDim ();
	public int getValMax();
	public int getValMin();
	public int getVal( int riga, int colonna );
	public void setVal( int riga, int colonna, int valore );
	public void reset();
	
}// Griglia Quadrata
