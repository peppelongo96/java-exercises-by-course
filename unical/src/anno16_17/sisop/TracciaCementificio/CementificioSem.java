package anno16_17.sisop.TracciaCementificio;

import java.util.concurrent.Semaphore;

class CementificioSem extends Cementificio {
	
	Semaphore coda = new Semaphore(nClientiCont,true ) ;
	Semaphore codaPrelievo = new Semaphore(1,true ) ;
	Semaphore attivaAddetto = new Semaphore(0) ;
	
	public CementificioSem( int nClientiCont, int sacchiInizio ) {
		super(nClientiCont, sacchiInizio ) ;
	}

	@Override
	protected void entra() throws InterruptedException {
		coda.acquire();
	}

	@Override
	protected void esci() throws InterruptedException {
		coda.release();
	}

	@Override
	protected void preleva() throws InterruptedException {
		codaPrelievo.acquire();
		if ( sacchiTot.get()<1 ) {
			if ( attivaAddetto.availablePermits()<1 )
				attivaAddetto.release();
			codaPrelievo.acquire();
		}
		codaPrelievo.release();
	}

	@Override
	protected void iniziaRifornimento() throws InterruptedException {
		attivaAddetto.acquire();
	}

	@Override
	protected void fineRifornimento() throws InterruptedException {
		codaPrelievo.release(-codaPrelievo.availablePermits()+1);
	}
	
	public static void main(String[] args) {
		CementificioSem cs = new CementificioSem(100, 5);
		cs.test(200);
	}

}
