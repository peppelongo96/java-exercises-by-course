package anno17_18.ingdelsoftware.progetto.view;

public interface GrattacieliGUI {
	
	public int getGrigliaPVSu ( int col );
	public int getGrigliaPVGiu ( int col );
	public int getGrigliaPVSx ( int riga );	
	public int getGrigliaPVDx ( int riga );
	public void rendiPVModificabili ( boolean b ); 
	public boolean grigliaPVvalida();
	public void setCellaGUI ( int riga, int colonna, int alt );
	public void aggiornaGrigliaGUI();
	public void resetGrigliaGUI();
	public int chooseCORHelper();
	public void showOnInfoBox( String s );
	
}// GrattacieliGUI
