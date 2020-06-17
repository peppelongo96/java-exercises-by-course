package anno16_17.sisop.TracciaMuratori;

import java.util.concurrent.*;

class CasaSem extends Casa{
	
	private Semaphore coda = new Semaphore(1,true);
	private boolean turnoMattone = false;

	@Override
	protected boolean inizia(int t) throws InterruptedException{
		coda.acquire();
		while ( (t==CEMENTO && turnoMattone==true)
				|| (t==MATTONE && turnoMattone==false) ) {
			coda.release();
			coda.acquire();
		}
		return muriCompletati.get()<4;
	}

	@Override
	protected void termina() {
		turnoMattone = !turnoMattone;
		coda.release();
	}
	
	public static void main(String[] args) {
		CasaSem cs = new CasaSem();
		cs.test(20, 7, 5);
	}

}
