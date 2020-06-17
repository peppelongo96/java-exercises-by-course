/**
 * 
 */
package anno17_18.retidicalcolatori.Appello14luglio2015.Esercizio4;

import java.util.HashMap;

/**
 * @author Giuseppe Longo
 *
 */
class Esame {
	
	private String nomeEsame;
	private HashMap<Integer, Integer> voti; //<matricola, voto>
	
	Esame ( String nomeEsame, HashMap<Integer, Integer> voti ) {
		this.nomeEsame = nomeEsame;
		this.voti = voti;
	}
	
	String getNomeEsame() {
		return nomeEsame;
	}
	
	HashMap<Integer, Integer> getVoti() {
		return voti;
	}
	
}
