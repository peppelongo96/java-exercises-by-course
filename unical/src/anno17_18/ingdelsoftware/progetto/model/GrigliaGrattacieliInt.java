package anno17_18.ingdelsoftware.progetto.model;

public interface GrigliaGrattacieliInt {
	
	public static final int VAL_DEF = 0;
	
	public int getPVSu ( int col );
	public void setPVSu ( int col, int alt );
	public int getPVGiu ( int col );
	public void setPVGiu ( int col, int alt );
	public int getPVSx ( int riga );
	public void setPVSx ( int riga, int Valore );
	public int getPVDx ( int riga );
	public void setPVDx ( int riga, int alt );
	public boolean grigliaValida();
	
}// GrigliaGrattacieliInt
