package anno16_17.sisop.TracciaGaraSci;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

abstract class Gara {
	
	protected abstract void partenza ( Sciatore s ) throws InterruptedException;
	
	protected abstract int arrivo ( Sciatore s );
	
	protected abstract boolean prossimo();
	
	protected LinkedList<Sciatore> classificaFinale = new LinkedList<>();
	protected LinkedList<Thread> lista = new LinkedList<>();
	protected AtomicInteger nSciatori;
	
	public void test(int nSciatori) {
		this.nSciatori = new AtomicInteger(nSciatori);
		new Thread ( new Addetto(this) ).start();
		for (int i = 0; i < nSciatori; i++) {
			lista.add(new Thread ( new Sciatore(i,this) ));
			lista.get(i).start();
		}
	}

}
