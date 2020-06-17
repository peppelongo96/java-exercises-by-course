/**
 * 
 */
package anno17_18.retidicalcolatori.Esercizio4punto2;

import java.io.Serializable;

/**
 * @author Giuseppe Longo
 *
 */
class Scommessa implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numCavallo, puntata, gara;
	
	Scommessa ( int numCavallo, int puntata, int gara ) {
		this.gara = gara;
		this.puntata = puntata;
		this.numCavallo = numCavallo;
	}
	
	int getGara() { return gara; }
	int getNumCavallo() { return numCavallo; }
	int getPuntata() { return puntata; }

}
