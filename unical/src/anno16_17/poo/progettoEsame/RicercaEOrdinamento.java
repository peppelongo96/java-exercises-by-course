package anno16_17.poo.progettoEsame;

import java.util.Comparator;

class RicercaEOrdinamento {
	
// MERGE_SORT CON COMPARATOR
	static <T> void mergeSort(List<T> list, Comparator<T> comp){
		mergeSort(list, comp, 0, list.size()-1);
	}
	
	private static <T> void mergeSort(List<T> list, Comparator<T> comp, int in, int fin){
		if (in < fin){
			int med = (in+fin)/2;
			mergeSort(list, comp, in, med);
			mergeSort(list, comp, med+1, fin);
			merge(list, comp, in, med, fin);
		}
	}
	
	private static <T> void merge(List<T> list, Comparator<T> comp, int in, int med, int fin){
		@SuppressWarnings("unchecked")
		T[] aux = (T[]) new Object[(fin+1)-in];
		int i = in, j = med+1, k = 0;
		while ( i<=med && j<=fin){
			if (comp.compare(list.get(i), list.get(j)) < 0){
				aux[k] = list.get(i); i++; k++;
			}
			else{
				aux[k] = list.get(j); j++; k++;
			}
		}
		//gestione residuo
		while ( i <=med ){ aux[k] = list.get(i); i++; k++; }
		while ( j <=fin ){ aux[k] = list.get(j); j++; k++; }
		//ricopia aux su list
		for (k = 0; k<aux.length; k++)
			list.set(k+in, aux[k]);
	}
	
//MERGE SORT
	static <T extends Comparable<? super T>> void mergeSort(List<T> list){
		mergeSort(list, 0, list.size()-1);
	}
	
	private static <T extends Comparable<? super T>> void mergeSort(List<T> list, int in, int fin){
		if (in < fin){
			int med = (in+fin)/2;
			mergeSort(list, in, med);
			mergeSort(list, med+1, fin);
			merge(list, in, med, fin);
		}
	}
	
	private static <T extends Comparable<? super T>> void merge(List<T> list, int in, int med, int fin){
		@SuppressWarnings("unchecked")
		T[] aux = (T[]) new Comparable[(fin+1)-in];
		int i = in, j = med+1, k = 0;
		while ( i<=med && j<=fin){
			if ( list.get(i).compareTo(list.get(j)) < 0){
				aux[k] = list.get(i); i++; k++;
			}
			else{
				aux[k] = list.get(j); j++; k++;
			}
		}
		//gestione residuo
		while ( i <=med ){ aux[k] = list.get(i); i++; k++; }
		while ( j <=fin ){ aux[k] = list.get(j); j++; k++; }
		//ricopia aux su list
		for (k = 0; k<aux.length; k++)
			list.set(k+in, aux[k]);
	}
	

//RICERCA BINARIA
	static <T extends Comparable<? super T>> int binarySearch(List<T> l, T elem, int in, int fin){
		int med = (in+fin)/2;
		if ( med<=0 || med>=l.size() ) return -1;
		if ( elem.equals(l.get(med)) ) return med;
		if ( elem.compareTo(l.get(med)) > 0) return binarySearch(l,elem,med+1,fin);
		else return binarySearch(l,elem,0,med-1);
	}
	
//RICERCA BINARIA CON COMPARATOR
	static <T> int binarySearch(List<T> l, T elem, int in, int fin, Comparator<T> comp){
		int med = (in+fin)/2;
		if ( med<=0 || med>=l.size() ) return -1;
		if ( elem.equals(l.get(med)) ) return med;
		if ( comp.compare(elem, l.get(med)) > 0) return binarySearch(l,elem,med+1,fin,comp);
		else return binarySearch(l,elem,0,med-1,comp);
	}
	
}
