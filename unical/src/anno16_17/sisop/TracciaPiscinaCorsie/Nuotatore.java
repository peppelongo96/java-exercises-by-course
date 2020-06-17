package anno16_17.sisop.TracciaPiscinaCorsie;

class Nuotatore implements Runnable {
	
	Piscina p;
	
	public Nuotatore( Piscina p) {
		this.p = p;
	}
	
	@Override
	public void run() {
		try {
			p.nuota(p.scegliCorsia());
			p.docciaEVia(Thread.currentThread());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
