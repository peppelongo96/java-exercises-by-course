package anno16_17.sisop;

public class Esercizio2punto3 {
	private static int somma;
	private static ProdottoScalare[] listaT;
	
	private static class ProdottoScalare extends Thread {
		private int[] a1, a2;
		private int in, fin;
		
		public ProdottoScalare(int[] a1, int[] a2, int in, int fin) {
			this.a1 = a1;
			this.a2 = a2;
			this.in = in;
			this.fin = fin;
		}//Costruttore
		
		@Override
		public void run() {
			for (int i = in; i < fin; i++) {
				somma+=a1[i]*a2[i];
			}
		}//run
	}
	
	private static void inizializzaThreads(int[] a1, int[] a2, int n, int m) {
		if ( n%m!=0 || n>a1.length ) throw new IllegalArgumentException();
		int nmul = n/m;
		int pos = 0;
		listaT = new ProdottoScalare[m];
		for (int i = 0; i < m; i++) {
			listaT[i] = new ProdottoScalare(a1, a2, pos, pos+nmul); 
			listaT[i].start();
			pos+=nmul;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		int[] a1 = {2,3,4,6};
		int[] a2 = {2,3,4,6};
		inizializzaThreads(a1, a2, 4, 2);
		for (int i = 0; i < listaT.length; i++) 
			listaT[i].join();
		System.out.println(somma);
		

	}

}
