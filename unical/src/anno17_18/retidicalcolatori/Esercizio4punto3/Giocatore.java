/**
 * 
 */
package anno17_18.retidicalcolatori.Esercizio4punto3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Giuseppe Longo
 *
 */
class Giocatore {

	private static final String localhost = "127.0.0.1";

	

	public static void main(String[] args) {
		
		try {
			
			Socket s = new Socket(localhost, ServerTris.porta);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream(),true);
			Scanner scan = new Scanner(System.in);
			
			
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			
			String scelta = scan.nextLine().trim().toUpperCase();
			int G1oG2 = 1;
			out.println(scelta);
			if ( scelta.equals("A") ) { //attendiAvversario
				System.out.println(in.readLine());
				while( !in.readLine().equals("GO") ) {}
				G1oG2 = 2;
			}
			
			System.out.println(in.readLine());
			
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			System.out.println(in.readLine());
			
			while ( in.readLine().equals("ANCORA_MOSSE") ) {
				System.out.println(in.readLine());
				String turno = in.readLine();
				if ( turno.equals("G1") && G1oG2==1 ) {
					System.out.println(in.readLine());
					String mossa = scan.nextLine().trim();
					out.println(mossa);
				} else if ( turno.equals("G2") && G1oG2==2 ) {
					System.out.println(in.readLine());
					String mossa = scan.nextLine().trim();
					out.println(mossa);
				}
				System.out.println(in.readLine());
				System.out.println(in.readLine());
				System.out.println(in.readLine());
				System.out.println(in.readLine());
				System.out.println(in.readLine());
			}
			
			System.out.println(in.readLine());
			s.close();
			scan.close();
			
			
		} catch ( Exception e) { System.out.println(e);}


	}

}
