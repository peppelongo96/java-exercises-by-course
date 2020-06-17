package anno16_17.sisop.TracciaNegozio;

import java.util.concurrent.TimeUnit;

abstract class Negozio {

	protected abstract void svuotaCarrello( int n ) throws InterruptedException;
	
	protected abstract void paga() throws InterruptedException;
	
	protected abstract void scansionaProdotti() throws InterruptedException;
	
	protected int prodottiInCassa;
	protected String clienteCor;
		
	public void test() throws InterruptedException{
		new Thread( new CassaAutomatica(this), "Cassa").start();
		int i = 0;
		while ( true ) {
			new Thread( new Cliente(this), "Cliente-"+i).start();
			i++;
			TimeUnit.SECONDS.sleep(10);
		}
	}
}
