/**
 * 
 */
package anno17_18.retidicalcolatori;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Giuseppe Longo
 *
 */

class PaginaWebTest {
	
	static String URL = "repubblica.it";
	static Socket s;
	
	public static void main(String[] args) {
		try{
			s = new Socket(URL , 80); //socket predisposto alla comunicazione via web
			
			//Lettore/Scrittore della richiesta sul socket s
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			//Procedura di richiesta
			out.println("GET / HTTP/1.1");
			out.println("Host: "+URL);
			out.println();
			
			//Procedura di lettura risposta
			boolean more = true;
			while(more) {
				String line = in.readLine();
				if(line == null) more = false;
				else System.out.println(line);
			}
		} catch (IOException e) { System.out.println("Error"+e); }
	}
}
