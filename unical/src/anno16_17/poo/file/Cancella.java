package anno16_17.poo.file;

import java.io.File;

public class Cancella {

	public static void main(String[] args) {
		int i = 1;
		while( i!=1161 ) {
			StringBuilder sb = new StringBuilder("C:/Users/peppe/Desktop/Nuova cartella/");
			sb.append(i+".info");
			File f = new File( sb.toString() );
			if ( f.delete() )
				System.out.println("eliminato "+i);
			i++;
		}
	}

}
