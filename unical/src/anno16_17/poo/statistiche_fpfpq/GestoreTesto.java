package anno16_17.poo.statistiche_fpfpq;
import java.io.*;
import java.util.*;

public class GestoreTesto {
	public enum Simbolo{ WORD, EOF }
	private boolean EOF=false;
	private String linea=null;
	private Scanner input, scan;
	private int numeroLineaCorrente=0;
	private String word, delim;

	public GestoreTesto( File f, String delim ) throws IOException{
		input=new Scanner( f );
		this.delim=delim;
	}//costruttore

	private void avanza(){
		try{
			if( linea==null || !scan.hasNext() ){
				linea=input.nextLine();
				numeroLineaCorrente++;
				//echo su output della linea
				System.out.println(numeroLineaCorrente+": "+linea);
				scan=new Scanner( linea );
				scan.useDelimiter( delim );
			}
		}catch( Exception ioe ){
			EOF=true; input.close();
		}
	}//avanza

	public Simbolo prossimoSimbolo() throws IOException{   
		do{
			avanza();
		}while( !EOF && !scan.hasNext() );
		if( EOF ) return Simbolo.EOF;
		word = scan.next();
		return Simbolo.WORD;
	}//prossimoSimbolo

	public String getString(){
		return word;
	}//getString

	public int getNumeroLinea(){
		return numeroLineaCorrente;
	}//numeroLinea

}//GestoreTesto
