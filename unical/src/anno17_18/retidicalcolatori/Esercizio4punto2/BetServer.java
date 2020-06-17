/**
 * 
 */
package anno17_18.retidicalcolatori.Esercizio4punto2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author Giuseppe Longo
 *
 */
class BetServer {

	/**
	 * @param args
	 */
	
	static final String localhost = "127.0.0.1";	
	static final int portaScommesse = 8001;

	static final String hostnameCast = "230.0.0.1";
	static final int portaCast = 8002;
	
	

	public static void main(String[] args) {

		new BetServer(2).avviaBetServer();

	}

	
	///////////////////////////////////////////////////////////////
	
	private Gara[] listaGare;
	private Scanner scan = new Scanner(System.in);
	
	BetServer ( int numeroGare ) {
		listaGare = new Gara[numeroGare];
		for (int i = 0; i < listaGare.length; i++) {
			System.out.println("GARA "+i+"");
			System.out.print("- Numero cavalli: "); 
			int numCavalli = scan.nextInt();
			scan = new Scanner(System.in);
			System.out.print("- Inizio gara: ");
			String orarioGara = scan.nextLine();
			StringTokenizer st = new StringTokenizer(orarioGara, ":");
			int ora = Integer.parseInt(st.nextToken());
			int minuti = Integer.parseInt(st.nextToken());
			System.out.println("- Inizio scommesse: "+(ora-1)+":"+minuti);
			listaGare[i] = new Gara(i, numCavalli, new OrarioGara(ora, minuti));
			System.out.println();
		}
		System.out.println("BETTER ABILITATI");
	}
	
	private void avviaBetServer() {
		
		new BetAccepter().start();
		for (int i = 0; i < listaGare.length; i++) {
			new BetDenyer(listaGare[i]).start();
		}
		
	}
	
	
	///////////////////////////////////////////////////////////

	
	private class BetAccepter extends Thread {
		
		private ServerSocket ss;
		
		@Override
		public void run() {
			try {
				ss = new ServerSocket(portaScommesse);
				
				while ( true ) {
					Socket s = ss.accept();
					new BetDispatcher(s).start();
				}

			} catch ( Exception e ) { System.out.println(e); }  
		}
		
		private class BetDispatcher extends Thread {
			
			Socket s;
			private ObjectOutputStream out;
			private ObjectInputStream in;
			
			private BetDispatcher( Socket s ) {
				this.s = s;
			}
			
			@Override
			public void run() {
				try {
					while ( true ) {
						out = new ObjectOutputStream(s.getOutputStream());
						in = new ObjectInputStream(s.getInputStream());
						Scommessa bet = (Scommessa)in.readObject();
						int numGara = bet.getGara();
						if ( numGara > listaGare.length || listaGare[numGara] == null || listaGare[numGara].addScommessa(s.getPort(), bet) == false ) 
							out.writeObject("SCOMMESSA RIFIUTATA");
						else 
							out.writeObject("SCOMMESSA ACCETTATA");
					}
				} catch ( Exception e ) { System.out.println(e); }
			}
			
		}
	}//BetAccepter
	
	private class BetDenyer extends Thread {
		
		private DatagramPacket dp;
		private byte[] buffer;		
		private MulticastSocket ms;
		private InetAddress groupAddress;
		
		private Gara g;
		private int cavalloVincente;
		private int mulScommessa = 12;
		private Random r = new Random();
		
		private BetDenyer(Gara g) {
			this.g = g;
			buffer = new byte[256];
		}
		
		@Override
		public void run() {
			try {
				ms = new MulticastSocket(portaCast);	
				groupAddress = InetAddress.getByName(hostnameCast);
				OrarioGara termina = new OrarioGara(g.getOrarioGara().getOra()+1, g.getOrarioGara().getMinuti());
//				while ( !termina.equals(g.getOrarioGara()) ) {
//					Thread.currentThread().sleep(1000);
//					g.getOrarioGara().avanza();
//				}
				Thread.currentThread().sleep(60000);
				g.setNoBet(true);
				buffer = calcolaVincitore().getBytes();
				dp = new DatagramPacket(buffer, buffer.length, groupAddress, portaCast);
				ms.send(dp);
				System.out.println("\n*FINE GARA "+g.getID()+"*\n");
				listaGare[g.getID()] = null;
			} catch (Exception e) { System.out.println(e); }
		}
		
		private String calcolaVincitore() {
			cavalloVincente = r.nextInt(g.getNumCavalli());
			Set<Scommessa> listaScommesse = g.getScommesse().keySet();
			Iterator<Scommessa> it = listaScommesse.iterator();
			StringBuilder sb = new StringBuilder();
			sb.append("*PER LA GARA "+g.getID()+" VINCE IL CAVALLO N° "+cavalloVincente);
			sb.append("\nECCO I VINCITORI:");
			while ( it.hasNext() ) {
				Scommessa scommessaCur = it.next();
				if ( scommessaCur.getNumCavallo() == cavalloVincente ) {
					sb.append("\n- Porta "+g.getScommesse().get(scommessaCur)+" vince "+scommessaCur.getPuntata()*mulScommessa+" euro\n");
				}
			}
			return sb.toString();
		}
	}
	
}
