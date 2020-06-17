package anno16_17.sisop.TracciaPiscinaCorsie;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class PiscinaSem extends Piscina{
	
	private Semaphore[] listaCorsie = new Semaphore[5];
	private Semaphore attivaPiscina = new Semaphore(0);
	private Random r = new Random();
	
	public PiscinaSem() {
		for (int i = 0; i < listaCorsie.length; i++) {
			listaCorsie[i] = new Semaphore(4, true);
		}
	}
	
	@Override
	protected void apriPiscina() {
		attivaPiscina.release();		
	}
	
	@Override
	protected void chiudiPiscina() throws InterruptedException {
		attivaPiscina.acquire();
	}

	@Override
	protected int scegliCorsia() {
		int corsia = 0;
		int postiMax = listaCorsie[0].availablePermits();
		for (int i = 1; i < listaCorsie.length; i++) {
			if ( listaCorsie[i].availablePermits() > postiMax ){
				corsia = i;
				postiMax = listaCorsie[i].availablePermits();
			}
		}
		return corsia;
	}

	@Override
	protected void nuota(int nCorsia) throws InterruptedException {
		while ( attivaPiscina.availablePermits()!=1 ) {}
		listaCorsie[nCorsia].acquire();
		System.out.println(Thread.currentThread().getName()+" ha iniziato a nuotare in corsia "+nCorsia);
		TimeUnit.MINUTES.sleep(r.nextInt(30)+30);
		listaCorsie[nCorsia].release();
	}
	
	public static void main(String[] args) {
		PiscinaSem ps = new PiscinaSem();
		ps.test(5);
	}

}
