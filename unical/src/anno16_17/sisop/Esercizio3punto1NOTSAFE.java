package anno16_17.sisop;

public class Esercizio3punto1NOTSAFE {
	
	private int[][] matrice = {{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0}};
	
	private void stampa() {
		for (int i = 0; i < matrice.length; i++) {
			for (int j = 0; j < matrice[0].length; j++) {
				System.out.print(matrice[i][j]);
			}
			
		}
	}
	
	private class ThreadIncRiga extends Thread {
		private int riga;
		
		private ThreadIncRiga(int riga) {
			this.riga = riga;
		}
	
		@Override
		public void run() {
			for (int i = 0; i < matrice[riga].length; i++) {
				matrice[riga][i]--;
			}
		}
	}
	
	private class ThreadDecCol extends Thread {
		private int col;
		
		private ThreadDecCol(int col) {
			this.col = col;
		}
	
		@Override
		public void run() {
			for (int i = 0; i < matrice.length; i++) {
				matrice[i][col]--;
			}
		}
	}
	
	public void inizializzaThread() {
		for (int i = 0; i < matrice.length; i++) {
			new ThreadIncRiga(i).start();
		}
		for (int i = 0; i < matrice[0].length; i++) {
			new ThreadDecCol(i).start();
		}
	}
	
	public static void main(String[] args) {
		Esercizio3punto1NOTSAFE n = new Esercizio3punto1NOTSAFE();
		n.inizializzaThread();
		n.stampa();
	}
}
