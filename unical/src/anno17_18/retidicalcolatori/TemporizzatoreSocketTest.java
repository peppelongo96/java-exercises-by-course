/**
 * 
 */
package anno17_18.retidicalcolatori;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Giuseppe Longo
 *
 */
class TemporizzatoreSocketTest {
	
	static String URL = "www.dimes.unical.it";
	static int porta = 80;
	static int timeout = 100000; //espresso in millisecondi (10 sec)
	static Socket s;

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		s = SocketOpener.openSocket(URL, porta, timeout);
		if(s == null)
			System.out.println("Il socket non ha rievuto risposta e quindi non si è aperto, entro "+timeout+" millisecondi");
		else
			System.out.println(s);
	}
	
}//TemporizzatoreSocket
	
class SocketOpener extends Thread{

	private String host;
	private int port;
	private Socket socket;

	private SocketOpener(String host, int port) {
		this.host= host; this.port= port; socket= null;
	}

	static Socket openSocket(String host, int port, int timeout) {
		SocketOpener opener= new SocketOpener(host, port);
		opener.start();
		try{
			opener.join(timeout); //attendi risposta per un massimo di timeout
		} catch (InterruptedException e) { System.err.println(e); }
		return opener.getSocket();
	}

	private Socket getSocket() { return socket; }

	public void run() {
		try{
			socket = new Socket(host, port);
		} catch (IOException e) { System.err.println(e); }
	}
}//SocketOpener

