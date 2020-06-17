package anno16_17.sisop.TracciaLaghetto;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LaghettoLock extends Laghetto{
	
	private Lock lock = new ReentrantLock();
	private Condition inizia = lock.newCondition();
	private boolean ceAddetto = false;
	private boolean cePescatore = false;
	private LinkedList<Thread> coda = new LinkedList<>();

	public LaghettoLock(int minPesci, int maxPesci) {
		super(minPesci, maxPesci);
		// TODO Auto-generated constructor stub
	}

	@Override
	void inizia(int t) throws InterruptedException {
		lock.lock();
		try {
			coda.add(Thread.currentThread());
			while ( !possoIniziare(t) ) {
				inizia.await();
			}
			coda.removeFirst();
			if ( t==0 ) {
				System.out.println(Thread.currentThread().getName()+" inizia a pescare");
				nPesci.decrementAndGet();
			}
			else {
				System.out.println(Thread.currentThread().getName()+" inizia a ripopolare");
				nPesci.addAndGet(10);
			}
		} finally {
			lock.unlock();
		}
	}

	private boolean possoIniziare( int t ) {
		if ( t==0 && (ceAddetto || nPesci.get() < minPesci) )
			return false;
		else if ( t==1 && (cePescatore || nPesci.get() > maxPesci) )
			return false;
		return true;
	}
	
	@Override
	void finisci(int t) throws InterruptedException {
		if ( t==0 ) {
			System.out.println(Thread.currentThread().getName()+" finisce di pescare");
		}
		else {
			System.out.println(Thread.currentThread().getName()+" finisce di ripopolare");
		}
		lock.lock();
		try {
			inizia.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		LaghettoLock ll = new LaghettoLock(50, 200);
		ll.test(40, 5);
	}

}
