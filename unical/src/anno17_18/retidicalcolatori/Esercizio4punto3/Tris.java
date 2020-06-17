/**
 * 
 */
package anno17_18.retidicalcolatori.Esercizio4punto3;

/**
 * @author Giuseppe Longo
 *
 */
class Tris {
	
	enum Responso { ANCORA_MOSSE, PATTA, VINCE_X, VINCE_O }; 
	
	enum Mossa { X, O };
	
	private String[][] matriceTris = new String[3][3];
	
	private int contaX, contaO;
	
	Tris() {
		for (int i = 0; i < 3; i++) 
			for (int j = 0; j < 3; j++) 
				matriceTris[i][j] = " ";
	}
	
	void inserisciMossa( Mossa m, PosMossa pm ) {
		if ( pm.getColonna() > 3 || pm.getRiga() > 3 ) throw new IllegalArgumentException();
		matriceTris[pm.getRiga()][pm.getColonna()] = m.toString();
		if ( m == Mossa.O ) contaO++; 
		else contaX++;
	}
	
	Responso controlloPartita() {
		if ( contaO < 3 && contaX < 3) return Responso.ANCORA_MOSSE;
		//controlla colonne
		for (int j = 0; j < 3; j++) {
			if ( matriceTris[0][j].equals(matriceTris[1][j]) && matriceTris[2][j].equals(matriceTris[1][j]) ) {
				if ( matriceTris[0][j].equals("X") )
					return Responso.VINCE_X;
				else if ( matriceTris[0][j].equals("O") ) 
					return Responso.VINCE_O;
			}
		}
		//controlla righe
		for (int i = 0; i < 3; i++) {
			if ( matriceTris[i][0].equals(matriceTris[i][1]) && matriceTris[i][2].equals(matriceTris[i][1]) ) {
				if ( matriceTris[i][0].equals("X") )
					return Responso.VINCE_X;
				else if ( matriceTris[i][0].equals("O") ) 
					return Responso.VINCE_O;
			}
		}
		//controllo diagonali
		if ( matriceTris[0][0].equals(matriceTris[1][1]) && matriceTris[2][2].equals(matriceTris[1][1]) ) {
			if ( matriceTris[0][0].equals("X") )
				return Responso.VINCE_X;
			else if ( matriceTris[0][0].equals("O") ) 
				return Responso.VINCE_O;
		}
		
		if ( matriceTris[0][2].equals(matriceTris[1][1]) && matriceTris[2][0].equals(matriceTris[1][1]) ) {
			if ( matriceTris[0][2].equals("X") )
				return Responso.VINCE_X;
			else if ( matriceTris[0][2].equals("O") ) 
				return Responso.VINCE_O;
		}
		
		return Responso.PATTA;
		
	}
	
	@Override
	public String toString() {
		return matriceTris[0][0]+"|"+matriceTris[0][1]+"|"+matriceTris[0][2]+"\n"+
			   "_____\n"+
			   matriceTris[1][0]+"|"+matriceTris[1][1]+"|"+matriceTris[1][2]+"\n"+
			   "_____\n"+
			   matriceTris[2][0]+"|"+matriceTris[2][1]+"|"+matriceTris[2][2];
	}
	
}
