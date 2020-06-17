package anno16_17.poo.progettoEsame;

import java.util.ListIterator;
import java.util.Comparator;
import java.util.Iterator;

public interface List<T> extends Collection<T>{

	ListIterator<T> listIterator();

	ListIterator<T> listIterator( int pos );
	
	enum Spostamento{ NEXT, PREVIOUS };

	static <E extends Comparable<? super E>> int binarySearch( List<E> l, E e ){
		if ( l==null ) throw new IllegalArgumentException();
		return RicercaEOrdinamento.binarySearch(l, e, 0, l.size());
	}//binarySearch (ordinamento naturale)

	default int binarySearch( Comparator<T> comp, T e ){
		if ( comp==null ) throw new IllegalArgumentException();
		return RicercaEOrdinamento.binarySearch(this, e, 0, this.size(), comp);
	}//binarySearch (con comparator)


	default void sort( Comparator<T> comp ){
		if ( comp==null ) throw new IllegalArgumentException();
		RicercaEOrdinamento.mergeSort(this, comp);
	}//sort (con comparator)

	static <E extends Comparable<? super E>> void sort(List<E> l){
		if ( l==null ) throw new IllegalArgumentException();
		RicercaEOrdinamento.mergeSort(l);
	}//sort (ordinamento naturale)


	default void add( int indice, T e ){
		//aggiunge l'elemento alla posizione definita dall'indice
		if ( indice < 0 || indice >= this.size() ) throw new IndexOutOfBoundsException();
		ListIterator<T> it = this.listIterator(indice);
		it.add(e);
	}//add

	default void addAll( int indice, Collection<T> c ){
		//aggiunge la collezione a partire dall'indice
		if ( indice < 0 || indice >= this.size() ) throw new IndexOutOfBoundsException();
		if ( c==null ) throw new IllegalArgumentException();
		ListIterator<T> it = this.listIterator(indice);
		for ( T e : c )
			it.add(e);
	}//addAll

	default T get( int indice ){
		//ritorna l'elemento alla posizione specificata
		if ( indice < 0 || indice >= this.size() ) throw new IndexOutOfBoundsException();
		ListIterator<T> it = this.listIterator(indice);
		return it.next();
	}//get

	default int indexOf( T e ){
	//ritorna l'indice posizione della prima occorrenza di e; -1 altrimenti
		int ris = 0;
		Iterator<T> it = this.iterator();
		while ( it.hasNext() ){
			T elem = it.next();
			if ( elem.equals(e) ) return ris;
			ris++;
		}
		return -1;
	}//indexOf

	default int lastIndexOf( T e ){
	//ritorna l'indice posizione della ultima occorrenza di e; -1 altrimenti
		int ris = this.size();
		ListIterator<T> it = listIterator(ris);
		while ( it.hasPrevious() ){
			T elem = it.previous();
			if ( elem.equals(e) ) return ris;
			ris--;
		}
		return -1;
	}//lastIndexOf

	default T remove( int indice ){
	//rimuove l'elemento alla posizione specificata
		if ( indice<0 || indice>=this.size() ) throw new IndexOutOfBoundsException();
		ListIterator<T> it = listIterator(indice);
		T ris = it.next();
		it.remove();
		return ris;
	}//remove

	default T set( int indice, T e ){
	//rimpiazza l'elemento alla posizione indice con e; ritorna il vecchio elemento
		if ( indice<0 || indice>=this.size() ) throw new IndexOutOfBoundsException();
		ListIterator<T> it = listIterator(indice);
		T ris = it.next();
		it.set(e);
		return ris;
	}//set

}//List
