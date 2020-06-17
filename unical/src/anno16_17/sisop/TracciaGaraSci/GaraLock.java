package anno16_17.sisop.TracciaGaraSci;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GaraLock extends Gara {
	
	Lock lock = new ReentrantLock();
	Condition parti = lock.newCondition();
	private AtomicInteger nCor = new AtomicInteger(-1);

	@Override
	protected void partenza(Sciatore s) throws InterruptedException {
		lock.lock();
		try {
			while ( !possoPartire(s) )
				parti.await();
		} finally {
			lock.unlock();
		}
	}
	
	private boolean possoPartire(Sciatore s) {
		return s.getID()==nCor.get();
	}

	@Override
	protected int arrivo(Sciatore s) {
		classificaFinale.add(s);
		s.setPosArrivo(classificaFinale.size());
		return classificaFinale.size();
	}

	@Override
	protected boolean prossimo() {
		lock.lock();
		try {
			if ( nSciatori.get() < 1 )
				return false;
			nSciatori.decrementAndGet();
			nCor.incrementAndGet();
			parti.signalAll();
			return true;
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		GaraLock gl = new GaraLock();
		gl.test(5);
	}

}
