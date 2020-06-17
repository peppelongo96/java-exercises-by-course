package anno16_17.sisop;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Esercizio5punto2 {
	
	private static Semaphore[] bacchette = new Semaphore[5];
	
	private static class Filosofo extends Thread {
		
		private int num;
		
		public Filosofo(int num) {
			this.num = num;
		}
		
		@Override
		public void run() {
			while ( true ) {
				try {
					bacchette[num].acquire();
					if ( bacchette[(num+1)%5].tryAcquire(5, TimeUnit.SECONDS)==false ) {
						bacchette[num].release();
						continue;
					}
					mangia();
					bacchette[num].release();
					bacchette[(num+1)%5].release();
					pensa();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		private void mangia() throws InterruptedException {
			System.out.println("Filosofo-"+num+" mangia!");
			TimeUnit.SECONDS.sleep(5);
		}
		
		private void pensa() throws InterruptedException {
			System.out.println("Filosofo-"+num+" pensa!");
			TimeUnit.SECONDS.sleep(5);
		}
	}
	
	private static void inizializzaFilosofi() {
		for (int i = 0; i < bacchette.length; i++) {
			bacchette[i] = new Semaphore(1);
		}
		for (int i = 0; i < 5; i++) {
			new Filosofo(i).start();
		}
	}

	public static void main(String[] args) {
		inizializzaFilosofi();
	}

}
