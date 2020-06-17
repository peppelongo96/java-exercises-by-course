package anno16_17.sisop;

import java.util.concurrent.Semaphore;

public class Esercizio4punto3SAFE {

	private int[][] matrice = {{0,0,0},{0,0,0},{0,0,0},{0,0,0},{0,0,0}};
	private Semaphore[][] mutex = new Semaphore[matrice.length][matrice[0].length];

	public void stampa() {
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
			for (int j = 0; j < 10; j++) {
				for (int i = 0; i < matrice[riga].length; i++) {
					try {
						mutex[riga][i].acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					matrice[riga][i]--;
					mutex[riga][i].release();
				}
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
			for (int j = 0; j < 10; j++) {
				for (int i = 0; i < matrice.length; i++) { 
					try {
						mutex[i][col].acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					matrice[i][col]--;
					mutex[i][col].release();;
				}
			}
		}
	}

	public void inizializzaThread() {
		inizializzaSem();
		for (int i = 0; i < matrice.length; i++) {
			new ThreadIncRiga(i).start();
		}
		for (int i = 0; i < matrice[0].length; i++) {
			new ThreadDecCol(i).start();
		}
	}
	
	private void inizializzaSem() {
		for (int i = 0; i < mutex.length; i++) 
			for (int j = 0; j < matrice[0].length; j++) 
				mutex[i][j] = new Semaphore(0);		
	}	

	public static void main(String[] args) {
		Esercizio4punto3SAFE n = new Esercizio4punto3SAFE();
		n.inizializzaThread();
		n.stampa();
	}
}

