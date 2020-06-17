package anno16_17.sisop.TracciaGaraSci;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

class Addetto implements Runnable {
	
	private Gara g;
	
	public Addetto ( Gara g ) {
		this.g = g;
	}

	@Override
	public void run() {
		try {
			while ( g.prossimo() ) {
				TimeUnit.SECONDS.sleep(20);
			}
			for ( Thread t : g.lista ) {
				t.join();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stampaClassifica();
	}
	
	private void stampaClassifica() {
		Collections.sort(g.classificaFinale);;
		System.out.println(g.classificaFinale);
	}

}
