/**
 * 
 */
package anno17_18.retidicalcolatori.SendReceiveSerializedStudente;

import java.io.Serializable;

/**
 * @author Giuseppe Longo
 *
 */
class Studente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int matricola;
	private String nome, cognome, corsoDiLaurea;
	
	Studente (int matricola, String nome, String cognome, String corsoDiLaurea) {
		this.matricola= matricola; 
		this.nome= nome;
		this.cognome= cognome; 
		this.corsoDiLaurea= corsoDiLaurea;
	}//Studente
	
	int getMatricola() { 
		return matricola;
	}//getMatricola
	
	String getNome() { 
		return nome;
	}//getNome
	
	String getCognome() { 
		return cognome; 
	}//getCognome
	
	String getCorsoDiLaurea() { 
		return corsoDiLaurea; 
	}//getCorsoDiLaurea
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "STUD. "+matricola
				+"\nNome: "+nome
				+"\nCognome: "+cognome
				+"\nCorso di Laurea: "+corsoDiLaurea;
	}//toString

}//Studente
