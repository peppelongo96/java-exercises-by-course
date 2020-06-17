package anno16_17.poo.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayVector<T> extends AbstractVector<T>{
	private T[] array;
	private int size;
	public ArrayVector(){
		this(17);
	}

	@SuppressWarnings("unchecked")
	public ArrayVector( int capacita ){
		if( capacita<=0 ) throw new IllegalArgumentException();
		array=(T[]) new Object[capacita];
		size=0;
	}

	public void add( T e ){
		if( size==array.length )
			array=Arrays.copyOf( array, size*2 );
		array[size]=e;
		size++;
	}//add

	public void add( int indice, T e ){
		if( indice<0 || indice>size )
			throw new IndexOutOfBoundsException();
		if( size==array.length )
			array=Arrays.copyOf( array, size*2 );
		//fai scorrimento destro
		for( int i=size-1; i>=indice; i-- )
			array[i+1]=array[i];
	    array[indice]=e;
	    size++;
	}//add

	public T set( int indice, T e ){
		if( indice<0 || indice>=size )
			throw new IndexOutOfBoundsException();
		T x=array[indice];
		array[indice]=e;
		return x;
	}//set
	
	public T remove( int index){
		if( index<0 || index>=size )
			throw new IndexOutOfBoundsException();
		T x=array[index];
		for(int i = index+1; i<size; i++ )
			array[i-1]=array[i];
		size--;
		return x;
	}//remove

	public Vector<T> subVector( int da, int a ){
		if(da>a || da<0 || a<0 || da>=size || a>=size)
		     throw new IndexOutOfBoundsException();
		Vector<T> v= new ArrayVector(a-da);
		for( int i=da; i<a; i++ ) v.add(array[i]);
		return v;
	}//subVector

	public void trimToSize(){
		if (array.length == size) return;
		array = Arrays.copyOf(array,size);
	}

	public Iterator<T> iterator(){
		return new ArrayVectorIterator();
	}//iterator

	private class ArrayVectorIterator implements Iterator<T>{
		protected int cur=-1;
		//di momento in momento o cur==-1 o punta
		//ad un elemento già "consumato" ossia restituito da next()

		protected boolean rimuovibile=false;
		
		protected void setCur(int i){
			cur = i;
		}

		public boolean hasNext(){
			if( cur==-1 ) return size>0;
			return cur<size-1;
		}
		public T next(){
			if( !hasNext() )
				throw new NoSuchElementException();
			cur++;
			rimuovibile=true;
			return array[cur];
		}
		public void remove(){
			if( !rimuovibile )
				throw new IllegalStateException();
			rimuovibile=false;
			ArrayVector.this.remove(cur);
			if (size == array.length/2)
				array = Arrays.copyOf(array,size);
			cur--;
		}
	}//ArrayVectorIterator


	@Override
	public ListIterator<T> listIterator() {
		return new ArrayVectorListIterator();
	}

	private class ArrayVectorListIterator extends ArrayVectorIterator implements ListIterator<T> {

		@Override
		public void add(T e) {
			if( size==array.length )
				array=Arrays.copyOf( array, size*2 );
			array[size]=e;
			size++;
		}//add

		@Override
		public boolean hasPrevious() {
			if( cur==0 ) return size>0;
			return cur>0;
		}

		@Override
		public int nextIndex() {
			if( !hasNext() )
				throw new NoSuchElementException();
			return cur+1;
				
		}

		@Override
		public T previous() {
			if( !hasPrevious() )
				throw new NoSuchElementException();
			cur--;
			rimuovibile=true;
			return array[cur];
		}

		@Override
		public int previousIndex() {
			if(!hasPrevious())throw new NoSuchElementException();
			return cur-1;
		}

		@Override
		public void set(T e) {
			array[cur]=e;
		}

	}

	@Override
	public ListIterator<T> listIterator(int pos) {
		if (pos >= size) throw new NullPointerException();
		ArrayVectorListIterator avlt = new ArrayVectorListIterator();
		avlt.setCur(pos);
		return avlt;
	}

	public static void main( String[] args ){
		ArrayVector<String> vs=new ArrayVector<>(10);
		vs.add("casa"); vs.add("abaco"); vs.add("lupo");
		vs.add("arco");
		vs.add(1,"fuoco");
		System.out.println(vs);
		ListIterator<String> it= vs.listIterator(4);
		
		while( it.hasNext() ){
			String s=it.next();
			if( s.equals("abaco") ){ it.remove(); break; }
		}
		
		while( it.hasPrevious() ){
			String s=it.previous();
			if( s.equals("abaco") ){ it.remove(); break; }
		}
		System.out.println(vs);

	}
}//ArrayVector
