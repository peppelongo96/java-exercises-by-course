package anno16_17.algDat.Traccia7gennaio2013;

import anno16_17.algDat.AlberoBinario;

class Traccia7gennaio2013 {
	
	public static boolean verifica ( AlberoBinario a, AlberoBinario b ) {
		if ( a==null || b==null ) return true;
		return verifica2(a,0,b);
	}
	
	private static boolean verifica2 ( AlberoBinario a, int livA, AlberoBinario b ) {
		if ( a==null ) return false;
		return verifica3(a.val(),livA,b,0) || verifica2(a.sinistro(),livA+1,b) || verifica2(a.destro(),livA+1,b);
	}
	
	private static boolean verifica3 ( int aVal, int livA, AlberoBinario b, int livB ) {
		if ( b==null ) return false;
		if ( livB>livA && aVal==b.val() ) 
			return true;
		return verifica3(aVal,livA,b.sinistro(),livB+1) || verifica3(aVal,livA,b.destro(),livB+1);
	}
	
	/* COMPLESSITA' TEMPORALE
	 * - Caso migliore: teta( n*m ) 
	 * - Caso peggiore: teta( n^2 ) se n=m. 
	 * 					 * 
	 * 
	 * COMPLESSITA' SPAZALE
	 * - Caso migliore: teta( log(n)+log(m) ) 
	 * - Caso peggiore: teta( 2n ), con n=m
	 * 					Si ipotizza albero degenere.
	 */

}
