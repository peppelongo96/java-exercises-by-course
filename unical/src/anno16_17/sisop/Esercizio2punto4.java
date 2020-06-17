package anno16_17.sisop;

import java.util.ArrayList;

public class Esercizio2punto4 {
	
	private static ArrayList<Posizione> listaMax, listaMin;
	private static Thread[] listaT;
	
	private static class Posizione {
		int riga,col;
		
		private  Posizione( int riga, int col ) {
			this.riga = riga;
			this.col = col;
		}
		
		@Override
		public boolean equals(Object o) {
			if ( !(o instanceof Posizione )) return false;
			if ( o==this ) return true;
			Posizione p = (Posizione)o;
			return p.col==this.col && p.riga==this.riga;
		}
		
		@Override
		public String toString() {
			return "["+riga+"],["+col+"]";
		}
	}
	
	private static class calcolaMax extends Thread {
		private int[][] matrice;
		private int riga;
		
		private calcolaMax ( int[][] matrice, int riga ) {
			this.matrice = matrice;
			this.riga = riga;
		}//Costruttore
		
		@Override
		public void run() {
			int max = Integer.MIN_VALUE;
			int col = -1;
			for (int i = 0; i < matrice[0].length; i++) {
				if ( matrice[this.riga][i]>max ) {
					max = matrice[this.riga][i];
					col = i;
				}
			}
			listaMax.add(new Posizione(this.riga, col));
		}
	}
	
	private static class calcolaMin extends Thread {
		private int[][] matrice;
		private int col;
		
		private calcolaMin ( int[][] matrice, int col ) {
			this.matrice = matrice;
			this.col = col;
		}//Costruttore
		
		@Override
		public void run() {
			int min = Integer.MAX_VALUE;
			int riga = -1;
			for (int i = 0; i < matrice.length; i++) {
				if ( matrice[i][this.col]<min ) {
					min = matrice[i][this.col];
					riga = i;
				}
			}
			listaMin.add(new Posizione(riga, this.col));
		}
	}
	
	private static void inizializaThreds( int[][]matrice) throws InterruptedException {
		listaMax = new ArrayList<>();
		listaMin = new ArrayList<>();
		
		listaT = new Thread[matrice.length+matrice[0].length];
		
		for (int i = 0; i < matrice.length; i++) {
			listaT[i] = new calcolaMax(matrice, i);
			listaT[i].start();
		}
		for (int i = 0; i < matrice[0].length; i++) {
			listaT[i+matrice.length] = new calcolaMin(matrice, i);
			listaT[i+matrice.length].start();
		}
		for (int i = 0; i < listaT.length; i++) {
			listaT[i].join();
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		int[][] matrice = {{1,2,13},{4,5,6},{9,10,20},{7,5,9},{8,20,7}};
		
		inizializaThreds(matrice);
				
		for ( Posizione p : listaMax ) {
			if ( listaMin.contains(p) ) {
				System.out.println(p);
				return;
			}
		}
	}

}
