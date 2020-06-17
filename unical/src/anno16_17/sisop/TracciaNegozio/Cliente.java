package anno16_17.sisop.TracciaNegozio;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class Cliente implements Runnable{
	
	private Negozio n;
	private Random r = new Random();
	private int nProdottiScelti;
	
	public Cliente( Negozio n ) {
		this.n = n;
	}

	@Override
	public void run() {
		try {
			scegliProdotti();
			n.svuotaCarrello(nProdottiScelti);
			n.paga();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void scegliProdotti() throws InterruptedException{
		nProdottiScelti = (r.nextInt(10)+5);
		TimeUnit.SECONDS.sleep(5*nProdottiScelti);
		System.out.println(Thread.currentThread().getName()+" ha scelto "+nProdottiScelti+" prodotti");
	}
}
