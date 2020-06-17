/**
 * 
 */
package anno17_18.retidicalcolatori.Esercizio2punto4;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author Giuseppe Longo
 *
 */
class TimeServerUDP extends Thread {
	
	static final int porta = 3575;
	private static DatagramSocket ds;

	///////////////////////////////////////////////
	
	private DatagramPacket dp;
	private byte[] buffer;
	private InetAddress clientAddress;
	private int clientPort;
	
	private String zone;
	private Calendar timeOfZone;
	private String response;
	
	
	private TimeServerUDP( DatagramPacket dp ) {
		this.dp = dp;
		buffer = new byte[256];
		zone = new String(dp.getData()).trim();
	}
	
	
	@Override
	public void run() {
		 
		try {
			System.out.println("TimeZone per "+zone);
			
			timeOfZone = Calendar.getInstance(TimeZone.getTimeZone(zone));
			response = "Time in "+zone+" -> "+timeOfZone.get(Calendar.HOUR) +":"+timeOfZone.get(Calendar.MINUTE);
			buffer = response.getBytes();
			
			clientAddress = dp.getAddress();
			clientPort = dp.getPort();
			dp = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
			
			ds.send(dp);
			
			System.out.println("RICHIESTA SODDISFATTA PER CLIENT CON PORTA "+dp.getPort());
			
		 } catch ( Exception e ) { System.out.println(e); }
		
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		try {
			
			ds = new DatagramSocket(porta);
			while ( true ) {
				byte[] buffer = new byte[256];
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
				ds.receive(dp);
				new TimeServerUDP(dp).start();
				System.out.println("RICEVUTA RICHIESTA DA CLIENT CON PORTA "+dp.getPort());
			}
			
		} catch ( Exception e) { System.out.println(e);}
		
	}

}
