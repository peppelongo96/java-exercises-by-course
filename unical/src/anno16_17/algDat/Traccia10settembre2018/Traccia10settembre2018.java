/**
 * 
 */
package anno16_17.algDat.Traccia10settembre2018;

import anno16_17.algDat.AlberoBinario;

/**
 * @author Giuseppe Longo
 *
 */
class Traccia10settembre2018 {
	
	public static boolean verifica( AlberoBinario a, int l ) {
		if ( a==null ) return true;
		if ( l<0 ) return false;
		return verifica2 ( a, a, l );
	}
	
	private static boolean verifica2 ( AlberoBinario a, AlberoBinario copia, int l ) {
		if ( a==null ) return true;
		if ( a.sinistro()==null && a.destro()==null ) return verifica3 ( copia, 0, a, l );
		return verifica2 ( a.sinistro(), copia, l ) && verifica2 ( a.destro(), copia, l );
	}
	
	private static boolean verifica3 ( AlberoBinario a, int la, AlberoBinario cur, int l ) {
		if ( a==null || la>=l ) return false;
		return cur.val()==a.val() || verifica3 ( a.sinistro(), la+1, cur, l ) || verifica3 ( a.destro(), la+1, cur, l ); 
	}
	
	/* COMPLESSITA' TEMPORALE
	 * - Caso migliore: teta( 1 ) se l<0
	 * - Caso peggiore: teta( n(n-1) ) ovvero teta( n^2 ), dove n è il numero di nodi. 
	 * 					 * 
	 * 
	 * COMPLESSITA' SPAZALIE
	 * - Caso migliore: teta( 1 ) se l<0
	 * - Caso peggiore: teta( n ), dove n è il numero di nodi. 
	 * 					Si ipotizza albero degenere.
	 */
	
}
