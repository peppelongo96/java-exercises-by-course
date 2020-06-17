package anno16_17.sisop.TracciaNegozio;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

class NegozioLock extends Negozio {
	
	private LinkedList<Thread> coda = new LinkedList<>();
	
	private Lock lock = new ReentrantLock();
	private Condition svuota = lock.newCondition();
	private Condition scansiona = lock.newCondition();
	private Condition paga = lock.newCondition();
	
	private boolean carrelloSvuotato = false;
	private boolean carrelloScansionato = false;
	

	@Override
	protected void svuotaCarrello(int n) throws InterruptedException {
		lock.lock();
		try {
			coda.add(Thread.currentThread());
			while ( !possoSvuotare() ) {
				svuota.await();
			}
			prodottiInCassa = n;
			clienteCor = Thread.currentThread().getName();
			carrelloScansionato = false;
			carrelloSvuotato = true;
			scansiona.signal();
		} finally {
			lock.unlock();
		}
		
	}
	
	private boolean possoSvuotare() {
		return coda.getFirst() == Thread.currentThread();
	}

	@Override
	protected void paga() throws InterruptedException {
		lock.lock(); 
		try {
			while ( !possoPagare() )
				paga.await();
			System.out.println(Thread.currentThread().getName()+" ha pagato "+prodottiInCassa+" euro");
			coda.removeFirst();
			svuota.signalAll();
		} finally {
			lock.unlock();
		}
		
	}
	
	private boolean possoPagare() {
		return carrelloScansionato==true;
	}

	@Override
	protected void scansionaProdotti() throws InterruptedException {
		lock.lock(); 
		try {
			while ( !possoScansionare() )
				scansiona.await();
			System.out.println("Cassa scansiona "+prodottiInCassa+" prodotti del "+clienteCor);
			TimeUnit.SECONDS.sleep(prodottiInCassa*2);
			carrelloScansionato = true;
			carrelloSvuotato = false;
			paga.signal();
		} finally {
			lock.unlock();
		}
		
	}
	
	private boolean possoScansionare() {
		return carrelloSvuotato==true;
	}
	
	public static void main(String[] args) throws InterruptedException {
		NegozioLock nl = new NegozioLock();
		nl.test();
	}

}
