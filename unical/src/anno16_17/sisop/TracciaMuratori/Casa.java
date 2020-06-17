package anno16_17.sisop.TracciaMuratori;

import java.util.concurrent.atomic.AtomicInteger;

abstract class Casa {
	
	protected abstract boolean inizia( int t ) throws InterruptedException;
	
	protected abstract void termina();
	
	protected int filaPerMuro;
	protected AtomicInteger livelloFila = new AtomicInteger(0);
	protected AtomicInteger muriCompletati = new AtomicInteger(0);
	protected final int CEMENTO = 0;
	protected final int MATTONE = 1;
	
	public void test( int filaPerMuro, int mCem, int mMatt ) {
		this.filaPerMuro = filaPerMuro;
		int n = 0;
		for (int i = 0; i < mMatt; i++) {
			new Thread ( new Muratore(this, MATTONE), "Muratore-"+n ).start();
			n++;
		}
		for (int i = 0; i < mCem; i++) {
			new Thread ( new Muratore(this, CEMENTO), "Muratore-"+n ).start();
			n++;
		}
	}

}
