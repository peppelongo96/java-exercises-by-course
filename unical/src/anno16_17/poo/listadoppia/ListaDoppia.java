package anno16_17.poo.listadoppia;
import java.util.*;

public class ListaDoppia<T extends Comparable<? super T>> implements Iterable<T> {
	
	private static class Nodo<E>{
		E info;
		Nodo<E> next, prior;
	}
	
	private Nodo<T> testa=null;
	
	public void add( T elem ){
		Nodo<T> nuovo=new Nodo<T>();
		nuovo.info=elem;
		if( testa==null || testa.info.compareTo(elem)>=0 ){
			nuovo.next=testa; nuovo.prior=null;
			if( testa!=null ) testa.prior=nuovo;
			testa=nuovo;
		}
		else{

			Nodo<T> cor=testa.next, pre=testa;
			while( cor!=null && cor.info.compareTo(elem)<0 ){
				pre=cor; cor=cor.next;
			}
			
			nuovo.next=cor;
			if( cor!=null ) cor.prior=nuovo;
			nuovo.prior=pre;
			pre.next=nuovo;
			
		}
	}//add
	
	public void remove( T elem ){
		Nodo<T> cor=testa;
		
		while( cor!=null && cor.info.compareTo(elem)<0 ) cor=cor.next;
		
		if( cor!=null && cor.info.equals(elem) ){
			if( cor==testa ){
				testa=testa.next;
				if( testa!=null ) testa.prior=null;
			}
			else{
				//esiste certamente il precedente di cor
				cor.prior.next=cor.next;
				if( cor.next!=null )
					cor.next.prior=cor.prior;
			}
		}
		
	}//remove
	
	public String toString(){
		StringBuilder sb=new StringBuilder(100);
		Nodo<T> cor=testa;
		sb.append('[');
		while( cor!=null ){
			sb.append( cor.info );
			if( cor.next!=null ) sb.append(", ");
			cor=cor.next;
		}
		sb.append(']');
		return sb.toString();
	}//toString
	
	public Iterator<T> iterator(){
		return new Iteratore();
	}//iterator
	
	private class Iteratore implements Iterator<T>{
		//cor o e' null o punta ad un nodo GIA' visitato
		private Nodo<T> cor=null;
		private boolean rimuovibile=false;
		
		public boolean hasNext(){
			if( cor==null ) return testa!=null;
			return cor.next!=null;
		}//hasNext
		
		public T next(){
			if( !hasNext() ) 
				throw new NoSuchElementException();
			rimuovibile=true;
			if( cor==null ) cor=testa;
			else cor=cor.next;
			return cor.info;
		}//next
		
		public void remove(){
			if( !rimuovibile ) 
				throw new IllegalStateException();
			rimuovibile=false;
			if( cor==testa ){
				testa=testa.next;
				if( testa!=null ) testa.prior=null;
			}
			else{
				cor.prior.next=cor.next;
				if( cor.next!=null )
					cor.next.prior=cor.prior;
			}
			cor=cor.prior; //arretra cor
		}//remove
		
	}//Iteratore
	
	public static void main( String[] args ){
		ListaDoppia<String> ld=new ListaDoppia<>();
		
		for( String s: args ) ld.add(s);
		
		assert ld!=null : "Lista vuota!";
		
		System.out.println(ld);
		
		ld.remove("zaino");
		System.out.println(ld);
		for( String x: ld ) System.out.print(x+" ");
		System.out.println();
		Iterator<String> it=ld.iterator();
		while( it.hasNext() ){
			it.next();
			it.remove();
		}
		System.out.println(ld);
	}//main
}//ListaDoppia
