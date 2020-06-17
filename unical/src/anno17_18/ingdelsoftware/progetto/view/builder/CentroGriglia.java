package anno17_18.ingdelsoftware.progetto.view.builder;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import anno17_18.ingdelsoftware.progetto.model.GrigliaGrattacieliInt;

class CentroGriglia extends JPanel implements GrigliaGrattacieliInt {

	private static final long serialVersionUID = 1L;
	
	private int dim, dimEff, altMax;
	private CellaGUI[][] grigliaGUI;

	CentroGriglia( int dim ) {
		super();
		this.dim = dim;
		this.dimEff = dim+2;
		this.altMax = dim;
		this.setLayout(new GridLayout(dimEff,dimEff));
		grigliaGUI = new CellaGUI[dimEff][dimEff];
		for (int i = 0; i < dimEff; i++) { 
			for (int j = 0; j < dimEff; j++) {
				if ( i==0 || i==dim+1 || j==0 || j==dim+1 ) {
					grigliaGUI[i][j] = new CellaPV(i,j,altMax);
					if ( (i==0 && j==0) || 
							(i==0 && j==dim+1) ||
							(i==dim+1 && j==0) ||
							(i==dim+1 && j==dim+1) )
						grigliaGUI[i][j].setVisible(false);
				}
				else
					grigliaGUI[i][j] = new CellaAltezza(i,j);
				this.add(grigliaGUI[i][j]);
			}
		}
	}// COSTRUTTORE

	public void setAlt( int riga, int colonna, int alt ) {
		grigliaGUI[riga][colonna].setValore(alt);
	}// setAlt
	
	public void reset() {
		for (int i = 0; i < grigliaGUI.length; i++) 
			for (int j = 0; j < grigliaGUI.length; j++)
				grigliaGUI[i][j].setText("");
	}// reset
	
	private CellaGUI getCellaPVSu ( int col ) {
		return grigliaGUI[0][col];
	}// getCellaPVSu
	
	private CellaGUI getCellaPVGiu ( int col ) {
		return grigliaGUI[dim+1][col];
	}// getCellaPVGiu
	
	private CellaGUI getCellaPVSx ( int riga ) {
		return grigliaGUI[riga][0];
	}// getCellaPVSx
	
	private CellaGUI getCellaPVDx ( int riga ) {
		return grigliaGUI[riga][dim+1];
	}// getCellaPVDx
	
	public void rendiPVModificabili( boolean b ) {
		for (int t = 1; t <=dim ; t++) {
			getCellaPVSu(t).setEditable(b);
			getCellaPVGiu(t).setEditable(b);
			getCellaPVSx(t).setEditable(b);
			getCellaPVDx(t).setEditable(b);
		}
	}// rendiPVModificabili
	
	public void aggiornaGrigliaGUI() {
		SwingUtilities.updateComponentTreeUI(this);
	}// aggiornaGrigliaGUI

	@Override
	public int getPVSu( int col ) {
		return grigliaGUI[0][col].getValore();
	}// getPVSu

	@Override
	public void setPVSu( int col, int alt ) {
		grigliaGUI[0][col].setValore(alt);
	}// setPVSu

	@Override
	public int getPVGiu( int col ) {
		return grigliaGUI[dim+1][col].getValore();
	}// getPVGiu

	@Override
	public void setPVGiu ( int col, int alt ) {
		grigliaGUI[dim+1][col].setValore(alt);
	}// setPVgiu
	
	@Override
	public int getPVSx ( int riga ) {
		return grigliaGUI[riga][0].getValore();
	}// getPVSx
	
	@Override
	public void setPVSx ( int riga, int alt ) {
		grigliaGUI[riga][0].setValore(alt);
	}// setPVSx
	
	@Override
	public int getPVDx ( int riga ) {
		return grigliaGUI[riga][dim+1].getValore();
	}// getPVDx
	
	@Override
	public void setPVDx ( int riga, int alt ) {
		grigliaGUI[riga][dim+1].setValore(alt);
	}// setPVDx

	@Override
	public boolean grigliaValida() {
		for (int t = 1; t <=dim ; t++) 
			if ( getPVSu(t) > 0 || getPVGiu(t) > 0 || getPVSx(t) > 0 || getPVDx(t) > 0 )
				return true;
		return false;
	}// PVammissibili

}// CentroGriglia
