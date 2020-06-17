package anno16_17.poo.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaConcatenataOrdinata<T extends Comparable<? super T>> extends CollezioneOrdinataAstratta<T>{
	
	private class Nodo<E>{
		T info;
		Nodo<E> next;
	}
	
	Nodo<T> inizio = null, fine = null;
	int size;
	
	@Override
	public void clear() {
		inizio = null; fine = null; size = 0;
	}
	
	@Override
	public void add(T e) {
		Nodo<T> nuovo = new Nodo<T>();
		nuovo.info = e;
		if (inizio == null){ inizio = nuovo; fine = nuovo; }
		else if (inizio.info.compareTo(e) >= 0){ 
			nuovo.next = inizio; inizio = nuovo; fine = nuovo.next;}
		else{
			Nodo<T> pre = inizio, cur = inizio.next;
			while (cur != null){
				if (cur.info.compareTo(e) >= 0){
					pre.next = nuovo; nuovo.next = cur; size++; return;
				}
				pre = cur; cur = cur.next;
			}
			pre.next = nuovo; fine = nuovo;
		}
		size++;
	}
		
	@Override
	public Iterator<T> iterator() {
		return new ListaConcIterator();
	}
	
	private class ListaConcIterator implements Iterator<T>{
		Nodo<T> cur = null, pre = null;

		@Override
		public boolean hasNext() {
			if (cur == null) return inizio != null;
			return cur.next != null;
		}

		@Override
		public T next() {
			if (!hasNext()) throw new NoSuchElementException();
			if (cur == null) cur = inizio;
			else{
				pre = cur;
				cur = cur.next;
			}
			return cur.info;
		}
		
		public void remove(){
			if (pre == cur) throw new IllegalStateException();
			if (cur == inizio){ 
				inizio = inizio.next; 
				if (inizio == null) fine = null; 
			}
			else if (cur == fine){ fine = pre; pre.next = null; }
			else{
				pre.next = cur.next;
			}
			cur = pre; size--;
		}
	}
	
	
}
