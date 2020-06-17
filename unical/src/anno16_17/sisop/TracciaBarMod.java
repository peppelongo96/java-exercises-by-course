package anno16_17.sisop;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Giuseppe Longo
 *
 */
class TracciaBarMod {
	
	private Semaphore cassa = new Semaphore(1, true);
	private int nCassa;
	private Semaphore bancone = new Semaphore(4, true);
	private int nBancone;
	private Random random = new Random();
	
	//0 = pagare; 1 = bere il caffé
	
	private class Persona extends Thread {
		
		private int scelta;
		
		public Persona() {
			scelta = -1;
		}
		
		private int scegli() {
			if ( cassa.tryAcquire() ) return 0;
			if ( bancone.tryAcquire() ) return 1;
			if ( nCassa == nBancone || nCassa > nBancone ) return 0;
			return 1;
		}
		
		private void inizia( int i ) throws InterruptedException {
			if ( i==0 ) {
				cassa.acquire();
				nCassa++;
				paga();
			}
			bancone.acquire();
			nBancone++;
			bereCaffe();
		}
				
		private void finisci( int i ) {
			if ( i==0 ) {
				cassa.release();
				nCassa--;
				this.scelta = 1;
			}
			bancone.release();;
			nBancone--;
			this.scelta = 0;
		}
		
		private void paga()throws InterruptedException {
			TimeUnit.SECONDS.sleep(random.nextInt(5)+5);
		}
		
		private void bereCaffe()throws InterruptedException {
			TimeUnit.SECONDS.sleep(random.nextInt(20)+20);
		}
		
		@Override
		public void run() {
			this.scelta = scegli();
			try {
				inizia(this.scelta);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finisci(this.scelta);
			try {
				inizia(this.scelta);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finisci(this.scelta);
		}

	}
	
	private void inizializza() {
		for (int i = 0; i < 3; i++) {
			new Persona().start();
		}
	}
	
	public static void main(String[] args) {
		TracciaBarMod bar = new TracciaBarMod();
		bar.inizializza();
		System.out.println("ciao");
	}
	
}
