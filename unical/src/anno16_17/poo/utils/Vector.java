package anno16_17.poo.utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

public interface Vector<T> extends Iterable<T>{
	
	default int size(){
		int c=0;
		for( T e: this ) c++;
		return c;
	}
	default int indexOf( T elem ){
		int i=-1;
		for( T e: this ){
			i++;
			if( e.equals(elem) ) return i;
		}
		return -1;
	}
	default boolean contains( T elem ){
		for( T e: this ) 
			if( e.equals(elem) ) return true;
		return false;
	}
	default T get( int indice ){
		if( indice<0 || indice>=size() )
			throw new IndexOutOfBoundsException();
		int i=-1;
		for( T e: this ){
			i++;
			if( i==indice ) return e;
		}
		return null;
	}
	T set( int indice, T elem );
	void add( T elem );
	void add( int indice, T elem );
	
	default void remove( T elem ) {
		Iterator<T> it=this.iterator();
		while( it.hasNext() ){
			T x=it.next();
			if( x.equals(elem) ){ it.remove(); break; }
		}		
	}
	default T remove( int indice ){ 
		if( indice<0 || indice>=size() )
			throw new IndexOutOfBoundsException();
		int i=-1;
		Iterator<T> it=this.iterator();
		while( it.hasNext() ){
			i++;
			T x=it.next();
			if( i==indice ){ it.remove(); return x; }
		}
		return null; //mai eseguita
	}
	default void clear(){
		Iterator<T> it=this.iterator();
		while( it.hasNext() ){
			it.next();
			it.remove();
		}		
	}
	default boolean isEmpty(){
		return size()==0;
	}
	
	Vector<T> subVector( int da, int a );
	
	Iterator<T> iterator();
	ListIterator<T> listIterator();
	ListIterator<T> listIterator(int pos);
	
	default void sort (Comparator<T> c){
		boolean scambi = true; int lim = size()-1; int ius = -1;
		while (scambi == true){
			scambi = false;
			for (int i = 0; i < lim; i++){
				T temp1 = this.get(i); T temp2 = this.get(i+1);
				if (c.compare(temp1, temp2) > 0){
					T tempScambio = temp1; temp1 = temp2; temp2 = tempScambio;
					scambi = true; ius = i;
				}
			}
			lim = ius;
		}
	}
}//Vector