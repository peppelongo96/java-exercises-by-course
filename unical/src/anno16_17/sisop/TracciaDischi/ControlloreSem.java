package anno16_17.sisop.TracciaDischi;

import java.util.concurrent.Semaphore;

class ControlloreSem extends Controllore {
	
	Semaphore coda = new Semaphore(1,true);

	@Override
	protected void allocaDischi(int a, int b) throws InterruptedException {
		coda.acquire();
		while ( listaDischi[a].get() == 5 || listaDischi[a].get()== 5 ) {
			coda.release();
			coda.acquire();
		}
		listaDischi[a].incrementAndGet();
		listaDischi[b].incrementAndGet();
	}

	@Override
	protected void rilasciaDischi(int a, int b) throws InterruptedException {
		listaDischi[b].decrementAndGet();
		listaDischi[a].decrementAndGet();
		coda.release();
	}
	
	public static void main(String[] args) {
		ControlloreSem cs = new ControlloreSem();
		cs.test(100);
	}

}
