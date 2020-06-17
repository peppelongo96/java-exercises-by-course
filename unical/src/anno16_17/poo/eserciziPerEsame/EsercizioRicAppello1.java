package anno16_17.poo.eserciziPerEsame;

public class EsercizioRicAppello1 {
	
	private static void stampaSol(char[]array){
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
		}
		System.out.println();
	}
	
	private static void esegui (char[][]sistema, int riga, char[]array ){
		if ( riga==13 ) stampaSol(array);
		else {
			for ( int i = 0; i<3; i++ ) {
				if ( i<sistema[riga].length ) {
					array[riga] = sistema[riga][i];
					esegui(sistema,riga+1,array);
				}
			}
		}
	}

	public static void main(String[] args) {
		char[][]sistema = {{'1'},{'2','1'},{'x'},{'1','2','x'},{'x','2'},{'2'},{'1'},{'1','2','x'},{'x'},{'2'},{'1','x'},{'2'},{'1'}};
		int riga = 0;
		char[]array = new char[13];
		esegui(sistema, riga, array);
	}
}
