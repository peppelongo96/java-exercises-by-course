package anno17_18.ingdelsoftware.progetto.view.builder;

import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import anno17_18.ingdelsoftware.progetto.view.GrattacieliGUI;
import anno17_18.ingdelsoftware.progetto.view.builder.BuilderGrattacieliGUI;
import anno17_18.ingdelsoftware.progetto.view.builder.CentroGriglia;
import anno17_18.ingdelsoftware.progetto.view.builder.Sud;

public class GUI implements GrattacieliGUI{
	
	final static String REGEX_SOLO_N_INT_POS = "[\\d]+";
	
	final static Font GUI_FONT = new Font(null, Font.PLAIN, 16);
	final static Font NUMBER_FONT = new Font(null, Font.BOLD, 20);
	final static Font INFO_FONT = new Font(null, Font.ITALIC, 15);
	
	private static GUI instance; // SINGLETON
		
	private BuilderGrattacieliGUI bgg;
	private CentroGriglia grigliaGUI;
	private Sud sudGUI;
	
	private GUI( int dim ) throws ClassNotFoundException, 
		InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		bgg = new BuilderGrattacieliGUI(dim);
		bgg.buildFinestra();
		bgg.buildNord();
		grigliaGUI = bgg.buildCentro();
		sudGUI = bgg.buildSud();
		bgg.aggiornaFinestra();
	}// COSTRUTTORE
	
	public static GUI crea( int dim ) throws ClassNotFoundException, InstantiationException, 
		IllegalAccessException, UnsupportedLookAndFeelException {
		if ( instance==null )
			instance = new GUI(dim);
		return instance;
	}// crea
	
	@Override
	public int chooseCORHelper() {
		return sudGUI.get11o22();
	}// chooseCORHelper
	
	@Override
	public void showOnInfoBox( String s ) {
		sudGUI.writeOnInfoBox(s);
	}// showOnInfoBox
	
	@Override
	public void setCellaGUI ( int riga, int colonna, int alt ) {
		grigliaGUI.setAlt(riga, colonna, alt);
	}// setCellaGUI
	
	@Override
	public void aggiornaGrigliaGUI() {
		grigliaGUI.aggiornaGrigliaGUI();
	}// aggiornaGrigliaGUI

	@Override
	public int getGrigliaPVSu( int col ) {
		return grigliaGUI.getPVSu(col);
	}// getGrigliaPVSu

	@Override
	public int getGrigliaPVGiu( int col ) {
		return grigliaGUI.getPVGiu(col);
	}// getGrigliaPVGiu

	@Override
	public int getGrigliaPVSx( int riga ) {
		// TODO Auto-generated method stub
		return grigliaGUI.getPVSx(riga);
	}// getGrigliaPVSx

	@Override
	public int getGrigliaPVDx( int riga ) {
		return grigliaGUI.getPVDx(riga);
	}// getGrigliaPVDx
	
	@Override
	public void rendiPVModificabili ( boolean b ) {
		grigliaGUI.rendiPVModificabili(b);
	}

	@Override
	public boolean grigliaPVvalida() {
		return grigliaGUI.grigliaValida();
	}// GrigliaPVammissibili

	@Override
	public void resetGrigliaGUI() {
		grigliaGUI.reset();		
	}// resetGrigliaGUI
	
}// GUI
