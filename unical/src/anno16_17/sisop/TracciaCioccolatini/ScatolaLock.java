package anno16_17.sisop.TracciaCioccolatini;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ScatolaLock extends Scatola {
	
	Lock lock = new ReentrantLock();
	Condition prendi = lock.newCondition();
	LinkedList<Thread> coda = new LinkedList<>();
	Random r = new Random();
	
	public ScatolaLock(int nCiocc, int nTipi) {
		super(nCiocc, nTipi);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected LinkedList<Integer> get() throws InterruptedException {
		lock.lock();
		try {
			coda.add(Thread.currentThread());
			while ( !possoPrendere() )
				prendi.await();
			LinkedList<Integer> manciata = new LinkedList<>();
			while ( listaCioccolatini.size()>0 ) {
				manciata.add(listaCioccolatini.remove(r.nextInt(listaCioccolatini.size())));
				if ( manciata.size()==5 )
					break;
			}
			return manciata;
		} finally {
			lock.unlock();
		}
	}
	
	private boolean possoPrendere() {
		return Thread.currentThread()==coda.getFirst();
	}

	@Override
	protected void put(LinkedList<Integer> manciata) throws InterruptedException {
		for ( Integer i : manciata )
			listaCioccolatini.add(i);
		lock.lock();
		try {
			coda.removeFirst();
			prendi.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		ScatolaLock sl = new ScatolaLock(100, 5);
		sl.test(100);
	}


}
