/**
 * 
 */
package anno17_18.retidicalcolatori.URLTesting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Giuseppe Longo
 *
 */


//VEDI SLIDE N.3, PG. 10 A 14
class URLConnectionTest {
	
	public static void main(String[] args) {
		try {
			
			URL url = new URL("http://www.unical.it");
			URLConnection connection = url.openConnection();
			connection.connect();
			// print header fields
			int n = 1;
			String key;
			while ((key = connection.getHeaderFieldKey(n)) != null) {
				String value = connection.getHeaderField(n);
				System.out.println(key + ": " + value);
				n++;
			} // print convenience functions
			System.out.println("----------");
			System.out.println("getContentType: "+connection.getContentType());
			System.out.println("getContentLength: "+connection.getContentLength());
			System.out.println("getContentEncoding: "
					+connection.getContentEncoding());
			System.out.println("getDate: "+connection.getDate());
			System.out.println("getExpiration: "+connection.getExpiration());
			System.out.println("getLastModifed: "+connection.getLastModified());
			System.out.println("----------");
			BufferedReader in = new BufferedReader(new
					InputStreamReader(connection.getInputStream()));
			// print first ten lines of contents
			String line; n = 1;
			while ((line = in.readLine()) != null && n <= 10) {
				System.out.println(line);
				n++;
			}
			if (line != null) System.out.println(". . .");
		} catch (IOException exception) {System.out.println("Error: " + exception); }
	}
	
}
