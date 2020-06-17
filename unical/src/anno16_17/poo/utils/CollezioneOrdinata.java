package anno16_17.poo.utils;

import java.util.Iterator;

public interface CollezioneOrdinata<T extends Comparable<? super T>> extends Iterable<T>{
	
	default int size(){
		int c=0;
		for( Iterator<T> i=iterator(); i.hasNext(); i.next(),c++ );
		return c;
	}//size
	
	default boolean contains( T e ){
		Iterator<T> it = this.iterator();
		while( it.hasNext() ){
			if ( it.next().equals(e))
				return true;
		}
		return false;
	}
	
	default void clear(){
		for (T e : this)
			e = null;
	}
	
	void add( T e );
	
	default void remove( T e ){
		Iterator<T> it = this.iterator();
		while ( it.hasNext() ){
			if (it.next().equals(e)){
				it.remove();
				return;
			}
		}
	}
	
}//CollezioneOrdinata
