package anno16_17.poo.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackArray<T> extends StackAstratto<T> {
	int capacityDef = 50;
	T[] contenuto = (T[]) new Object[capacityDef];
	private int size;

	@Override
	public void clear() {
		contenuto = (T[]) new Object[capacityDef];
		size = 0;
	}
	
	@Override
	public int size(){
		return size;
	}

	@Override
	public void push(T elem) {
		if (size == contenuto.length) contenuto = Arrays.copyOf(contenuto, contenuto.length*2);
		contenuto[size] = elem; 
		size++;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return new StackIterator();
	}
	
	private class StackIterator implements Iterator<T>{
		int cur = -1; boolean rimuovibile = false;

		@Override
		public boolean hasNext() {
			if (cur == -1) return contenuto!= null;
			return cur < size-1;
		}

		@Override
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			cur++; rimuovibile = true;
			return contenuto[cur];
		}
		
		public void remove(){
			if (rimuovibile == false) throw new IllegalStateException();
			rimuovibile = false;
			for (int i = cur+1; i < size; i++) {
				contenuto[i-1] = contenuto[i];
			}
			contenuto[size]=null;
			if (size < contenuto.length/2) 
				contenuto = Arrays.copyOf(contenuto, contenuto.length/2);
			size--;
		}
	}

}
