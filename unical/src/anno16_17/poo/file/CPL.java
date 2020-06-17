package anno16_17.poo.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class CPL {
	public static long linee = 0;
	public static long parole = 0;
	public static long caratteri = 0;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader lettore = new BufferedReader(new FileReader(args[0]));
		String linea = null;
		StringTokenizer st = null;
		for(;;){
			linea = lettore.readLine();
			if (linea == null) break;
			linee++;
			caratteri+=linea.length();
			st = new StringTokenizer(linea," .,:;-_?!");
			parole+=st.countTokens();
		}
		System.out.println("In questo testo ci sono: "+caratteri+" caratteri, "+parole+" parole e "+linee+" linee.");
		lettore.close();
	}

}
