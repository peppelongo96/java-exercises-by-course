package anno16_17.poo.polinomi;

import java.util.Iterator;
//import java.util.LinkedList;
import java.util.ListIterator;

import anno16_17.poo.progettoEsame.LinkedList;

public class PolinomioLL extends PolinomioAstratto{
	private LinkedList<Monomio> contenuto=new LinkedList<>();
	
	public PolinomioLL(){};
	
	public PolinomioLL( Polinomio p ){
		for( Monomio m: p ) this.add(m);
	}
	
	public PolinomioLL(String p){
		contenuto = ((PolinomioLL) creaDaString(p)).contenuto;
	}
	
	public PolinomioLL crea(){ return new PolinomioLL(); }
	
	public Iterator<Monomio> iterator(){ return contenuto.iterator(); }
	
	public int size(){ return contenuto.size(); }
	
	public void add( Monomio m ){
		if( m.getCoeff()==0 ) return;
		ListIterator<Monomio> lit=contenuto.listIterator();
		boolean flag=false;
		while( lit.hasNext() && !flag ){
			Monomio x=lit.next();
			if( x.equals(m) ){
				Monomio r=x.add(m);
				if( r.getCoeff()==0 ){ lit.remove(); flag=true; }
				else{ lit.set(r); flag=true; }
			}
			else if( x.compareTo(m)>0 ){
				lit.previous();
				lit.add(m);
				flag=true;
			}
		}
		if( !flag ){ lit.add(m); }
	}//add

}//PolinomioLL
