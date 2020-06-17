package anno16_17.poo.progettoEsame;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayList<T> extends AbstractCollection<T> implements List<T> {
	
	private int concurrentALCounter = 0;
	
	private final int capacityDef = 10;
	private int size;
	private int capacity = capacityDef;
	
	@SuppressWarnings("unchecked")
	private T[] contenuto = (T[]) new Object[capacity];
	
	@Override
	public int size(){
		return size;
	}//size
	
	private boolean resize(){
		boolean flag = false;
		if ( size > capacity ){
			capacity*=2; flag = true;
		}
		if ( size <= capacity/2 ){
			capacity/=2; flag = true;
		}
		if (flag) contenuto = Arrays.copyOf(contenuto, capacity);
		return flag;
	}//resize
	
	public ArrayList(){} 
	//costruttore di default
	
	@SuppressWarnings("unchecked")
	public ArrayList( int dim ){
		if ( dim<1 || dim>Integer.MAX_VALUE ) throw new IllegalArgumentException();
		capacity = dim;
		contenuto = (T[]) new Object[capacity];
	} //costruttore da dimensione

//---------------------------------------------------------------------------------------------//
	
	@Override
	public boolean add(T e) {
		size++; resize();
		concurrentALCounter++;
		contenuto[size-1] = e;
		return true;
	}//add

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		contenuto = (T[]) new Object[capacityDef];
		size = 0;
		concurrentALCounter++;
	}//clear
	
	@Override
	public T get(int indice) {
		if ( indice < 0 || indice >= this.size ) throw new IndexOutOfBoundsException();
		return contenuto[indice];
	}//get
	
	@Override
	public T set(int indice, T e) {
		if ( indice < 0 || indice >= this.size ) throw new IndexOutOfBoundsException();
		T ris = contenuto[indice];
		contenuto[indice] = e;
		return ris;
	}//set

	@Override
	public Iterator<T> iterator() {
		return new myIterator();
	}//iterator
	
	private class myIterator implements Iterator<T>{
		
		private int concurrentCounter = concurrentALCounter;
		
		private int cur = -1;
		private boolean rimuovibile = false;

		@Override
		public boolean hasNext() {
			if ( concurrentALCounter!=concurrentCounter )
				throw new ConcurrentModificationException();
			if ( cur==-1 ) return size>0;
			return cur < size-1;
		}//hasNext

		@Override
		public T next() {
			if ( !hasNext() ) throw new NoSuchElementException();
			cur++;
			rimuovibile = true;
			return contenuto[cur];
		}//next
		
		@Override
		public void remove(){
			if ( rimuovibile==false ) throw new IllegalStateException();
			rimuovibile = false;
			for ( int i = cur; i <= size-2; i++ ){
				contenuto[i] = contenuto[i+1];
			}
			contenuto[size-1] = null;
			cur--;
			size--; resize();
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
		
		private int cur = -1;
		private boolean editabile = false;
		private Spostamento direzione;

		@Override
		public void add(T e) {
			editabile = false;
			if ( size==0 ){
				contenuto[0] = e;
				size++; resize();
			}
			else{
				size++; resize();
				for ( int i = size-1; i>=cur+2; i-- ){
					contenuto[i] = contenuto[i-1];
				}
				contenuto[cur+1] = e;
			}
			cur++;
			concurrentALCounter++;
			concurrentCounter++;
		}//add

		@Override
		public boolean hasNext() {
			if ( concurrentALCounter!=concurrentCounter )
				throw new ConcurrentModificationException();
			if ( cur==-1 ) return size>0;
			return cur+1 < size;
		}//hasNext

		@Override
		public boolean hasPrevious() {
			if ( concurrentALCounter!=concurrentCounter )
				throw new ConcurrentModificationException();
			return cur > -1;
		}//hasPrevious

		@Override
		public T next() {
			if ( !hasNext() ) throw new NoSuchElementException();
			cur++;
			direzione = Spostamento.NEXT;
			editabile = true;
			return contenuto[cur];
		}//next

		@Override
		public int nextIndex() {
			if ( concurrentALCounter!=concurrentCounter )
				throw new ConcurrentModificationException();
			int ris = cur+1;
			return ris;
		}//nextIndex

		@Override
		public T previous() {
			if ( !hasPrevious() ) throw new NoSuchElementException();
			T ris = contenuto[cur];
			cur--;
			direzione = Spostamento.PREVIOUS;
			editabile = true;
			return ris;
		}//previous

		@Override
		public int previousIndex() {
			if ( concurrentALCounter!=concurrentCounter )
				throw new ConcurrentModificationException();
			return cur;
		}//previousIndex

		@Override
		public void remove() {
			if ( editabile==false ) throw new IllegalStateException();
			editabile = false;
			int toDelete = cur;
			if ( direzione==Spostamento.PREVIOUS ) toDelete = cur+1;
			for ( int i = toDelete; i <= size-2; i++ ){
				contenuto[i] = contenuto[i+1];
			}
			contenuto[size-1] = null;
			if ( direzione==Spostamento.NEXT ) cur--;
			size--; resize();
			concurrentALCounter++;
			concurrentCounter++;
		}//remove

		@Override
		public void set(T e) {
			if ( editabile==false ) throw new IllegalStateException();
			editabile = false;
			if ( direzione==Spostamento.PREVIOUS ) contenuto[cur+1] = e;
			else contenuto[cur] = e;
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
			super.cur = pos-1;
		}//costruttore da posizione
		
	}//myListIteratorPOS
	
}//ArrayList
	
	