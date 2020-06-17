/**
 * 
 */
package anno17_18.retidicalcolatori.RequestSerializedStudent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Giuseppe Longo
 *
 */
class Client {

	private static final String localhost = "127.0.0.1";

	private static Socket s;
	private static ObjectOutputStream out;
	private static ObjectInputStream in;

	private static Scanner scan = new Scanner(System.in);
	private static int matCercata;
	private static Object risposta;

	public static void main(String[] args) {
		try {
			s = new Socket(localhost, ServerDatabaseStudenti.porta);
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());
			
			System.out.print((String)in.readObject());
			matCercata = scan.nextInt();
			out.writeObject(matCercata);
			System.out.println("Hai inviato la matricola. Attendi risposta...");
			risposta = in.readObject();
			try {
				System.out.println((Studente)risposta);
			} catch (Exception e) { System.out.println((String)risposta); }
		} catch ( Exception e ) { System.out.println(e); }
	}

}
