package anno16_17.sisop.TracciaCementificio;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class Cliente implements Runnable { 
	
	Cementificio c;
	Random r = new Random();
	
	public Cliente( Cementificio c) {
		this.c = c;
	}
	
	@Override
	public void run() {
		try {
			int scelta = scegliSacchi();
			System.out.println(Thread.currentThread().getName()+" ha scelto");
			c.entra();
			System.out.println(Thread.currentThread().getName()+" entra in cementificio");
			for (int i = 0; i < scelta ; i++) {
				c.preleva();
				prelievo();
			}
			System.out.println(Thread.currentThread().getName()+" è uscito");
			c.esci();
		} catch ( InterruptedException ie ) {}
	}
	
	private int scegliSacchi() {
		return r.nextInt(21)+10;
	}
	
	private void prelievo() throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
		c.sacchiTot.decrementAndGet();
	}

}
