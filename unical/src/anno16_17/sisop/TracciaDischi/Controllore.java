package anno16_17.sisop.TracciaDischi;

import java.util.concurrent.atomic.AtomicInteger;

abstract class Controllore {
	
	protected abstract void allocaDischi ( int a, int b ) throws InterruptedException;
	
	protected abstract void rilasciaDischi ( int a, int b ) throws InterruptedException; 
	
	protected AtomicInteger[] listaDischi = new AtomicInteger[3];
	
	public void test( int nProcessi ) {
		for (int i = 0; i < listaDischi.length; i++) {
			listaDischi[i] = new AtomicInteger(0);
		}
		for (int i = 0; i < nProcessi; i++) {
			new Thread ( new Processo(this), "Processo-"+i ).start();
		}
	}

}
