package anno16_17.poo.utils;

import java.util.Iterator;

public interface Coda<T> extends Iterable<T>{
	
	default int size(){
		int size = 0;
		for (T e : this) size++;
		return size;
	}
	
	default void clear(){
		for (T e : this)
			e = null;
	}
	
	void put( T elem );
	
	default T get(){
		Iterator<T> it = iterator();
		T ris= it.next();
		it.remove();
		return ris;
	}
	
	default T peek(){
		Iterator<T> it = iterator();
		T ris=null;
		while (it.hasNext()){ris = it.next();}
		return ris;
	}
	
	default boolean isEmpty(){
		for ( T e : this)
			if ( e != null ) return false;
		return true;
	}
	
	boolean isFull();
	
}
