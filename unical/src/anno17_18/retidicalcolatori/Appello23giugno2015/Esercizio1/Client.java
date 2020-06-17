/**
 * 
 */
package anno17_18.retidicalcolatori.Appello23giugno2015.Esercizio1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Giuseppe Longo
 *
 */
class Client {

	/**
	 * @param args
	 */
	
	private static final String localhost = "127.0.0.1";
	
	public static void main(String[] args) {
		
		Socket s;
		BufferedReader in;
		PrintWriter out;
		ObjectOutputStream obOut;
		
		String filename;
		String[] keywords;
		String content;

		try {
			Scanner scan = new Scanner(System.in);

			System.out.println("INSERISCI FILE ");
			System.out.print("- N° Keywords: ");
			int nKey = scan.nextInt();
			keywords = new String[nKey];
			scan = new Scanner(System.in);
			for (int i = 0; i < nKey; i++) {
				System.out.print("--key "+i+": ");
				keywords[i] = scan.nextLine().trim();
			}
			System.out.print("- Filename: ");
			filename = scan.nextLine().trim();
			System.out.print("- Content: ");
			content = scan.nextLine();
			System.out.print("c PER CERCARE IL FILE, m PER MEMORIZZARLO -> ");
			String scelta = scan.nextLine().trim().toLowerCase();
			
			if ( scelta.equals("c") ) {
				s = new Socket(localhost, IndexServer.portaTCP );
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				out = new PrintWriter(s.getOutputStream(),true);
				System.out.println(in.readLine());
				
				out.println(filename); out.println(keywords);
				
				System.out.println(in.readLine());
				System.out.println(in.readLine());
			}
			else if ( scelta.equals("m") ) {
				s = new Socket(localhost, StorageServer.portaTCP );
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				obOut = new ObjectOutputStream(s.getOutputStream());
				System.out.println(in.readLine());
				
				File f = new File(filename, content, keywords);
				
				obOut.writeObject(f);

			}
		} catch ( Exception e ) { System.out.println(e); }
	}

}
