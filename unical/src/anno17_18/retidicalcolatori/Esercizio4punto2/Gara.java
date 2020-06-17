/**
 * 
 */
package anno17_18.retidicalcolatori.Esercizio4punto2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Giuseppe Longo
 *
 */
class Gara {
	
	private final int numCavalli, ID;
	private final OrarioGara og;
	private boolean noBet = false;
	
	private Map<Scommessa, Integer> scommesse; //<scommessa,portaClient>
	
	Gara(int ID, int numCavalli, OrarioGara og ) {
		this.numCavalli = numCavalli;
		this.og = og;
		this.ID = ID;
		scommesse = new HashMap<>();
	}
	
	boolean addScommessa( int portaClient, Scommessa bet ) {
		if ( noBet==true || bet.getNumCavallo() > numCavalli )
			return false;
		scommesse.put(bet, portaClient);
		return true;
	}
	
	OrarioGara getOrarioGara() { return og; }
	
	Map<Scommessa, Integer> getScommesse() { return scommesse; }
	
	int getNumCavalli() { return numCavalli; }
	
	int getID() { return ID; } 

	boolean getNoBet() { return noBet; }
	void setNoBet(boolean val) { noBet = val; }
	
	
}
