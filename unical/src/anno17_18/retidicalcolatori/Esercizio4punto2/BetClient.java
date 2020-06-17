/**
 * 
 */
package anno17_18.retidicalcolatori.Esercizio4punto2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Giuseppe Longo
 *
 */
class BetClient {

	
	private class Scommetti extends Thread {
		
		private Socket s;
		private ObjectOutputStream out;
		private ObjectInputStream in;
		
		private  Scanner scan = new Scanner(System.in);
				
		@Override
		public void run() {
			try {		
				while ( true ) {
					s = new Socket(BetServer.localhost, BetServer.portaScommesse);
					out = new ObjectOutputStream(s.getOutputStream());
					in = new ObjectInputStream(s.getInputStream());		
					System.out.println("SCOMMESSA");
					System.out.print("- N° cavallo: "); int numCavallo = scan.nextInt();
					System.out.print("- Puntata: "); int puntata = scan.nextInt();
					System.out.print("- N° gara: "); int numGara = scan.nextInt();
					out.writeObject(new Scommessa(numCavallo, puntata, numGara));
					System.out.println("\n"+(String)in.readObject()+"\n");
				}	
			} catch ( Exception e ) { System.err.println(e); }
		}
		
	}
	
	private class TracciaVincitori extends Thread {
		
		private MulticastSocket ms;
		private InetAddress groupAddress;
		
		private DatagramPacket dp;
		private byte[] buffer;
		private String message;
		
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			try {
				ms = new MulticastSocket(BetServer.portaCast);	
				groupAddress = InetAddress.getByName(BetServer.hostnameCast);
				ms.joinGroup(groupAddress);
				
				while( true ) {
					buffer = new byte[256];
					dp = new DatagramPacket(buffer, buffer.length);
					ms.receive(dp);
					message = new String(dp.getData());
					System.out.println("\n"+message+"\n");
				}
			} catch (Exception e) { System.out.println(e); }
		}//run
	}
	
	private void generaBetClient() {
		new Scommetti().start();
		new TracciaVincitori().start();
	}

	public static void main(String[] args) {
		
		new BetClient().generaBetClient();

	}
}
