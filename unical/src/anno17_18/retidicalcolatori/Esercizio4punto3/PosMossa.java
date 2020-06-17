/**
 * 
 */
package anno17_18.retidicalcolatori.Esercizio4punto3;

/**
 * @author Giuseppe Longo
 *
 */
class PosMossa {
	
	private int riga, colonna;
	
	PosMossa( int riga, int colonna ) {
		if ( riga < 0 || colonna < 0 ) throw new IllegalArgumentException();
		this.riga = riga;
		this.colonna = colonna;
	}
	
	int getRiga() {
		return riga;
	}
	
	int getColonna() {
		return colonna;
	}

}
