package anno16_17.poo.eserciziPerEsame;

import java.util.Arrays;

public class EliminaMultipli {
	
	public static int[] eliminaMultipli (int[] array, int elem ) {
		if ( array==null ) throw new IllegalArgumentException();
		int[] ris = new int [array.length];
		esegui(ris,0,array,0,elem);
		return ris;
	}//eliminaMultipli
	
	private static void esegui(int[] ris, int pos1, int[] array, int pos2, int elem ) {
		if ( pos2==array.length-1 ) return;
		if ( array[pos2]%elem!=0 ) {
			ris[pos1] = array[pos2];
			esegui(ris,pos1+1,array,pos2+1,elem);
		}
		else esegui(ris,pos1,array,pos2+1,elem);
	}//esegui

	public static void main(String[] args) {
		int[] es = {2,4,6,9,8};
		System.out.println(Arrays.toString(eliminaMultipli(es,2)));
	}

}
