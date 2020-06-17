package anno16_17.sisop.TracciaGaraSci;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class GaraSem extends Gara {
	
	private Semaphore parti = new Semaphore(0, true);
	private AtomicInteger nCor = new AtomicInteger(0);

	@Override
	protected void partenza(Sciatore s) throws InterruptedException {
		parti.acquire();
		while ( s.getID()!= nCor.get() ) {
			parti.release();
			parti.acquire();
		}
		nCor.incrementAndGet();
	}
			
	@Override
	protected int arrivo(Sciatore s) {
		classificaFinale.add(s);
		s.setPosArrivo(classificaFinale.size());
		return classificaFinale.size();
	}

	@Override
	protected boolean prossimo() {
		if ( nSciatori.get() < 1 )
			return false;
		nSciatori.decrementAndGet();
		parti.release();
		return true;
	}
	
	public static void main(String[] args) {
		GaraSem gs = new GaraSem();
		gs.test(5);
	}

}
