package anno16_17.poo.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CodaConcatenata<T> extends CodaAstratta<T>  {
	
	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}
	
	Nodo<T> testa;
	Nodo<T> corrente;
	int size;

	@Override
	public void clear() {
		testa = null; size = 0;
	}

	@Override
	public void put(T elem) {
		Nodo<T> nuovo = new Nodo();
		nuovo.info = elem;
		if ( testa == null){ testa = nuovo; corrente = nuovo; }
		else{
			corrente.next = nuovo;
			corrente = corrente.next;
		}
		size++;
	}

	@Override
	public boolean isEmpty() {
		return size==0 && testa == null;
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return new CodaIterator();
	}
	
	private class CodaIterator implements Iterator<T>{
		Nodo<T> cur = null, pre = null;

		@Override
		public boolean hasNext() {
			if (cur == null) return testa!=null;
			return cur.next != null;
		}

		@Override
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			if (cur == null) cur = testa;
			else{ 
				pre = cur; cur = cur.next;
			}
			return cur.info;
		}
		
		public void remove(){
			if (pre == cur) throw new IllegalStateException();
			if (cur == testa) testa = testa.next;
			else{ pre.next = cur.next; cur = pre; }
			size--;
		}
		
	}

}
