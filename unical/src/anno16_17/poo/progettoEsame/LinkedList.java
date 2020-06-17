package anno16_17.poo.progettoEsame;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<T> extends AbstractCollection<T> implements List<T>  {
	
	private static class Nodo<E>{
		E info;
		Nodo<E> prior, next;
	}//Nodo
	
	private int concurrentALCounter = 0;
	
	private Nodo<T> inizio, fine;
	private int size;	

	@Override
	public int size() {
		return size;
	}//size

//----------------------------------------------------------------------------------------------//
	
	@Override
	public void clear() {
		inizio = null; fine = null;
		size = 0;
		concurrentALCounter++;
	}//clear
	
	public void reverseRic(){
		Nodo<T> temp = inizio.next;
		inizio.next = null;
		reverse(temp);
	}
	
	private void reverse(Nodo<T> nodo){
		if (nodo==null) return;
		Nodo<T> pros = nodo.next;
		nodo.next = inizio;
		inizio = nodo;
		reverse(pros);
	}
	
	public void reverseIt(){
		Nodo<T> cur = inizio;
		Nodo<T> pre = null;
		while ( cur!=null ) {
			Nodo<T> pros = cur.next;
			cur.next = pre;
			pre = cur; inizio = cur;
			cur = pros;
		}
	}
	
	public T removeFirst(){
		if ( size==0) throw new NoSuchElementException();
		T ris = inizio.info;
		inizio = inizio.next;
		if ( size==1 ) fine = null;
		else inizio.prior = null;
		size--;
		concurrentALCounter++;
		return ris;
	}//removeFirst

	public T removeLast(){
		if ( size==0 ) throw new NoSuchElementException();
		T ris = fine.info;
		fine = fine.prior;
		if ( size==1 ) inizio = null;
		else fine.next = null;
		size--;
		concurrentALCounter++;
		return ris;
	}//removeLast
	
	public T getFirst(){
		if ( size==0 ) throw new NoSuchElementException();
		return inizio.info;
	}//getFirst
	
	public T getLast(){
		if ( size==0 ) throw new NoSuchElementException();
		return fine.info;
	}//getLast
	
	public void addFirst( T e ){
		Nodo<T> nuovo = new Nodo<T>();
		nuovo.info = e;
		if ( size==0 ){ inizio = nuovo; fine = nuovo; }
		else{
			inizio.prior = nuovo; 
			nuovo.next = inizio;
			inizio = nuovo;
		}
		size++;
		concurrentALCounter++;
	}//addFirst
	
	public void addLast( T e ){
		Nodo<T> nuovo = new Nodo<T>();
		nuovo.info = e;
		if ( size==0 ){ inizio = nuovo; fine = nuovo; }
		else{
			fine.next = nuovo;
			nuovo.prior = fine;
			fine = nuovo;
		}
		size++;
		concurrentALCounter++;
	}//addLast

	@Override
	public boolean add(T e) {
		this.addLast(e);
		return true;
	}//add

	@Override
	public Iterator<T> iterator() {
		return new myIterator();
	}//iterator
	
	private class myIterator implements Iterator<T>{
		
		private int concurrentCounter = concurrentALCounter;
		
		Nodo<T> cur = null;
		boolean rimuovibile = false;

		@Override
		public boolean hasNext() {
			if ( concurrentALCounter!=concurrentCounter )
				throw new ConcurrentModificationException();
			if ( cur==null ) return inizio != null;
			return cur.next != null;
		}//hasNext

		@Override
		public T next() {
			if ( !hasNext() ) throw new NoSuchElementException();
			if ( cur==null ) cur = inizio;
			else cur = cur.next;
			rimuovibile = true;
			return cur.info;
		}//next
		
		@Override
		public void remove(){
			if ( rimuovibile==false ) throw new IllegalStateException();
			rimuovibile = false;
			if ( cur==inizio ){
				inizio = inizio.next;
				inizio.prior = null;
				if ( size==1 ) fine = null;
				cur = null;
			}
			else if ( cur==fine ){
				fine = fine.prior;
				fine.next = null;
				cur = fine;
			}
			else{
				cur.prior.next = cur.next;
				cur.next.prior = cur.prior;
				cur = cur.prior;
			}
			size--;
			concurrentALCounter++;
			concurrentCounter++;
		}//remove
		
	}//myIterator

	@Override
	public ListIterator<T> listIterator() {
		return new myListIterator();
	}//listIterator
		
	private class myListIterator implements ListIterator<T>{
		
		private int concurrentCounter = concurrentALCounter;
		
		private Nodo<T> cur = null;
		private Spostamento direzione;
		private boolean editabile = false;
		private int indice = -1;

		@Override
		public void add(T e) {
			editabile = false;
			Nodo<T> nuovo = new Nodo<>();
			nuovo.info = e;
			if ( cur==null ){
				if ( size==0 ){
					fine = nuovo;
					inizio = nuovo;
				}
				else {
					inizio.prior = nuovo;
					nuovo.next = inizio;
					inizio = nuovo;
				}
			}
			else if ( cur==fine ){
				fine.next = nuovo;
				nuovo.prior = fine;
				fine = nuovo;
			}
			else {
				cur.next.prior = nuovo;
				nuovo.next = cur.next;
				nuovo.prior = cur;
				cur.next = nuovo;
			}
			cur = nuovo;
			indice++;
			size++;
			concurrentALCounter++;
			concurrentCounter++;
		}//add

		@Override
		public boolean hasNext() {
			if ( concurrentALCounter!=concurrentCounter )
				throw new ConcurrentModificationException();
			if ( cur==null ) return inizio != null;
			return cur.next != null;
		}//hasNext

		@Override
		public boolean hasPrevious() {
			if ( concurrentALCounter!=concurrentCounter )
				throw new ConcurrentModificationException();
			if ( cur==null ) return false;
			return cur.prior != null;
		}//hasPrevious

		@Override
		public T next() {
			if ( !hasNext() ) throw new NoSuchElementException();
			if ( cur==null ) cur = inizio;
			else cur = cur.next;
			editabile = true;
			direzione = Spostamento.NEXT;
			indice++;
			return cur.info;
		}//next

		@Override
		public int nextIndex() {
			if ( concurrentALCounter!=concurrentCounter )
				throw new ConcurrentModificationException();
			int ris = indice+1;
			return ris;
		}//nextIndex

		@Override
		public T previous() {
			T ris = cur.info;
			cur = cur.prior;
			editabile = true;
			direzione = Spostamento.PREVIOUS;
			indice--;
			return ris;
		}//previous

		@Override
		public int previousIndex() {
			if ( concurrentALCounter!=concurrentCounter )
				throw new ConcurrentModificationException();
			return indice;
		}//previousIndex

		@Override
		public void remove() {
			if ( editabile==false ) throw new IllegalStateException();
			editabile = false;
			Nodo<T> curToDelete = cur;
			if ( direzione==Spostamento.PREVIOUS) curToDelete = cur.next;
				
			if ( curToDelete==inizio ){
				inizio = inizio.next;
				inizio.prior = null;
				if ( size==1 ) fine = null;
				if ( direzione==Spostamento.NEXT) cur = null;
			}
			else if ( curToDelete==fine ){
				fine = fine.prior;
				fine.next = null;
				if ( direzione==Spostamento.NEXT) cur = fine;
			}
			else{
				curToDelete.prior.next = curToDelete.next;
				curToDelete.next.prior = curToDelete.prior;
				if ( direzione==Spostamento.NEXT) cur = cur.prior;
			}
			size--;
			concurrentALCounter++;
			concurrentCounter++;
		}//remove

		@Override
		public void set(T e) {
			if ( editabile==false ) throw new IllegalStateException();
			editabile = false;
			if ( direzione==Spostamento.PREVIOUS ) cur.next.info = e;
			else cur.info = e; 	
			concurrentALCounter++;
			concurrentCounter++;
		}//set
		
	}//myListIterator

	@Override
	public ListIterator<T> listIterator(int pos) {
		if ( pos<0 || pos>size ) throw new IndexOutOfBoundsException();
		return new myListIteratorPOS(pos);
	}//listIterator (da posizione)
	
	private class myListIteratorPOS extends myListIterator{

		private myListIteratorPOS(int pos){
			super();
			while ( super.indice<pos-1 ){ this.next(); }
		}//costruttore normale

	}//myListIteratorPOS
	
}//LinkedList 