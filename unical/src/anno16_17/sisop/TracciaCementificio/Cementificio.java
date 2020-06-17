package anno16_17.sisop.TracciaCementificio;

import java.util.concurrent.atomic.AtomicInteger;

abstract class Cementificio {
	
	protected abstract void entra() throws InterruptedException;
	
	protected abstract void esci() throws InterruptedException;
	
	protected abstract void preleva() throws InterruptedException;
	
	protected abstract void iniziaRifornimento() throws InterruptedException;
	
	protected abstract void fineRifornimento() throws InterruptedException;
	
	protected AtomicInteger sacchiTot;
	protected int sacchiInizio;
	protected int nClientiCont;
	
	public Cementificio( int nClientiCont, int sacchiInizio ) {
		this.nClientiCont = nClientiCont;
		this.sacchiInizio = sacchiInizio;
		this.sacchiTot = new AtomicInteger(sacchiInizio);
	}
	
	protected void test( int clienti) {
		new Thread( new Addetto(this), "Addetto").start();
		for (int i = 0; i < clienti; i++) {
			new Thread( new Cliente(this), "Cliente-"+i).start();
		}
	}
	
	

}
