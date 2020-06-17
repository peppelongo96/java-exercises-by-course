/**
 * 
 */
package anno17_18.retidicalcolatori.URLTesting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author Giuseppe Longo
 *
 */
class URLTest {
	
	public static void main(String[] args) {
		try {
			URL url = new URL("http://www.w3.org");
			BufferedReader in = new BufferedReader (new InputStreamReader(url.openStream()));
			boolean more = true;
			while (more) {
				String line = in.readLine();
				if (line == null)
					more = false;
				else
					System.out.println(line);
			}
		} catch (IOException e) { System.out.println("Error"+e); }
	}

}
