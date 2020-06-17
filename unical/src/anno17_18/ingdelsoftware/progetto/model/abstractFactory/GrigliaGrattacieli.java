package anno17_18.ingdelsoftware.progetto.model.abstractFactory;

import java.io.IOException;

import anno17_18.ingdelsoftware.progetto.controller.GiocaGrattacieli5;
import anno17_18.ingdelsoftware.progetto.model.Gioco;
import anno17_18.ingdelsoftware.progetto.model.GrigliaGrattacieliInt;
import anno17_18.ingdelsoftware.progetto.model.GrigliaQuadrata;
import anno17_18.ingdelsoftware.progetto.model.memento.GrigliaGrattacieliMem;
import anno17_18.ingdelsoftware.progetto.model.memento.Memento;
import anno17_18.ingdelsoftware.progetto.model.templateMethod.Problema;

abstract class GrigliaGrattacieli extends Problema<CellaGrattacieli, Integer>
	implements GrigliaQuadrata, GrigliaGrattacieliInt, Gioco { 
	
	// PV = punto di vista
	// dim = dimensione griglia
	// altMin/Max = altezza minima/massima di un grattacielo
	
	private int dim; 
	private int altMin, altMax;
	private CellaGrattacieli[][] griglia;
	
	protected GrigliaGrattacieli() {
		super();
		this.dim = getDim();
		this.altMax = getValMax();
		this.altMin = getValMin();
		griglia = new CellaGrattacieli[dim+2][dim+2]; 
		for (int i = 0; i < griglia.length; i++) {
			for (int j = 0; j < griglia.length; j++) {
				griglia[i][j] = new CellaGrattacieli(i, j, VAL_DEF);
			}
		}
	}// COSTRUTTORE	
	
	@Override
	public int getPVSu ( int col ) {
		return griglia[0][col].getValore();
	}// getPVsu
	
	@Override
	public void setPVSu ( int col, int alt ) {
		griglia[0][col].setValore(alt);
	}// setPVsu
	
	@Override
	public int getPVGiu ( int col ) {
		return griglia[dim+1][col].getValore();
	}// getPVgiu
	
	@Override
	public void setPVGiu ( int col, int alt ) {
		griglia[dim+1][col].setValore(alt);
	}// setPVgiu
	
	@Override
	public int getPVSx ( int riga ) {
		return griglia[riga][0].getValore();
	}// getPVSx
	
	@Override
	public void setPVSx ( int riga, int alt ) {
		griglia[riga][0].setValore(alt);
	}// setPVSx
	
	@Override
	public int getPVDx ( int riga ) {
		return griglia[riga][dim+1].getValore();
	}// getPVDx
	
	@Override
	public void setPVDx ( int riga, int alt ) {
		griglia[riga][dim+1].setValore(alt);
	}// setPVDx
	
	@Override
	public boolean grigliaValida() {
		for (int t = 1; t <=dim ; t++) {
			if ( getPVSu(t) < VAL_DEF || getPVSu(t) > dim ||
					getPVGiu(t) < VAL_DEF || getPVGiu(t) > dim ||
					getPVSx(t) < VAL_DEF || getPVSx(t) > dim ||
					getPVDx(t) < VAL_DEF || getPVDx(t) > dim )
				return false;
		}
		for (int t = 1; t <=dim ; t++) 
			if ( getPVSu(t) > 0 || getPVGiu(t) > 0 || getPVSx(t) > 0 || getPVDx(t) > 0 )
				return true;
		return false;
	}// grigliaValida
	
	@Override
	public int getVal( int riga, int colonna ) {
		return griglia[riga][colonna].getValore();
	}// getVal
	
	@Override
	public void setVal( int riga, int colonna, int valore ) {
		griglia[riga][colonna].setValore(valore);
	}// setVal
	
	@Override
	public int start( int nSolMax ) {
		super.nummaxsoluzioni = nSolMax;
		for (int t = 1; t <= dim; t++) {
			setPVSu(t, GiocaGrattacieli5.gui.getGrigliaPVSu(t));
			setPVGiu(t, GiocaGrattacieli5.gui.getGrigliaPVGiu(t));
			setPVSx(t, GiocaGrattacieli5.gui.getGrigliaPVSx(t));
			setPVDx(t, GiocaGrattacieli5.gui.getGrigliaPVDx(t));
		}
		risolvi();
		return super.nrsoluzione; // = numero soluzioni effettivamente trovate
	}// start
		
	@Override
	public void reset() {
		for (int i = 0; i < griglia.length; i++) 
			for (int j = 0; j < griglia.length; j++)
				griglia[i][j].setValore(VAL_DEF);
		super.nrsoluzione = 0;
	}// reset
	
	@Override
	public Memento createMemento() {
		Integer[][] grigliaMem = new Integer[dim][dim];
		for (int i = 0; i < dim; i++) 
			for (int j = 0; j < dim; j++)
				grigliaMem[i][j] = griglia[i+1][j+1].getValore();
		GrigliaGrattacieliMem ggm = new GrigliaGrattacieliMem(grigliaMem);
		return ggm;
	}// createMemento

	@Override
	public void setMemento( Memento m ) {
		GrigliaGrattacieliMem ggm = (GrigliaGrattacieliMem) m;
		for (int i = 1; i <= dim; i++) 
			for (int j = 1; j <= dim; j++)
				griglia[i][j].setValore(ggm.getAltezza(i-1,j-1));
	}// setMemento
	
	private int tempPVSx, altMaxCurSx, nGratSx;
	private int tempPVDx, altMaxCurDx, nGratDx;
	private int tempPVSu, altMaxCurSu, nGratSu;
	private int tempPVGiu, altMaxCurGiu, nGratGiu;
	private boolean okSx, okDx, okSu, okGiu;
	
	boolean verificaAllPV() {
		for (int t = 1; t <= dim; t++) {
			tempPVSx = getPVSx(t); altMaxCurSx = griglia[t][1].getValore(); nGratSx = 1;
			tempPVDx = getPVDx(t); altMaxCurDx = griglia[t][dim].getValore(); nGratDx = 1;
			tempPVSu = getPVSu(t); altMaxCurSu = griglia[1][t].getValore(); nGratSu = 1;
			tempPVGiu = getPVGiu(t); altMaxCurGiu =griglia[dim][t].getValore(); nGratGiu = 1;
			okSx = false; okDx = false; okSu = false; okGiu = false;
			if ( tempPVSx != VAL_DEF || tempPVDx != VAL_DEF || tempPVSu != VAL_DEF || tempPVGiu != VAL_DEF ) {
				for (int v = 2; v <= dim; v++) {
					if ( !okSx && tempPVSx != VAL_DEF && griglia[t][v].getValore() > altMaxCurSx ) {
						altMaxCurSx = griglia[t][v].getValore();
						nGratSx++;
						if ( nGratSx > tempPVSx ) return false;
						if ( altMaxCurSx == altMax ) okSx = true;
					}
					if ( !okDx && tempPVDx != VAL_DEF && griglia[t][dim-v+1].getValore() > altMaxCurDx ) {
						altMaxCurDx = griglia[t][dim-v+1].getValore();
						nGratDx++;
						if ( nGratDx > tempPVDx ) return false;
						if ( altMaxCurDx == altMax ) okDx = true;
					}
					if ( !okSu && tempPVSu != VAL_DEF && griglia[v][t].getValore() > altMaxCurSu ) {
						altMaxCurSu = griglia[v][t].getValore();
						nGratSu++;
						if ( nGratSu > tempPVSu ) return false;
						if ( altMaxCurSu == altMax ) okSu = true;
					}
					if ( !okGiu && tempPVGiu != VAL_DEF && griglia[dim-v+1][t].getValore() > altMaxCurGiu ) {
						altMaxCurGiu = griglia[dim-v+1][t].getValore();
						nGratGiu++;
						if ( nGratGiu > tempPVGiu ) return false;
						if ( altMaxCurGiu == altMax ) okGiu = true;
					}
				}
				if ( ( tempPVSx!= VAL_DEF && nGratSx != tempPVSx )
						|| ( tempPVDx != VAL_DEF && nGratDx != tempPVDx )
						|| ( tempPVSu != VAL_DEF && nGratSu != tempPVSu )
						|| ( tempPVGiu != VAL_DEF && nGratGiu != tempPVGiu ) ) return false;
			}
		}
		return true;
	}/// verificaAllPV
	
	@Override
	public CellaGrattacieli primoPuntoDiScelta() {
		return griglia[1][1];
	}// primoPuntoDiScelta

	@Override
	public CellaGrattacieli prossimoPuntoDiScelta( CellaGrattacieli c, Integer s ) {
		int colNext = c.getColonna()+1;
		int rigaNext = c.getRiga();
		if ( c.getColonna() == dim ) {
			colNext = 1;
			rigaNext++;
		}
		return griglia[rigaNext][colNext];
	}// prossimoPuntoDiScelta
	
	@Override
	public CellaGrattacieli precedentePuntoDiScelta( CellaGrattacieli c ) {
		int colPrev = c.getColonna()-1;
		int rigaPrev = c.getRiga();
		if ( c.getColonna() == 1 ) {
			colPrev = dim;
			rigaPrev--;
		}
		return griglia[rigaPrev][colPrev];
	}// precedentePuntoDiScelta

	@Override
	public CellaGrattacieli ultimoPuntoDiScelta() {
		return griglia[dim][dim];
	}// ultimoPuntoDiScelta

	@Override
	public Integer primaScelta( CellaGrattacieli c ) {
		return altMin;
	}// primaScelta

	@Override
	public Integer prossimaScelta( Integer alt ) {
		return alt+1;
	}// prossimaScelta

	@Override
	public Integer ultimaScelta( CellaGrattacieli c ) {
		return altMax;
	}// ultimaScelta

	@Override
	public boolean assegnabile( Integer alt, CellaGrattacieli c ) {
		int rigaCella = c.getRiga();
		int colCella = c.getColonna();
		for (int col = 1; col <= dim; col++) {
			if ( griglia[rigaCella][col].getValore() == alt ) return false;
		}
		for (int riga = 1; riga <= dim; riga++) {
			if ( griglia[riga][colCella].getValore() == alt ) return false;
		}
		return true;	
	}// assegnabile
	
	@Override
	public void assegna( Integer alt, CellaGrattacieli c ) {
		c.setValore(alt);
	}// assegna

	@Override
	public void deassegna( Integer s, CellaGrattacieli c ) {
		c.setValore(VAL_DEF);
	}// deassegna

	@Override
	public Integer ultimaSceltaAssegnata( CellaGrattacieli c ) {
		return c.getValore();
	}// ultimaSceltaAssegnata
	
	/* La particolare specifica di progetto ha reso necessario un override dello stesso metodo 
	 * già definito in upper class. */
	@Override
	public void risolvi() {
		CellaGrattacieli ps = primoPuntoDiScelta();
		Integer s = primaScelta(ps);
		boolean backtrack = false, fine = false;
		do {			
			while ( !backtrack && nrsoluzione < nummaxsoluzioni ) {
				if ( assegnabile(s, ps) ) {
					assegna(s, ps);
					if ( ps.equals(ultimoPuntoDiScelta()) && verificaAllPV()==true ) {
						++nrsoluzione;
						scriviSoluzione(nrsoluzione);
						deassegna(s, ps);
						if (!s.equals(ultimaScelta(ps)))
							s = prossimaScelta(s);
						else
							backtrack = true;
					} else {
						ps = prossimoPuntoDiScelta(ps,s);
						s = primaScelta(ps);
					}
				} else if ( !s.equals(ultimaScelta(ps)) )
					s = prossimaScelta(s);
				else
					backtrack = true;
			}
			fine = ps.equals(primoPuntoDiScelta())
					|| nrsoluzione == nummaxsoluzioni;
			while ( backtrack && !fine ) {
				ps = precedentePuntoDiScelta(ps);
				s = ultimaSceltaAssegnata(ps);
				deassegna(s, ps);
				if ( !s.equals(ultimaScelta(ps)) ) {
					s = prossimaScelta(s);
					backtrack = false;
				} else if ( ps.equals(primoPuntoDiScelta()) )
					fine = true;
			}
		} while ( !fine );
	}// risolvi
	
	@Override
	public void scriviSoluzione( int nr_sol ) {
		try {
			Memento m = this.createMemento();
			GiocaGrattacieli5.ggCT.saveMem(m,nr_sol);
		} catch (IOException e) { e.printStackTrace(); }
		if ( nr_sol == 1 )
			GiocaGrattacieli5.ggCT.graficaMemento(nr_sol);
	}// scriviSoluzione
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < griglia.length; i++) {
			for (int j = 0; j < griglia.length; j++) {
				sb.append(griglia[i][j].getValore()+" ");
			}
			sb.append("\n");
		}
		sb.append("\n\n");
		return sb.toString();
	}// toString
	
}// GrigliaGrattacieli
