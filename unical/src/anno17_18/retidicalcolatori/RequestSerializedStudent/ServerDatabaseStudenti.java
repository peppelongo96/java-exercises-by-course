/**
 * 
 */
package anno17_18.retidicalcolatori.RequestSerializedStudent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * @author Giuseppe Longo
 *
 */
class ServerDatabaseStudenti extends Thread {

	static final int porta = 3575;
	private static ServerSocket ss;
	
	private static DatabaseStudente dbs = new DatabaseStudente();
	private static int numStudenti = 0;
	private static Random r = new Random();
		
	public static void main(String[] args) {
		
		//GENERA DATABASE
		System.out.println("GENERO DATABASE...");
		while( numStudenti<50 ) {
			int m = r.nextInt(2001);
			String n = Character.toString((char)(r.nextInt(66)+25));
			String c = Character.toString((char)(r.nextInt(66)+25));
			String cdl = Character.toString((char)(r.nextInt(66)+25));
			
			Studente s = new Studente(m, n, c, cdl);
			dbs.addStudente(s);
			
			System.out.println("Aggiunto stud. "+s.getMatricola());
			numStudenti++;
		}
		
		System.out.println("DATABASE GENERATO");
		
		try {
			ss = new ServerSocket(porta);
			while ( true ) {
				Socket s = ss.accept();
				new ServerDatabaseStudenti(s).start();
			}
		} catch ( Exception e ) { System.out.println(e); }

	}//main
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	
	/**
	 * 
	 */
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket s;
	
	private Studente studRichiesto;
	
	ServerDatabaseStudenti(Socket s) throws IOException {
		this.s = s;
		out = new ObjectOutputStream(this.s.getOutputStream());
		in = new ObjectInputStream(this.s.getInputStream());
	}
	
	@Override
	public void run() {
		
		try {
			out.writeObject("Ti sei connesso al database degli studenti.\n"
					+ "Inserisci adesso la matricola dello studente da cercare: ");
			studRichiesto = dbs.getStudenteFromMatricola((Integer)in.readObject());
			if ( studRichiesto==null ) 
				out.writeObject("Siamo spiacenti ma non è presente nessun studente con la matricola inviata.");
			else out.writeObject(studRichiesto);
		} catch ( Exception e ) { System.out.println(e); }
		
	}

}
