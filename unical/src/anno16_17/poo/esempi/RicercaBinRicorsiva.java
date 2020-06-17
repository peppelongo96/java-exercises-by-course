package anno16_17.poo.esempi;

public class RicercaBinRicorsiva{
	private static int med;
	
	public static int trova(int[] array, int elem){
		return esegui(array, elem, 0, array.length);
	}
	
	public static <T extends Comparable<? super T>> int trova(T[] array, T elem){
		return esegui(array,elem,0,array.length);
	}
	
	private static int esegui(int[] array, int elem, int in, int fin){
		med = (in+fin)/2;
		if ( med<=0 || med>=array.length ) return -1;
		if ( elem == (array[med]) ) return med;
		if ( elem >(array[med])) return esegui(array,elem,med+1,fin);
		else return esegui(array,elem,0,med-1);
	}
	
	private static <T extends Comparable<? super T>> int esegui(T[] array, T elem, int in, int fin){
		med = (in+fin)/2;
		if ( med<=0 || med>=array.length ) return -1;
		if ( elem.equals(array[med]) ) return med;
		if ( elem.compareTo(array[med]) > 0) return esegui(array,elem,med+1,fin);
		else return esegui(array,elem,0,med-1);
	}
	
	
	public static void main(String[] args) {
		int[] a = {79,79,79,100};
		System.out.println(RicercaBinRicorsiva.trova(a, 79));
	}
}
