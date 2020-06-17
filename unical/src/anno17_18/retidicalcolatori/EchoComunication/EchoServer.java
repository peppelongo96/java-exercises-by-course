/**
 * 
 */
package anno17_18.retidicalcolatori.EchoComunication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Giuseppe Longo
 *
 */
class EchoServer {

	/**
	 * @param args
	 */
	
	static final int porta = 8196;
	
	private static ServerSocket ss;
	private static Socket s;
	private static BufferedReader in;
	private static PrintWriter out;
	 
	public static void main(String[] args) {
		
		try{
			// Inizializza server socket e lettore/scrittore sullo stesso
			ss = new ServerSocket(porta); //crea il socket server sul quale i client si connetteranno
			
			s = ss.accept(); //il server socket viene messo in attesa del client socket. Questa invocazione
							 // è bloccante per il resto del codice
			
			in = new BufferedReader (new InputStreamReader(s.getInputStream()));
			out = new PrintWriter (s.getOutputStream(),true); //il true permette l'autoflush
			
			out.println("Hello!Enter BYE toexit.");
			
			boolean done = false;
			while( !done ){
				String line = in.readLine();
				out.println("Echo:"+ line);
				String trimmedLine = line.trim();
				if(trimmedLine.equals("BYE")) {
					s.close();
					System.out.println("Il server ha chiuso la connessione col client");
				}
				if ( trimmedLine.equals("EXIT") ) {
					done = true;
					s.close();
					ss.close();
					System.out.println("Il server ha chiuso tutta la comunicazione");
				}
			}
		}catch(Exception e){ System.err.println(e); }
	}//main
}
