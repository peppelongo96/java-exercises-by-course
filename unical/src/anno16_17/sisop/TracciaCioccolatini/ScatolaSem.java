package anno16_17.sisop.TracciaCioccolatini;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;

class ScatolaSem extends Scatola {
	
	Semaphore coda = new Semaphore(1, true);
	Random r = new Random();

	public ScatolaSem(int nCiocc, int nTipi) {
		super(nCiocc, nTipi);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected LinkedList<Integer> get() throws InterruptedException {
		coda.acquire();
		LinkedList<Integer> manciata = new LinkedList<>();
		while ( listaCioccolatini.size()>0 ) {
			manciata.add(listaCioccolatini.remove(r.nextInt(listaCioccolatini.size())));
			if ( manciata.size()==5 )
				break;
		}
		return manciata;
	}

	@Override
	protected void put(LinkedList<Integer> manciata) {
		for ( Integer i : manciata )
			listaCioccolatini.add(i);
		coda.release();
	}
	
	public static void main(String[] args) {
		ScatolaSem ss = new ScatolaSem(100, 5);
		ss.test(100);
	}

}
