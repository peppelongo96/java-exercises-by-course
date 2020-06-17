/**
 * 
 */
package anno17_18.retidicalcolatori.Appello23giugno2015.Esercizio1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Giuseppe Longo
 *
 */
class IndexServer  {
	
	static final int portaUDP = 3000;
	static final int portaTCP = 4000;

	private ConcurrentHashMap<String, ConcurrentHashMap<String, Set<Integer>>> archivi;
	//mappa<keyword, mappa<filanme, setIdStorage>>
	
	private class Indicizza extends Thread {
		
		private DatagramSocket ds;
		private DatagramPacket dp;
		private byte[] buffer;
	
		public void run() {
			try {
				ds = new DatagramSocket(portaUDP);
				while ( true ) {
					buffer = new byte[256];
					dp = new DatagramPacket(buffer, buffer.length);
					ds.receive(dp);
					int IDstorage = ds.getPort();
					String fileToString = new String(dp.getData());
					aggiornaIndex(IDstorage, fileToString);
				}
				
			} catch ( Exception e ) { System.out.println(e); }
		}
		
		private void aggiornaIndex( int IDstorage, String fileToString ) {
			StringTokenizer st = new StringTokenizer( fileToString, "[],");
			String filename = st.nextToken();
			while ( st.hasMoreTokens() ) {
				String keyword = st.nextToken();
				if ( !archivi.containsKey(keyword) ) {
					ConcurrentHashMap<String, Set<Integer>> mapStorage = new ConcurrentHashMap<>();
					Set<Integer> setIDstorage = new HashSet<>();
					setIDstorage.add(IDstorage);
					mapStorage.put(filename, setIDstorage);
					archivi.put(keyword, mapStorage);
				}
				else {
					if ( !archivi.get(keyword).containsKey(filename) ) {
						Set<Integer> setIDstorage = new HashSet<>();
						setIDstorage.add(IDstorage);
						archivi.get(keyword).put(filename, setIDstorage);
					}
					else 
						archivi.get(keyword).get(filename).add(IDstorage);
				}
			}
		}
	}
	
	private class Cerca extends Thread {
		
		private ServerSocket ss;
		private Socket s;
		private BufferedReader in;
		private PrintWriter out;
		
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			try {
				ss = new ServerSocket(portaTCP);
				while ( true ) {
					s = ss.accept();
					in = new BufferedReader(new InputStreamReader(s.getInputStream()));
					out = new PrintWriter(s.getOutputStream(), true);
					
					out.println("TI SEI CONNESSO ALL'INDEX-SERVER ");
					String filename = in.readLine();
					String keywords = in.readLine();
					StringTokenizer st = new StringTokenizer(keywords, "[],");
					boolean trovato = true;
					Set<Integer> risID = new HashSet<>();
					while ( st.hasMoreTokens() ) {
						String keyword = st.nextToken();
						if ( !archivi.containsKey(keyword) ) {
							trovato = false;
							break;
						}
						else if ( !archivi.get(keyword).containsKey(filename) ) {
							trovato = false;
							break;
						}
						else risID.addAll(archivi.get(keyword).get(filename));						
					}
					if ( trovato ) {
						out.println("LISTA SERVER-STORAGE CONTENENTI IL FILE CERCATO: ");
						out.println(risID);
					} else {
						out.println("IL FILE COSI' CERCATO NON E' PRESENTE");
						out.println("Riprova");
					}

					
					s.close();
					
				}
			} catch ( Exception e ) { System.out.println(e); }
		}
	}
	
	private void avviaIndexServer() {
		new Indicizza().start();
		new Cerca().start();
	}
	
	public static void main(String[] args) {
		 new IndexServer().avviaIndexServer();
	}
	

}
