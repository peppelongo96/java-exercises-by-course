package anno16_17.poo.progettoEsame;

import java.util.Iterator;

public interface Collection<T> extends Iterable<T>{
	
	boolean add( T e ); 
	//aggiunge elemento alla fine della collezione; ritorna true se la collezione cambia
	
	void clear();
	//pulisce la collezione
	
	int size();
	//ritorna la size di this
	
	default boolean addAll( Collection<T> c ){
	//aggiunge tutti gli elementi della collezione c a this; ritorna true se la collezione cambia
		if ( c==null ) throw new NullPointerException();
		int sizePrima = this.size();
		for ( T e : c ) this.add(e);
		return sizePrima != this.size();
	}//addAll
	
	default boolean isEmpty(){
	//verifica se la collezione vuota
		return this.size() == 0;
	}//isEmpty
	
	default boolean contains( T e ){
	//ritorna true se la collezione contiene e
		for ( T elem : this)
			if ( elem.equals(e) ) return true;
		return false;
	}//contains
	
	default boolean containsAll( Collection<T> c ){
	//ritorna true se this contiene tutti gli elementi della collezione c
		if ( c==null ) throw new NullPointerException();
		if ( this.size() < c.size() ) return false;
		Iterator<T> it = c.iterator();
		while ( it.hasNext() ){
			if ( !this.contains(it.next()) ) return false;
		}
		return true;
	}//containsAll
	
	default boolean remove( T e ){
	//rimuove l'elemento e dalla collezione; ritorna true se l'operazione va a termine
		Iterator<T> it = this.iterator();
		while ( it.hasNext() ){
			T elem = it.next();
			if ( elem.equals(e) ){
				it.remove(); 
				return true;
			}
		}
		return false;
	}//remove
	
	default boolean removeAll( Collection<T> c ){
	//rimuove gli elementi della collezione c presenti in this; ritorna true se this cambia
		if ( c==null ) throw new NullPointerException();
		int sizePrima = this.size();
		Iterator<T> it = this.iterator();
		while ( it.hasNext() ){
			T e= it.next();
			if ( c.contains(e) ){
				it.remove();
			}
		}
		return this.size() != sizePrima;
	}//removeAll
	
	default boolean retainAll( Collection<T> c ){
	//rimuove da this tutti gli elementi della collezione c non presenti in this; 
	//ritorna true se this cambia
		if ( c==null ) throw new NullPointerException();
		int sizePrima = this.size();
		Iterator<T> it = this.iterator();
		while ( it.hasNext() ){
			T e= it.next();
			if ( !c.contains(e) ){
				it.remove();
			}
		}
		return this.size() != sizePrima;
	}//retainAll
	
	default Object[] toArray(){
	//converte la collezione in array di object rispettando l'ordinamento
		Object[] ris = new Object[this.size()];
		int pos = 0; Object nuovo;
		for ( T e : this){
			nuovo = (Object)e;
			ris[pos] = nuovo;
			pos++;
		}
		return ris;
	}//toArray (generico in Object)
	
	@SuppressWarnings("unchecked")
	default <E> E[] toArray( E[] a ){
	//converte la collezione in un array che rispetta il tipo dinamico, seguendo l'ordinamento
		if ( a==null) throw new NullPointerException();
		int pos = 0; E elem;
		for ( T e : this){
			try{
				elem = (E)e;
			}catch ( ClassCastException ex){ throw new ArrayStoreException(); }
			if ( pos<a.length ){
				a[pos] = elem;
				pos++;
			}
			else break;
		}
		return a;
	}//toArray (generico in E)

}//Collection
