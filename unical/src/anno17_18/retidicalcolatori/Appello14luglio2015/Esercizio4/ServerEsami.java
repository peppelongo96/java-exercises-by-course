/**
 * 
 */
package anno17_18.retidicalcolatori.Appello14luglio2015.Esercizio4;

import java.util.HashMap;

/**
 * @author Giuseppe Longo
 *
 */
class ServerEsami {
	
	private HashMap<Data, Esame> archivio;
	
	/**
	 * 
	 */
	ServerEsami() {
		archivio = new HashMap<>();
	}
	
	void aggiungiEsame ( Data d, Esame e ) {
		archivio.put(d, e);
	}
	
	int votoStudente ( String nomeEsame, Data dataEsame, int matricola ) {
		return archivio.get(dataEsame).getVoti().get(matricola);
	}
	
	String esameGiorno ( Data d ) {
		return archivio.get(d).getNomeEsame();
	}

}
