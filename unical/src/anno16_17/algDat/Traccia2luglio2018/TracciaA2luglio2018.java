/**
 * 
 */
package anno16_17.algDat.Traccia2luglio2018;

import anno16_17.algDat.AlberoBinario;

/**
 * @author Giuseppe Longo
 *
 */
class TracciaA2luglio2018 {
		
	public static  boolean verifica ( AlberoBinario a ) {
		if ( a==null ) return false;
		if ( a.sinistro()==null && a.destro()==null ) return false;
		return (verifica2(a.sinistro(),a.val()) && verifica2(a.destro(),a.val())) || verifica(a.sinistro()) || verifica(a.destro());
	}
		
	private static boolean verifica2 ( AlberoBinario a, int val ) {
		if ( a==null ) return true;
		if ( a.sinistro()==null && a.destro()==null ) 
			return a.val()==val;
		return verifica2(a.sinistro(),a.val()) && verifica2(a.destro(),a.val());
	}
	
	/* COMPLESSITA' TEMPORALE
	 * - Caso migliore: teta( 1 ), se l'albero non ha nodi non foglia
	 * - Caso peggiore: teta( n^2 ), dove n è il numero di nodi. 
	 * 
	 * 
	 * COMPLESSITA' SPAZALE
	 * - Caso migliore: teta( 1 )
	 * - Caso peggiore: teta( 2n ), dove n è il numero di nodi. 
	 * 					Si ipotizza albero degenere che quindi costa n per la definizione dell'altezza.
	 */
	
}
