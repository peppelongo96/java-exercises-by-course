package anno16_17.sisop.TracciaDischi;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class Processo implements Runnable {
	
	Controllore c;
	Random r = new Random();
	
	public Processo(Controllore c) {
		this.c = c;
	}
	
	@Override
	public void run() {
		while ( true ) {
			int a = scegliDisco();
			int b = scegliDisco();
			try {
				System.out.println(Thread.currentThread().getName()+" ha scelto dischi "+a+" e "+b);
				c.allocaDischi(a, b);
				System.out.println(Thread.currentThread().getName()+" usa dischi "+a+" e "+b);
				utilizza();
				c.rilasciaDischi(a, b);
				System.out.println(Thread.currentThread().getName()+" ha rilasciato dischi "+a+" e "+b);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private int scegliDisco() {
		return r.nextInt(3);
	}
	
	private void utilizza() throws InterruptedException{
		TimeUnit.MILLISECONDS.sleep(r.nextInt(1000-100+1)+100);
	}

}
