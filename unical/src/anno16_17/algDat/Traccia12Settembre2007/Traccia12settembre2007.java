/**
 * 
 */
package anno16_17.algDat.Traccia12Settembre2007;

import anno16_17.algDat.AlberoBinario;

/**
 * @author peppe
 *
 */
class Traccia12settembre2007 {
	
	public static boolean eRipetuto ( AlberoBinario a, int x ) {
		if ( a==null ) return false;
		return (verifica(a.sinistro(),x) && verifica(a.destro(),x)) || eRipetuto(a.sinistro(), x) || eRipetuto(a.destro(), x);
	}
	
	private static boolean verifica ( AlberoBinario a, int x ) {
		if ( a==null ) return false;
		return a.val()==x || verifica(a.sinistro(),x) || verifica(a.destro(),x);
	}
	
	/* COMPLESSITA' TEMPORALE
	 * - Caso migliore: teta( n^2 ) 
	 * - Caso peggiore: teta( n^2 )
	 * 					  
	 * 
	 * COMPLESSITA' SPAZALE
	 * - Caso migliore: teta( log(n) ) se l'albero è completo 
	 * - Caso peggiore: teta( n ), dove n è il numero di nodi. 
	 * 					Si ipotizza albero degenere.
	 */

}
