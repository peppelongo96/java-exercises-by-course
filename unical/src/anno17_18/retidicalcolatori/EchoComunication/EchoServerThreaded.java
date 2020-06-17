/**
 * 
 */
package anno17_18.retidicalcolatori.EchoComunication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Giuseppe Longo
 *
 */
class EchoServerThreaded extends Thread {
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */

	static final int porta = 8196;

	private static ServerSocket ss;
	
	public static void main(String[] args) {
		try{
			// Inizializza server socket 
			ss = new ServerSocket(porta); //crea il socket server sul quale i client si connetteranno
			while (true) {
				Socket s = ss.accept();
				
				//Il server avviare un processo per ogni client s
				new EchoServerThreaded(s).start();
			}
			 //il server socket viene messo in attesa del client socket. Questa invocazione
			// è bloccante per il resto del codice
		}catch(Exception e){ System.err.println(e); }
	}//main
	
	////////////////////////////////////////////////////////

	private BufferedReader in;
	private PrintWriter out;
	private Socket s;
	
	/**
	 * @throws IOException 
	 * 
	 */
	EchoServerThreaded(Socket s) throws IOException {
		this.s = s;
		in = new BufferedReader (new InputStreamReader(s.getInputStream()));
		out = new PrintWriter (s.getOutputStream(),true);
	}

	@Override
	public void run() {
		try{
		
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
	}

}
