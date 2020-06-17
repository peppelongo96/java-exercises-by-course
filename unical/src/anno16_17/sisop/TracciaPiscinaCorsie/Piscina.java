package anno16_17.sisop.TracciaPiscinaCorsie;

import java.util.concurrent.TimeUnit;

abstract class Piscina {
	
	protected Thread[] listaNuotatori;
	
	protected abstract int scegliCorsia();
	
	protected abstract void apriPiscina();
	protected abstract void chiudiPiscina() throws InterruptedException;
	
	protected abstract void nuota( int nCorsia) throws InterruptedException;
	
	@SuppressWarnings("static-access")
	protected void docciaEVia( Thread t) throws InterruptedException {
		System.out.println(t.getName()+" va in doccia");
		t.sleep(20000);
		t.interrupt();
		System.out.println(t.getName()+" è andato via");
	}
	
	protected void pausaNuoto() throws InterruptedException {
		TimeUnit.HOURS.sleep(1);
	}
	
	protected void sgombraPiscina() throws InterruptedException {
		for (int i = 0; i < listaNuotatori.length; i++) {
			docciaEVia(listaNuotatori[i]);
		}
	}
	
	public void test( int nNuot) {
		listaNuotatori = new Thread[nNuot];
		new Thread( new Istruttore(this), "Istruttore").start();
		for (int i = 0; i < nNuot; i++) {
			listaNuotatori[i]= new Thread( new Nuotatore(this), "Nuotatore-"+i);
			listaNuotatori[i].start();
		}
	}

}
