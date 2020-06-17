/**
 * 
 */
package anno16_17.algDat.Traccia14luglio2014;

import anno16_17.algDat.AlberoBinario;

/**
 * @author peppe
 *
 */
class TracciaA14luglio2014 {
	
	public static boolean verificaDueNodi ( AlberoBinario a ) {
		if ( a==null ) return false;
		if ( verifica(a,0)>=2 ) return true;
		return false;
	}

	private static int verifica ( AlberoBinario a, int liv ) {
		if ( a==null ) return 0;
		return ( (a.val()+liv)<0 ? 1 : 0 ) + verifica(a.sinistro(),liv+1) + verifica(a.destro(),liv+1);
	}
	
	/* COMPLESSITA' TEMPORALE
	 * - Caso migliore: teta( n ) 
	 * - Caso peggiore: teta( n ) 
	 * 
	 * COMPLESSITA' SPAZALE
	 * - Caso migliore: teta( log(n) ) con albero completo
	 * - Caso peggiore: teta( n ), dove n è il numero di nodi. 
	 * 					Si ipotizza albero degenere.
	 */
}
