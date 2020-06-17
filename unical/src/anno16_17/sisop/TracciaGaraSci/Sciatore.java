package anno16_17.sisop.TracciaGaraSci;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class Sciatore implements Runnable,Comparable<Sciatore>{
	
	private final int ID;
	private int tempoArrivo;
	private int posArrivo;
	private Gara g;
	
	private Random r = new Random();
	
	public Sciatore(int id, Gara g) {
		this.ID = id;
		this.g = g;
	}
	
	void setPosArrivo ( int p ) {
		this.posArrivo = p;
	}
	
	int getID() {
		return this.ID;
	}
	
	@Override
	public String toString() {
		return tempoArrivo+"-->"+
				"Maglia."+this.ID+" in posizione "+this.posArrivo;
	}

	@Override
	public void run() {
		try {
			g.partenza(this);
			completaGara();
			System.out.println("Maglia."+this.ID+" arriva "+g.arrivo(this)+"°");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void completaGara() throws InterruptedException {
		tempoArrivo = r.nextInt(20)+50;
		TimeUnit.SECONDS.sleep(tempoArrivo);
	}

	@Override
	public int compareTo(Sciatore s) {
		if ( this.tempoArrivo>s.tempoArrivo ) return 1;
		if ( this.tempoArrivo<s.tempoArrivo ) return -1;
		return 0;
	}

}
