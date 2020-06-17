/**
 * 
 */
package anno17_18.retidicalcolatori.RequestSerializedStudent;

import java.util.LinkedList;

/**
 * @author Giuseppe Longo
 *
 */
class DatabaseStudente {
	
	private LinkedList<Studente> datStud;
	
	/**
	 * 
	 */
	DatabaseStudente() {
		datStud = new LinkedList<>();
	}
	
	Studente getStudenteFromMatricola ( int matricola ) {
		for (Studente s : datStud ) 
			if ( s.getMatricola()==matricola )
				return s;
		return null;
	}

	Studente addStudente ( Studente s ) {
		datStud.add(s);
		return s;
	}

}//DatabaseStudente
