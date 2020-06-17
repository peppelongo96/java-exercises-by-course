package anno16_17.sisop.TracciaCementificio;

import java.util.concurrent.TimeUnit;

class Addetto implements Runnable {
	
	Cementificio c;
	
	public Addetto( Cementificio c ) {
		this.c = c;
	}
	
	@Override
	public void run() {
		try {
			while ( true ) {
				c.iniziaRifornimento();
				System.out.println(Thread.currentThread().getName()+" inizia Rif");
				rifornisci();
				c.fineRifornimento();
			}
		} catch ( InterruptedException ie ) {}
	}
	
	private void rifornisci() throws InterruptedException {
		TimeUnit.SECONDS.sleep(5);
		c.sacchiTot.set(c.sacchiInizio);
	}

}
