package anno16_17.poo.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackConcatenato<T> extends StackAstratto<T>{
	
	private class Nodo<E>{
		E info;
		Nodo<E> next;
	}
	
	Nodo<T> testa;
	int size;
	
	@Override
	public void clear() {
		size = 0; testa = null;
	}

	@Override
	public void push(T elem) {
		Nodo<T> nuovo = new Nodo<>();
		nuovo.info = elem;
		if (testa != null) nuovo.next = testa;
		testa = nuovo; 
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
		Nodo<T> cur = null, pre = null; 
		
		@Override
		public boolean hasNext() {
			if ( cur == null) return testa != null;
			return cur.next != null;
		}

		@Override
		public T next() {
			if ( !hasNext() ) throw new NoSuchElementException();
			if (cur == null){ cur = testa; return testa.info; }
			pre = cur; cur = cur.next;
			return cur.info;
			
		}
		
		public void remove(){
			if (pre == cur) throw new IllegalStateException();
			if ( cur == testa){ testa = testa.next; size--; return; }
			else{
				pre.next = cur.next;
				cur = pre;
			}
			size--;
		}
	}

}
