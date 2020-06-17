package anno16_17.sisop.TracciaCementificio;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

class CementificioLock extends Cementificio {
	
	Lock lock = new ReentrantLock();
	LinkedList<Thread> coda = new LinkedList<>();
	LinkedList<Thread> codaPrelievo = new LinkedList<>();
	private AtomicInteger clientiPresenti = new AtomicInteger(0);
	
	Condition entra = lock.newCondition();
	Condition preleva = lock.newCondition();
	Condition attivaAddetto = lock.newCondition();
	
	public CementificioLock( int nClientiCont, int sacchiInizio ) {
		super( nClientiCont, sacchiInizio );
	}

	@Override
	protected void entra() throws InterruptedException {
		lock.lock();
		try {
			coda.add(Thread.currentThread());
			while ( !possoEntrare() )
				entra.await();
			coda.removeFirst();
			clientiPresenti.incrementAndGet();
		} finally {
			lock.unlock();
		}
		
	}
	
	private boolean possoEntrare() {
		return coda.getFirst() == Thread.currentThread()
				&& clientiPresenti.get() < nClientiCont;
	}

	@Override
	protected void esci() throws InterruptedException {
		lock.lock(); 
		try {
			clientiPresenti.decrementAndGet();
			entra.signalAll();
		} finally {
			lock.unlock();
		}
		
	}

	@Override
	protected void preleva() throws InterruptedException {
		lock.lock();
		try {
			codaPrelievo.add(Thread.currentThread());
			while ( !possoPrelevare() ) {
				if( sacchiTot.get() <1 )
					attivaAddetto.signal();
				preleva.await();
			}
			codaPrelievo.removeFirst();
		} finally {
			lock.unlock();
		}
		
	}
	
	private boolean possoPrelevare() {
		return codaPrelievo.getFirst() == Thread.currentThread()
				&& sacchiTot.get() > 0 ;
	}

	@Override
	protected void iniziaRifornimento() throws InterruptedException {
		lock.lock();
		try {
			attivaAddetto.await();
		}
		finally {
			lock.unlock();
		}
	}

	@Override
	protected void fineRifornimento() throws InterruptedException {
		lock.lock();
		try {
			preleva.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		CementificioLock cl = new CementificioLock(100, 5);
		cl.test(200);
	}

}
