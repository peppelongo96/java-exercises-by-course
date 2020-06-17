/**
 * 
 */
package anno16_17.algDat.Traccia23luglio2018;

import anno16_17.algDat.AlberoBinario;

/**
 * @author Giuseppe Longo
 *
 */
class Traccia23luglio2018 {
	
	public static boolean verifica ( AlberoBinario a, int l ) {
		if ( a==null ) return false;
		if ( l<0 ) return true;
		int h_a = altezzaAB(a);
		for (int la = l+1; la < h_a; la++) {
			if ( verifica2(a, 0, la)>=2 ) return true;
		}
		return false;
	}
	
	private static int altezzaAB ( AlberoBinario a ) {
		if ( a==null ) return 0;
		return 1 + Math.max(altezzaAB(a.destro()), altezzaAB(a.sinistro()));
	}
	
	private static int verifica2 ( AlberoBinario a, int lcur, int la ) {
		if( a.sinistro()==null && a.destro()==null ) return 0;
		if ( lcur==la && a.val()==0 ) return 1;
		return verifica2(a.destro(), lcur+1, la) + verifica2(a.sinistro(), lcur+1, la);
	}
	
	/* COMPLESSITA' TEMPORALE
	 * - Caso migliore: teta( 1 ), se l<0
	 * - Caso peggiore: teta( n + n*n ), dove n è il numero di nodi. 
	 * 					Si ipotizza albero degenere che quindi costa n per la definizione dell'altezza.
	 * 
	 * 
	 * COMPLESSITA' SPAZALE
	 * - Caso migliore: teta( 1 )
	 * - Caso peggiore: teta( n ), dove n è il numero di nodi. 
	 * 					Si ipotizza albero degenere che quindi costa n per la definizione dell'altezza.
	 */

 
}
