/**
 * 
 */
package anno17_18.retidicalcolatori.EchoComunication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Giuseppe Longo
 *
 */
class EchoClient {

	/**
	 * @param args
	 */
	
	private static final String localhost = "127.0.0.1";
	
	private static Socket s;
	private static BufferedReader in;
	private static PrintWriter out;
	
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		try {
			s = new Socket(localhost, EchoServer.porta);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(),true);
			
			System.out.println(in.readLine());
			
			while (true) {
				System.out.print("Invia: ");
				String mex = scan.nextLine();
				out.println(mex);
				System.out.println(in.readLine());
			}
		} catch(Exception e){ System.err.println(e); }
	}
}
