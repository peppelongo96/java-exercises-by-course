package anno16_17.sisop.TracciaPiscinaCorsie;

import java.util.concurrent.TimeUnit;

class Istruttore implements Runnable {
	
	Piscina p;
	
	public Istruttore( Piscina p) {
		this.p = p;
	}

	@Override
	public void run() {
		while ( true ) {
			p.apriPiscina();
			try {
				TimeUnit.HOURS.sleep(4);
				System.out.println("PAUSA PISCINA");
				p.chiudiPiscina();
				p.sgombraPiscina();
				TimeUnit.HOURS.sleep(1);
				p.apriPiscina();
				TimeUnit.HOURS.sleep(5);
				p.sgombraPiscina();
				p.chiudiPiscina();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}

}
