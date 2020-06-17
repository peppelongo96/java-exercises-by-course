package anno17_18.ingdelsoftware.progetto.view.chainOfResponsability;

public abstract class HelperGUI {
	
	protected final static String TITOLO_INFO_BOX = "REGOLE DI GIOCO";
	
	protected HelperGUI helper = null;
	
	public void setSuperiore ( HelperGUI helper ) {
		this.helper = helper;
	}// setSuperiore
	
	public abstract void check ( int nPanel );

}// HelperGUI
