package anno17_18.ingdelsoftware.progetto.model.memento;

public class GrigliaGrattacieliMem implements Memento {

	private static final long serialVersionUID = 1L;
	
	private Integer[][] grigliaMem;
	
	public GrigliaGrattacieliMem( Integer[][] grigliaMem ) {
		this.grigliaMem = grigliaMem;
	}// COSTRUTTORE
	
	public Integer getAltezza ( int i, int j ) {
		return grigliaMem[i][j];
	}// getAltezza

}// GrigliaGrattacieliMem
