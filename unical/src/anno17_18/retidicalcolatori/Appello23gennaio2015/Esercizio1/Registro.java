/**
 * 
 */
package anno17_18.retidicalcolatori.Appello23gennaio2015.Esercizio1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Giuseppe Longo
 *
 */
class Registro {
	
	static final int portaUDP = 2301;
	private DatagramSocket ds;
	private DatagramPacket dp;
	private byte[] buffer;
	
	private ConcurrentHashMap<String, String> registro;
	//<nome_servizio, ind_IP-porta>
		
	private Registro() {
		registro = new ConcurrentHashMap<>();
	}
	
	private void addService( String nomeSer, String indIP, String porta ) {
		registro.put(nomeSer.trim(), indIP.trim()+" - "+porta.trim());
	}
	
	private void avviaRegistro() {
		try {
			ds = new DatagramSocket(portaUDP);
			buffer = new byte[256];
			
			addService("Il pisciolo di Salvatore", "11.1.1.11.", "69");
			
			while ( true ) {
				dp = new DatagramPacket(buffer, buffer.length);
				ds.receive(dp);
				new GestisciClient(dp).start();
			}
		} catch ( Exception e ) { System.out.println(e); } 
	}
	
	private class GestisciClient extends Thread {
		
		private int portaClient;
		private InetAddress indirizzoClient;
		private String richiesta;
		private String risposta;
		
		private DatagramSocket ds;
		private DatagramPacket dp;
		private byte[] buffer;
				
		private GestisciClient ( DatagramPacket dp ) {
			portaClient = dp.getPort();
			indirizzoClient = dp.getAddress();
			richiesta = new String(dp.getData());
			
		}
		
		public void run() {
			try {
				risposta = registro.get(richiesta.trim());
				if ( risposta==null ) risposta = "unknown service requested";
				buffer = risposta.getBytes();
				ds = new DatagramSocket();
				dp = new DatagramPacket(buffer, buffer.length, indirizzoClient , portaClient);
				ds.send(dp);	
			} catch ( Exception e ) { System.out.println(e); } 
		}
	
	}
	
	public static void main(String[] args) {
		Registro r = new Registro();
		r.avviaRegistro();
	}

}
