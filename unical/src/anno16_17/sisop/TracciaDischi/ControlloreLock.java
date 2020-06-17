package anno16_17.sisop.TracciaDischi;

import java.util.LinkedList;
import java.util.concurrent.locks.*;

class ControlloreLock extends Controllore{
	
	Lock lock = new ReentrantLock();
	Condition alloca = lock.newCondition();
	Condition disponibilita = lock.newCondition();
	
	LinkedList<Thread> coda = new LinkedList<>();
	LinkedList<Thread> codaNonDisponibili = new LinkedList<>();

	@Override
	protected void allocaDischi(int a, int b) throws InterruptedException {
		lock.lock();
		try {
			codaNonDisponibili.add(Thread.currentThread());
			while ( Thread.currentThread()!= codaNonDisponibili.getFirst() &&
					(listaDischi[a].get() == 5 || listaDischi[a].get()== 5))
				disponibilita.await();
			codaNonDisponibili.removeFirst();
			coda.add(Thread.currentThread());
			while ( !possoAllocare() )
				alloca.await();
			listaDischi[a].incrementAndGet();
			listaDischi[b].incrementAndGet();
		} finally {
			lock.unlock();
		}
		
	}

	private boolean possoAllocare() {
		return Thread.currentThread() == coda.getFirst();
	}
	
	@Override
	protected void rilasciaDischi(int a, int b) throws InterruptedException {
		lock.lock();
		try {
			listaDischi[b].decrementAndGet();
			listaDischi[a].decrementAndGet();
			coda.removeFirst();
			disponibilita.signalAll();
			alloca.signal();
			System.out.println("adsfsdgdsfgdfsgdfsgdsfgs");
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		ControlloreLock cl = new ControlloreLock();
		cl.test(100);
	}

}
