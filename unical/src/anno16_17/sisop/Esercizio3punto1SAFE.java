package anno16_17.sisop;

import java.util.concurrent.atomic.AtomicInteger;

public class Esercizio3punto1SAFE {

	private AtomicInteger[][] matrice = new AtomicInteger[5][10];
	private Thread[] listaT = new Thread[matrice.length+matrice[0].length];

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
				matrice[riga][i].incrementAndGet();
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
				matrice[i][col].decrementAndGet();
			}
		}
	}

	public void inizializzaThread() {
		for (int i = 0; i < matrice.length; i++) 
			for (int j = 0; j < matrice[0].length; j++)
				matrice[i][j] = new AtomicInteger(0);
		int pos = 0;	
		for (int i = 0; i < matrice.length; i++) {
			listaT[pos] = new ThreadIncRiga(i); pos++;
		}
		for (int i = 0; i < matrice[0].length; i++) {
			listaT[pos] = new ThreadDecCol(i); pos++;
		}
		for (int i = 0; i < listaT.length; i++) {
			listaT[i].start();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Esercizio3punto1SAFE n = new Esercizio3punto1SAFE();
		n.inizializzaThread();
		for (int i = 0; i < n.listaT.length; i++) {
			n.listaT[i].join();
		}
		n.stampa();
	}
}
