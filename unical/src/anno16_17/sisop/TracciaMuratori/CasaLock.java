package anno16_17.sisop.TracciaMuratori;

import java.util.LinkedList;
import java.util.concurrent.locks.*;

class CasaLock extends Casa {
	
	private Lock lock = new ReentrantLock();
	private Condition inizia = lock.newCondition();
	private Condition turnoMat = lock.newCondition();
	
	private LinkedList<Thread> coda = new LinkedList<>();
	private LinkedList<Thread> codaMattone = new LinkedList<>();
	
	private boolean turnoMattone = false;
	
	@Override
	protected boolean inizia(int t) throws InterruptedException {
		lock.lock(); 
		try {
			codaMattone.add(Thread.currentThread());
			while ( Thread.currentThread()!= codaMattone.getFirst() 
					&& t==MATTONE && turnoMattone==false )
				turnoMat.await();
			codaMattone.removeFirst();
			coda.addLast(Thread.currentThread());
			while ( !possoIniziare() )
				inizia.await();
			return muriCompletati.get()<4;
		} finally {
			lock.unlock();
		}
	}
	
	private boolean possoIniziare() {
		return Thread.currentThread()==coda.getFirst();
	}
	
	@Override
	protected void termina() {
		lock.lock();
		try {
			turnoMattone = !turnoMattone;
			coda.removeFirst();
			if ( turnoMattone==true )
				turnoMat.signalAll();
			inizia.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		CasaLock cl = new CasaLock();
		cl.test(20, 7, 5);
	}

}
