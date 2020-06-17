/**
 * 
 */
package anno17_18.retidicalcolatori.SendReceiveSerializedStudente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Giuseppe Longo
 *
 */
class SendStudente {
	
	
	static final int porta = 3575;
	
	private static ServerSocket ss;
	private static Socket s;
	private static ObjectOutputStream out;
	private static ObjectInputStream in;
	
	private static Studente stud = 
			new Studente(175983, "Giuseppe", "Longo", "Ing. Informatica");
	 
	public static void main(String[] args) {
		
		try {
			ss = new ServerSocket(porta);
			s = ss.accept();
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());
			
			out.writeObject("1-> Salve. Si è connesso alla porta "+porta+". Sta ricevendo lo studente.");
			
			out.writeObject(stud);
						
			System.out.println("2-> Studente inviato");
			System.out.println((String)in.readObject());
			
		} catch ( Exception e ) { System.out.println(e); }
	}

}
