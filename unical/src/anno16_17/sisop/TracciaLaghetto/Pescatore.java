package anno16_17.sisop.TracciaLaghetto;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class Pescatore implements Runnable{
	
	Laghetto l;
	Random r = new Random();
	
	public Pescatore( Laghetto l ) {
		this.l = l;
	}

	@Override
	public void run() {
		while ( true ) {
			try {
				l.inizia(0);
				pesca();
				l.finisci(0);
				pausa();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void pesca() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(r.nextInt(200)+600);
	}
	
	private void pausa() throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
	}

}
