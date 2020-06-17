package anno16_17.sisop.TracciaLaghetto;

import java.util.concurrent.atomic.AtomicInteger;

abstract class Laghetto {
	
	protected int minPesci, maxPesci;
	
	protected AtomicInteger nPesci = new AtomicInteger(0);
	
	abstract void inizia( int t) throws InterruptedException;
	
	abstract void finisci ( int t) throws InterruptedException;
	
	public Laghetto(int minPesci, int maxPesci) {
		this.maxPesci = maxPesci;
		this.minPesci = minPesci;
	}

	public void test( int pescatori, int addetti ) {
		for (int i = 0; i < pescatori; i++) 
			new Thread ( new Pescatore(this), "Pescatore "+i).start();
		for (int i = 0; i < addetti; i++) {
			new Thread ( new Addetto(this), "Addetto "+i).start();
		}
	}
}
