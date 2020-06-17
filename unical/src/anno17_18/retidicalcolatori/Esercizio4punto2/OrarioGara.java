/**
 * 
 */
package anno17_18.retidicalcolatori.Esercizio4punto2;

/**
 * @author Giuseppe Longo
 *
 */
class OrarioGara {
	
	private int ora, minuti;
	
	OrarioGara( int ora, int minuti ) {
		this.ora = ora;
		this.minuti = minuti;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ora+":"+minuti;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(OrarioGara og) {
		return ora == og.ora && minuti == og.minuti;
	}
	
	void avanza() {
		minuti++;
		if ( minuti==60 ) ora++;
	}
	
	int getOra() { return ora; }
	int getMinuti() { return minuti; }

}
