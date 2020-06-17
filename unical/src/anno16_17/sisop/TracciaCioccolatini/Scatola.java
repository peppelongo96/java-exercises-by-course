package anno16_17.sisop.TracciaCioccolatini;

import java.util.LinkedList;

abstract class Scatola {
	
	protected LinkedList<Integer> listaCioccolatini = new LinkedList<>();
	
	public Scatola( int nCiocc, int nTipi ) {
		if ( nCiocc%nTipi!=0 )
			throw new IllegalArgumentException();
		int nPerTipo = nCiocc/nTipi;
		for (int i = 0; i < nTipi; i++) 
			for (int j = 0; j < nPerTipo; j++)
				listaCioccolatini.add(i);
	}

	protected abstract LinkedList<Integer> get() throws InterruptedException;
	
	protected abstract void put(LinkedList<Integer> c) throws InterruptedException;
	
	public void test ( int persone ) {
		for (int i = 0; i < persone; i++) {
			new Thread( new Persona(this), "Persona-"+i).start();
		}
	}
}
