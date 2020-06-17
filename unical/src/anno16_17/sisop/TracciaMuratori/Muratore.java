package anno16_17.sisop.TracciaMuratori;

import java.util.concurrent.TimeUnit;

class Muratore implements Runnable {
	
	Casa c;
	int tipoLavoro;
	
	public Muratore(Casa c, int tipoLavoro) {
		this.c = c;
		this.tipoLavoro = tipoLavoro;
	}
	
	@Override
	public void run() {
		while ( true) {
			try {
				preparaMateriale();
				if ( !c.inizia( tipoLavoro ) ) break;
				lavora();
				c.termina();
				riposa();
			} catch ( InterruptedException ie ) {}
		}
	}
	
	private void preparaMateriale() throws InterruptedException {
		int tempo = 500;
		if ( tipoLavoro==c.CEMENTO ) 
			tempo = 700;
		TimeUnit.MILLISECONDS.sleep(tempo);
		System.out.println(Thread.currentThread().getName()+" ha scelto i materiali");
	}
	
	private void lavora() throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
		if ( tipoLavoro==c.MATTONE ) {
			c.livelloFila.incrementAndGet();
			System.out.println("Muro-"+c.muriCompletati.get()+" raggiunge fila-"+c.livelloFila.get());
			if ( c.livelloFila.get()==c.filaPerMuro ) {
				System.out.println("MURO-"+c.muriCompletati.get()+" E' STATO COMPLETATO");
				c.muriCompletati.incrementAndGet();
				c.livelloFila.set(0);
			}
		}
	}
	
	private void riposa() throws InterruptedException {
		TimeUnit.SECONDS.sleep(5);
	}

}
