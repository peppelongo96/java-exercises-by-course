package anno16_17.sisop.TracciaPiscinaCorsie;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

class PiscinaLock extends Piscina {
	
	private AtomicInteger[] listaCorsie = new AtomicInteger[5];
	
	private Lock lock = new ReentrantLock(true);
	private Condition[] listaAttivaCorsie = new Condition[5];
	
	private boolean attivaPiscina = false;
	private LinkedList<Thread> listaNuotatori = new LinkedList<>();
	
	private LinkedList<Thread> codaRiattivaNuotatori = new LinkedList<>();
	private Condition riattivaNuotatore = lock.newCondition();
	
	private Random r = new Random();
	
	public PiscinaLock() {
		for (int i = 0; i < listaCorsie.length; i++) {
			listaCorsie[i] = new AtomicInteger(4);
			listaAttivaCorsie[i] = lock.newCondition();
		}
	}

	@Override
	protected int scegliCorsia() {
		int corsia = 0;
		int postiMax = listaCorsie[0].get();
		for (int i = 1; i < listaCorsie.length; i++) {
			if ( listaCorsie[i].get() > postiMax ){
				corsia = i;
				postiMax = listaCorsie[i].get();
			}
		}
		return corsia;
	}

	@Override
	protected void nuota(int nCorsia) throws InterruptedException {
		lock.lock();
		try {
			listaNuotatori.addLast(Thread.currentThread());
			while ( !possoNuotare() && listaCorsie[nCorsia].get()<1 ) {
				listaAttivaCorsie[nCorsia].await();
			}
			listaNuotatori.removeFirst();
			listaCorsie[nCorsia].decrementAndGet();
			for (int i = 0; i < listaAttivaCorsie.length; i++) {
				if ( i != nCorsia )
					 listaAttivaCorsie[i].signalAll();	
			}
			System.out.println(Thread.currentThread().getName()+" ha iniziato a nuotare in corsia "+nCorsia);
			codaRiattivaNuotatori.addLast(Thread.currentThread());
			lock.unlock();
//			TimeUnit.MINUTES.sleep(r.nextInt(30)+30);
			TimeUnit.SECONDS.sleep(10);
			lock.lock();
			while ( Thread.currentThread()!=codaRiattivaNuotatori.getFirst() )
				riattivaNuotatore.await();
			codaRiattivaNuotatori.removeFirst();
			riattivaNuotatore.signalAll();
			listaCorsie[nCorsia].incrementAndGet();
			listaAttivaCorsie[nCorsia].signalAll();
				
		} finally {
			lock.unlock();
		}
	}
	
	private boolean possoNuotare() {
		return Thread.currentThread() == listaNuotatori.getFirst()
				&& attivaPiscina==true;
	}

	@Override
	protected void apriPiscina() {
		attivaPiscina = true;
		
	}

	@Override
	protected void chiudiPiscina() throws InterruptedException {
		attivaPiscina = false;
	}

	public static void main(String[] args) {
		PiscinaLock pl = new PiscinaLock();
		pl.test(5);
	}

}
