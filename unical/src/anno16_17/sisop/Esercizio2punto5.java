package anno16_17.sisop;

public class Esercizio2punto5 {
	
	private static int sommaX, sommaY;
	private static trovaRipetizioni[] listaT;
	
	private static class trovaRipetizioni extends Thread {
		int[] riga;
		int x,y;
		
		private trovaRipetizioni( int[] riga, int x, int y ) {
			this.riga = riga;
			this.x = x; this.y = y;
		}
		
		@Override
		public void run() {
			for (int i = 0; i < riga.length; i++) {
				if ( riga[i]==x ) sommaX++;
				else if ( riga[i]==y ) sommaY++;
			}
		}
	}
	
	private static boolean inizializzaThreads(int[][] matrice, int x, int y) throws InterruptedException {
		listaT = new trovaRipetizioni[matrice.length];
		for (int i = 0; i < matrice.length; i++) {
			listaT[i] = new trovaRipetizioni(matrice[i], x, y);
			listaT[i].start();
		}
		for (int i = 0; i < listaT.length; i++) {
			listaT[i].join();
		}
		if (sommaX > sommaY ) return true;
		return false;
	}
	

	public static void main(String[] args) throws InterruptedException {
		int[][] matrice = {{3,4,5},{3,2,5},{5,7,8},{6,3,4}};
		System.out.println(inizializzaThreads(matrice, 3, 5));

	}
}

	