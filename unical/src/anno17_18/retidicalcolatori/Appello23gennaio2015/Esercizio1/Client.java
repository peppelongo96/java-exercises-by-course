/**
 * 
 */
package anno17_18.retidicalcolatori.Appello23gennaio2015.Esercizio1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * @author Giuseppe Longo
 *
 */
class Client {
	
	public static void main(String[] args) {
		try {
			Scanner scan = new Scanner(System.in);

			DatagramSocket ds = new DatagramSocket();
			System.out.print("INSERRISCI NOME SERVIZIO: ");
			byte[] buffer = scan.nextLine().trim().getBytes();
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), Registro.portaUDP);
			ds.send(dp);
			buffer = new byte[256];
			dp = new DatagramPacket(buffer, buffer.length);
			ds.receive(dp);
			System.out.println(new String(dp.getData()));
			ds.close();
		} catch ( Exception e ) { System.out.println(e); }
	}

}
