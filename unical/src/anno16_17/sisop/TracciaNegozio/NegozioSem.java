package anno16_17.sisop.TracciaNegozio;

import java.util.concurrent.*;

public class NegozioSem extends Negozio{
	
	private Semaphore coda = new Semaphore(1, true);
	private Semaphore attivaCassa = new Semaphore(0);
	private Semaphore attivaPaga = new Semaphore(0);

	@Override
	protected void svuotaCarrello(int n) throws InterruptedException {
		coda.acquire();	
		prodottiInCassa = n;
		clienteCor = Thread.currentThread().getName();
		attivaCassa.release();
	}

	@Override
	protected void paga() throws InterruptedException {
		attivaPaga.acquire();
		System.out.println(Thread.currentThread().getName()+" ha pagato "+prodottiInCassa+" euro");
		coda.release();
		
	}

	@Override
	protected void scansionaProdotti() throws InterruptedException {
		attivaCassa.acquire();
		System.out.println("Cassa scansiona "+prodottiInCassa+" prodotti del "+clienteCor);
		TimeUnit.SECONDS.sleep(prodottiInCassa*2);
		attivaPaga.release();
	}
	
	public static void main(String[] args) throws InterruptedException {
		NegozioSem ns = new NegozioSem();
		ns.test();
	}

}
