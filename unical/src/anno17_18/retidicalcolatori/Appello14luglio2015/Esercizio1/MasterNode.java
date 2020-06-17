/**
 * 
 */
package anno17_18.retidicalcolatori.Appello14luglio2015.Esercizio1;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Giuseppe Longo
 *
 */
class MasterNode {

	static final int portaPerUtenti = 2000;
	static final int portaPerOpServer = 3000;
	

	private static ServerSocket ss;
	
	public static void main(String[] args) {
		try {
			ss = new ServerSocket(portaPerUtenti);
			while ( true ) {
				Socket s = ss.accept();
				new GestisciUtenti(s).start();
			}
		} catch ( Exception e ) { System.out.println(e); }


	}
	
	private static class GestisciUtenti extends Thread {
		
		private Processo richiesta;
		private int portaUtente;
		private Callable<String>[] arrayTask;
		private ExecutorService esecutore;
		
		private ObjectInputStream in;
		
		private Socket s;
		
		private GestisciUtenti ( Socket s ) {
			this.s = s;
			esecutore = Executors.newSingleThreadExecutor();
			
		}
	
		public void run() {
			try {
				in = new ObjectInputStream(s.getInputStream());
				portaUtente = in.readInt();
				richiesta = (Processo)in.readObject();
				arrayTask = richiesta.getArrayTask();
				for (int i = 0; i < arrayTask.length; i++) {
					esecutore.submit( new AvviaOp(i, arrayTask[i]) );
				}
				
				
				
			} catch ( Exception e ) { System.err.println(e); }
			
		}
		
		private static class AvviaOp implements Runnable {
			
			private int ID;
			
			private Callable<String> opDaFare;

			AvviaOp ( int ID, Callable<String> opDaFare ) {
				this.ID = ID;
				this.opDaFare = opDaFare;
			}
			
			public void run() {
				//new OpServer(ID, opDaFare).start();
			}
			
		}
	}

}
