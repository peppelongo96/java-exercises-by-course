/**
 * 
 */
package anno17_18.retidicalcolatori.Appello14luglio2015.Esercizio1;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author Giuseppe Longo
 *
 */
class Processo implements Serializable, Callable<Boolean> {
	
	private Callable<String>[] arrayTask;
	private boolean allDone = false;

	Processo(int nTask) {
		arrayTask = new Callable[nTask];
		for (int i = 0; i < arrayTask.length; i++) {
			arrayTask[i] = new Operazione(i);
		}
	}
	
	boolean getAllDone() {
		return allDone;
	}
	
	void setAllDone( boolean b ) {
		allDone = b;
	}
	
	private static class Operazione implements Callable<String> {
		
		private int ID;
		
		private Operazione ( int ID ) {
			this.ID = ID;
		}
		
		public String call() throws Exception {
			return "OPERAZIONE "+ID;
		}
		
	}
	
	Callable<String>[] getArrayTask() {
		return arrayTask;
	}

	public Boolean call() throws Exception {
		while ( !allDone ) {}
		return true;
	}

}
