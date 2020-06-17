/**
 * 
 */
package anno16_17.algDat.Traccia3Settembre2010;

import anno16_17.algDat.AlberoBinario;

/**
 * @author peppe
 *
 */
class Traccia3Settembre2010 {

	public static boolean verifica ( AlberoBinario a ) {
		if ( a==null ) return false;
		int altezza = altezza(a);
		return verifica2(a,0,altezza-1);
	}
	
	private static boolean verifica2 ( AlberoBinario a, int l, int prof ) {
		if ( a==null ) return false;
		if ( a.sinistro()==null && a.destro()==null && l==prof ) 
			return a.val()>=0;
		return verifica2(a.sinistro(),l+1,prof) || verifica2(a.destro(),l+1,prof);	 
	}
	
	private static int altezza(AlberoBinario a) {
		if ( a==null ) return 0;
		return 1 + Math.max(altezza(a.sinistro()), altezza(a.destro()));
	}
	
	/* COMPLESSITA' TEMPORALE
	 * - Caso migliore: teta( 2n ) 
	 * - Caso peggiore: teta( 2n ) 
	 * 					 * 
	 * 
	 * COMPLESSITA' SPAZALE
	 * - Caso migliore: teta( n ) 
	 * - Caso peggiore: teta( n ),
	 * 					Si ipotizza albero degenere.
	 */

	
}
