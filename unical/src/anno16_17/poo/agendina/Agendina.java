package anno16_17.poo.agendina;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.StringTokenizer;
/**
 * Tipo di dato astratto che descrive un'agendina telefonica.
 * Gli elementi sono di tipo Nominativo. Non si ammettono le
 * omonimie. L'agendina e' supposta mantenuta ordinata
 * per cognome crescente e a parita' di cognome per nome crescente.
 * @author Libero Nigro
 */
public interface Agendina extends Iterable<Nominativo>{

	/**
	 * Restituisce il numero di nominativi dell'agenda.
	 * @return il numero di nominativi in agenda.
	 */
	default int size(){
		int c=0;
		for( Iterator<Nominativo> it=iterator(); it.hasNext(); it.next(), c++ );
		return c;
	}//size

	/**
	 * Svuota il contenuto dell'agendina.
	 */
	default void svuota(){
		Iterator<Nominativo> it=iterator();
		while( it.hasNext() ){
			it.next(); it.remove();
		}
	}//svuota

	/**
	 * Aggiunge un nominativo all'agenda. Non si ammettono
	 * le omonimie. L'aggiunta avviene in ordine alfabetico crescente
	 * del cognome ed a parita' di cognome in ordine alfabetico del nome.
	 * @param n il nominativo da aggiungere
	 */
	void aggiungi( Nominativo n );

	/**
	 * Rimuove un nominativo dall'agenda.
	 * @param n il nominativo da rimuovere dall'agenda.
	 */
	default void rimuovi( Nominativo n ){
		Iterator<Nominativo> it=iterator();
		while( it.hasNext() ){
			Nominativo x=it.next();
			if( n.equals(x) ){ it.remove(); break; }
		}
	}//rimuovi

	/**
	 * Cerca un nominativo uguale ad n.
	 * @param n il nominativo da cercare, significativo solo per nome e cognome.
	 * @return il nominativo dell'agenda uguale ad n o null se n non e' in agenda.
	 */
	default Nominativo cerca( Nominativo n ){
		Iterator<Nominativo> it=iterator();
		while( it.hasNext() ){
			Nominativo x=it.next();
			if( n.equals(x) ){ return x; }
		}
		return null;
	}//cerca

	/**
	 * Cerca un nominativo nell'agenda, di assegnato prefisso e numero di telefono.
	 * @param prefisso
	 * @param telefono
	 * @return il nominativo trovato o null
	 */
	default Nominativo cerca( String prefisso, String telefono ){
		Iterator<Nominativo> it=iterator();
		while( it.hasNext() ){
			Nominativo x=it.next();
			if( prefisso.equals(x.getPrefisso()) &&
				telefono.equals(x.getTelefono()) ){ return x; }
		}
		return null;
	}//cerca

	/**
	 * Salva il contenuto dell'agenda su file.
	 * @param nomeFile il nome esterno del file per il salvataggio.
	 * @throws IOException
	 */
	default void salva(String nomeFile) throws IOException{
		PrintWriter pw=new PrintWriter( new FileWriter(nomeFile) );
		for( Nominativo n: this ) pw.println( n );
		pw.close();
	}//salva

	/**
	 * Ripristina il contenuto dell'agenda, a partire da un file.
	 * @param nomeFile il nome esterno del file da cui attingere.
	 * @throws IOException es. se il file non esiste
	 */
	default void ripristina(String nomeFile) throws IOException{
		BufferedReader br=new BufferedReader( new FileReader(nomeFile) );
		svuota();
		String linea=null;
		for(;;){
			linea=br.readLine();
			if( linea==null ) break;
			StringTokenizer st=new StringTokenizer( linea, " -" );
			String co=st.nextToken(), no=st.nextToken(), pr=st.nextToken(), te=st.nextToken();
			this.aggiungi( new Nominativo(co,no,pr,te) );
		}
		br.close();
	}//ripristina

}//Agendina