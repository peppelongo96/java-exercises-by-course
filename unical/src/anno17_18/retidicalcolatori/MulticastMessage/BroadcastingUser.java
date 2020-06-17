/**
 * 
 */
package anno17_18.retidicalcolatori.MulticastMessage;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

/**
 * @author Giuseppe Longo
 *
 */
class BroadcastingUser {

	private static final String hostname = "230.0.0.1";
	private static InetAddress groupAddress;
	private static final int port = 3575;
	
	private MulticastSocket ms;
	
	private BroadcastingUser() {
		try {
			ms = new MulticastSocket(port);	
			groupAddress = InetAddress.getByName(hostname);
			ms.joinGroup(groupAddress);
		} catch (Exception e) { System.out.println(e); }
	}
	
	private class Sender extends Thread {
		
		private DatagramPacket dp;
		private byte[] buffer;
		private String message;
		private Scanner scan;
		
		private Sender() {
			scan = new Scanner(System.in);
		}
	
		@SuppressWarnings("static-access")
		public void run() {
			try {
				while( true ) {
					System.out.print("Messaggio da inviare -> ");
					message = scan.nextLine();
					buffer = message.getBytes();
					dp = new DatagramPacket(buffer, buffer.length, groupAddress, port);
					ms.send(dp);
					Thread.currentThread().sleep(1000);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}//run
		
	}
	
	private class Receiver extends Thread {
		
		private DatagramPacket dp;
		private byte[] buffer;
		private String message;
		
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			try {
				while( true ) {
					buffer = new byte[256];
					dp = new DatagramPacket(buffer, buffer.length);
					ms.receive(dp);
					message = new String(dp.getData());
					System.out.println("\n"+message);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}//run
		
	}
	
	private void generaUser() {
		new Sender().start();
		new Receiver().start();
	}
	
	public static void main(String[] args) {
		 
		new BroadcastingUser().generaUser();
		
	}

}
