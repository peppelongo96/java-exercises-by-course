/**
 * 
 */
package anno17_18.retidicalcolatori;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author Giuseppe Longo
 *
 */
class TimeServerTest {
	
	/* Daytime Protocol è un protocollo rivolto a dare in risposta, ora e data ottenuto
	 * da un time server sincronizzato. A ogni richiesta su un dato socket da parte di 
	 * un client, viene data in risposta ora e data 
	 */
	
	static Socket richiesta;
	static BufferedReader leggiRisposta;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			richiesta = new Socket("ntp1.inrim.it",13); //crea socket su indirizzo e porta su cui funziona il protocollo
			leggiRisposta = new BufferedReader (new InputStreamReader(richiesta.getInputStream()));
			boolean more = true;
			while(more) {
				String line = leggiRisposta.readLine();
				if(line == null)
					more = false;
				else
					System.out.println(line);
			}
		} catch (IOException e) { System.out.println(e); }
	}

}//TimeServer
