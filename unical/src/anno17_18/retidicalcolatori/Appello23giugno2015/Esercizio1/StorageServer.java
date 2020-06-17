/**
 * 
 */
package anno17_18.retidicalcolatori.Appello23giugno2015.Esercizio1;

import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * @author Giuseppe Longo
 *
 */
class StorageServer extends Thread {
	
	final static int portaTCP = 2000;
	private static InetAddress localhostUDP;
	
	//////////////////////////////////////////////////////
	
	private int ID;
	private LinkedList<File> archivio = new LinkedList<>();	
	private File f;
	private String toIndexServer;
	
	private ServerSocket ss;
	private Socket s;
	
	private DatagramSocket ds;
	private DatagramPacket dp;
	byte[] buffer;
	
	private ObjectInputStream in;
	private PrintWriter out;
	
	public void run() {
		try {		
			ss = new ServerSocket(portaTCP);
			localhostUDP = InetAddress.getLocalHost();
			ds = new DatagramSocket();
			ID = ds.getLocalPort();

			while ( true ) {
				
				s = ss.accept();
				in = new ObjectInputStream(s.getInputStream());
				out = new PrintWriter(s.getOutputStream(),true);

				out.println("TI SEI CONNESSO AL SERVER "+ID);
				f = (File)in.readObject();
				archivio.add(f);
								
				toIndexServer = f.getFilename()+f.stampaKeywords().trim();
				buffer = toIndexServer.getBytes();
				dp = new DatagramPacket(buffer, buffer.length, localhostUDP, IndexServer.portaUDP);
				ds.send(dp);
				
				
			}
		} catch ( Exception e ) { System.out.println(e); }
	}
	
	boolean aggiungiFile( File f ) {
		return archivio.add(f);
	}
	
	int getID() {
		return ID;
	}
	
	private void avviaStorageServer() {
		new StorageServer().start();
	}
	
	public static void main(String[] args) {
		new StorageServer().avviaStorageServer();
	}
}
