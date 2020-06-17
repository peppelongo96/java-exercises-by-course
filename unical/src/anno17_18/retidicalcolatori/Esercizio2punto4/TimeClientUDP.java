/**
 * 
 */
package anno17_18.retidicalcolatori.Esercizio2punto4;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * @author Giuseppe Longo
 *
 */
class TimeClientUDP {

	private static InetAddress localhost;
	
	public static void main(String[] args) {
				
		DatagramSocket ds;
		DatagramPacket dp;
		byte[] buffer;
		
		String request;
		Scanner scan = new Scanner(System.in); 

		try {
			localhost = InetAddress.getLocalHost();
			
			ds = new DatagramSocket();
			
			System.out.print("Inserisci zona: ");
			request = scan.nextLine();
			buffer = request.getBytes();
			
			dp = new DatagramPacket(buffer, buffer.length, localhost, TimeServerUDP.porta);
			ds.send(dp);
			System.out.println("RICHIESTA INOLTRATA");
			
			buffer = new byte[256];
			dp = new DatagramPacket(buffer, buffer.length);
			ds.receive(dp);
			
			System.out.println(new String(dp.getData()));
			
		} catch ( Exception e ) { System.out.println(e);}
	}

}
