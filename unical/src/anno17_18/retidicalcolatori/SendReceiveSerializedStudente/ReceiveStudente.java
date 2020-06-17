/**
 * 
 */
package anno17_18.retidicalcolatori.SendReceiveSerializedStudente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Giuseppe Longo
 *
 */
class ReceiveStudente {

	private static final String localhost = "127.0.0.1";

	private static Socket s;
	private static ObjectOutputStream out;
	private static ObjectInputStream in;
	
	private static Studente stud;

	public static void main(String[] args) {
		
		try {
			s = new Socket(localhost, SendStudente.porta);
			in = new ObjectInputStream(s.getInputStream());
			out = new ObjectOutputStream(s.getOutputStream());
			
			System.out.println((String)in.readObject());
			
			stud = (Studente)in.readObject();
			System.out.println("3-> Ecco lo studente ricevuto:\n"+stud);
			
			out.writeObject("4-> Lo studente è stato ricevuto correttamente.");
			
		} catch ( Exception e ) { System.out.println(e); }
		
	}

}
