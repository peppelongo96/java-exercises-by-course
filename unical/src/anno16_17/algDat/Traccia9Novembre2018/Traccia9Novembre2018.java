package anno16_17.algDat.Traccia9Novembre2018;

import anno16_17.algDat.AlberoBinario;

class Traccia9Novembre2018 {
		
	public static boolean verifica ( AlberoBinario a ) {
		if ( a==null ) return false;
		if ( a.sinistro()==null && a.destro()==null ) 
			return a.val()<0;
		if ( a.val()<0 ) 
			return false;
		return verifica(a.sinistro()) || verifica(a.destro());
	}
	
	/* COMPLESSITA' TEMPORALE
	 * - Caso migliore: teta( 1 ), se l'albero ha un solo nodo oppure la radice ha valore negativo
	 * - Caso peggiore: teta( n ), se tutti gli antenati hanno valore positivo
	 * 					 * 
	 * 
	 * COMPLESSITA' SPAZALE
	 * - Caso migliore: teta( 1 ), se l'albero ha un solo nodo oppure la radice ha valore negativo
	 * - Caso peggiore: teta( n ), se l'albero è degenere. L'albero non è bilanciato quindi le chiamate sullo stack non
	 *                             crescono in log2n ma linearmente
	 */

}
