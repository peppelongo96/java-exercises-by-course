package anno16_17.sisop.TracciaLaghetto;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class Addetto implements Runnable {
	
	Laghetto l;
	Random r = new Random();
	
	public Addetto( Laghetto l ) {
		this.l = l;
	}

	@Override
	public void run() {
		while ( true ) {
			try {
				l.inizia(1);
				ripopola();
				l.finisci(1);
				pausa();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void ripopola() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(r.nextInt(300)+300);
	}
	
	private void pausa() throws InterruptedException {
		TimeUnit.SECONDS.sleep(3);
	}
}
