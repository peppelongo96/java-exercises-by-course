package anno16_17.sisop.TracciaCioccolatini;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

class Persona implements Runnable {
	
	Scatola s;
	Random r = new Random();
	
	public Persona( Scatola s ) {
		this.s = s;
	}

	@Override
	public void run() {
		try {
			LinkedList<Integer> manciata = s.get();
			scegliCioccolatino(manciata);
			s.put(manciata);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void scegliCioccolatino( LinkedList<Integer> manciata ) throws InterruptedException{
		TimeUnit.SECONDS.sleep(3);
		int scelta = r.nextInt(manciata.size());
		System.out.println(Thread.currentThread().getName()+ " ha scelto un chock di tipo "+manciata.remove(scelta));
	}

}
