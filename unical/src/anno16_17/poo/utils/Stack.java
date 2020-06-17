package anno16_17.poo.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface Stack<T> extends Iterable<T>{
	
	default int size(){
		int size = 0;
		for (T e : this) size++;
		return size;
	};
	
	void clear();
	void push(T elem);
	
	default T pop(){
		if ( this.isEmpty() ) throw new NoSuchElementException();
		Iterator<T> it = this.iterator();
		T rimosso = it.next();
		it.remove();
		return rimosso;
	}
	
	default T top(){
		Iterator<T> it = this.iterator();
		return it.next();
	}
	
	boolean isEmpty();
	boolean isFull();
}//Stack