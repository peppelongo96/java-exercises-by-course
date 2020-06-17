package anno16_17.sisop.TracciaLaghetto;

import java.util.concurrent.Semaphore;

class LaghettoSem extends Laghetto{
	
	Semaphore mutex = new Semaphore(1, true);
	Semaphore ceAddetto = new Semaphore(0);
	Semaphore cePescatore = new Semaphore(0);

	public LaghettoSem(int minPesci, int maxPesci) {
		super(minPesci, maxPesci);
		// TODO Auto-generated constructor stub
	}

	@Override
	void inizia(int t) throws InterruptedException {
		mutex.acquire();
		if ( t==0 ) {
			while ( ceAddetto.availablePermits()>0 || nPesci.get() < minPesci ) {
				mutex.release();
				mutex.acquire();
			}
			cePescatore.release();
			nPesci.decrementAndGet();
			System.out.println(Thread.currentThread().getName()+" inizia a pescare");
		}
		else {
			while ( cePescatore.availablePermits()>0 || nPesci.get() > maxPesci ) {
				mutex.release();
				mutex.acquire();
			}
			ceAddetto.release();
			nPesci.addAndGet(10);
			System.out.println(Thread.currentThread().getName()+" inizia a ripopolare");
		}
	}

	@Override
	void finisci(int t) throws InterruptedException {
		if ( t==0 ) {
			System.out.println(Thread.currentThread().getName()+" finisce di pescare");
			cePescatore.acquire();
		}
		else {
			System.out.println(Thread.currentThread().getName()+" finisce di ripopolare");
			ceAddetto.acquire();
		}
		mutex.release();
	}
	
	public static void main(String[] args) {
		LaghettoSem ls = new LaghettoSem(50, 200);
		ls.test(40, 5);
	}
	
}
