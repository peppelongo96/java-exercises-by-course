/**
 * 
 */
package anno17_18.retidicalcolatori.Esercizio2punto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


/**
 * @author Giuseppe Longo
 *
 */
class ChatUser {
	
	private static Set<Integer> listaClient = new HashSet<>();
	
	private static Random genPorte = new Random();
	
	///////////////////////////////////////////////////////////////////////////////////
	
	private class Server  extends Thread {
		
		private BufferedReader in;
		private PrintWriter out;
		private Scanner scan = new Scanner(System.in);
		private Socket s;
		private int porta;
		
		private Server( Socket s, int porta ) throws IOException {
			this.s = s; this.porta = porta;
			in = new BufferedReader (new InputStreamReader(this.s.getInputStream()));
			out = new PrintWriter (this.s.getOutputStream(),true);
		}
		
		@Override
		public void run() {
			
			try {
				
				//Messaggio di benvenut al client
				out.println("Ciao! Io sono il server "+porta+"\nCONVERSAZIONE AVVIATA");
				
				//Messaggio di benvenuto dal client
				System.out.println(in.readLine());
				
				while (true) {
					System.out.print("Invia: ");
					String mex = scan.nextLine();
					out.println(mex);
					System.out.println(in.readLine());
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	private static final String localhost = "127.0.0.1";
	
	private class Client extends Thread {
		
		private Socket s;
		private int porta;
		private BufferedReader in;
		private PrintWriter out;
		private Scanner scan = new Scanner(System.in);
		
	
		private Client(int porta) {
			this.porta = porta;
		}
		
		@Override
		public void run() {
			try {
				int portaScelta = 0;
				while ( portaScelta==0 ) {
					System.out.println("Ciao! Sono il client "+porta+"\nUsers attualmente disponibili: ");
					System.out.println(listaClient);
					System.out.print("Inserisci numero, A per aggiornare lista: "); String scelta = scan.nextLine();
					try { portaScelta = Integer.parseInt(scelta); }
					catch ( Exception e ) { continue; }
				}
				s = new Socket(localhost, portaScelta);
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				out = new PrintWriter(s.getOutputStream(),true);
				
				//Messaggio di benvenuto dal server
				System.out.println(in.readLine());
				
				//Messaggio di benvenut al server
				out.println("Ciao! Io sono l'utente "+porta+"\nCONVERSAZIONE AVVIATA");

				while (true) {
					System.out.print("Invia: ");
					String mex = scan.nextLine();
					out.println(mex);
					System.out.println(in.readLine());
				}
			
			}catch(Exception e){ System.err.println(e); }
		}
		
	}//Client
	
	
	////////////////////////////////////////////////////
	
	void generaUser() {
		
		try {

			int porta = genPorte.nextInt(1001)+2000;

			//avvio parte client
			listaClient.add(porta);
			new Client(porta).start();

			//avvio parte server
			ServerSocket ss = new ServerSocket(porta);
			while ( true ) {
				Socket sss = ss.accept();
				new Server(sss, porta).start();
			}

		} catch (Exception e) { System.out.println(e); }
		
	}

	public static void main(String[] args) {
		 
		new ChatUser().generaUser();
	
	}
	

}
