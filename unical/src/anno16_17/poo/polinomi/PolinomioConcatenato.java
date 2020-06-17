package anno16_17.poo.polinomi;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PolinomioConcatenato extends PolinomioAstratto{
	private static class Nodo{
		Monomio info;
		Nodo next;
	}
	private int size=0;
	private Nodo testa=null;
	
	public PolinomioConcatenato crea(){ return new PolinomioConcatenato(); }
	
	public PolinomioConcatenato(String s){
		PolinomioConcatenato p = (PolinomioConcatenato)creaDaString(s);
		for (Monomio m : p)
			this.add(m);
	}
	
	public PolinomioConcatenato() {}

	public int size(){ return size; }
	
	public void add( Monomio m ){
		if( m.getCoeff()==0 ) return;
		Nodo pre=null, cor=testa;
		while( cor!=null && cor.info.compareTo(m)<0 ){
			pre=cor;
			cor=cor.next;
		}
		if( cor!=null && cor.info.equals(m) ){
			Monomio ms=cor.info.add(m);
			if( ms.getCoeff()!=0 ){//update
				cor.info=ms;
			}
			else{//rimuovi nodo cor
				if( cor==testa ){
					testa=testa.next;
				}
				else{
					pre.next=cor.next;
				}
				size--;
			}
		}
		else{ //va aggiunto nuovo nodo
			Nodo nuovo=new Nodo();
			nuovo.info=m; nuovo.next=cor;
			if( cor==testa ){//inserimento in testa o in lista vuota
				testa=nuovo;
			}
			else{//inserimento in posizione intermedia o alla fine della lista
				pre.next=nuovo;
			}
			size++;
		}
	}//add
	
	public Iterator<Monomio> iterator(){
		return new IteratoreLista();
	}//iterator
	
	private class IteratoreLista implements Iterator<Monomio>{
		private Nodo pre=null, cor=null;
		public boolean hasNext(){
			if( cor==null ) return testa!=null;
			return cor.next!=null;
		}//hasNext
		public Monomio next(){
			if( !hasNext() ) throw new NoSuchElementException();
			if( cor==null ) cor=testa;
			else{
				pre=cor;
				cor=cor.next;
			}
			return cor.info;
		}//next
		public void remove(){
			if( pre==cor ) throw new IllegalStateException();
			if( cor==testa ) testa=testa.next;
			else pre.next=cor.next;
			size--;
			cor=pre;
		}//remove
	}
	
}//PolinomioConcatenato
