package anno16_17.poo.polinomi;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class PolinomioSet extends PolinomioAstratto{
	
	private Set<Monomio> contenuto = new TreeSet<>();
	
	public PolinomioSet(){}
	
	public PolinomioSet( Polinomio p ){
		for( Monomio m: p ) this.add(m);
	}
	
	public PolinomioSet(String p){
		contenuto = ((PolinomioSet)creaDaString(p)).contenuto;
	}
	
	public PolinomioSet crea(){ return new PolinomioSet(); }
	
	public Iterator<Monomio> iterator(){ return contenuto.iterator(); }
	
	public int size(){ return contenuto.size(); }
	
	public void add( Monomio m ){
		if( m.getCoeff()==0 ) return;
		Iterator<Monomio> it=contenuto.iterator();
		boolean flag=false;
		while( it.hasNext() && !flag ){
			Monomio x=it.next();
			if( x.compareTo(m)>0 ){
				contenuto.add(m); flag=true;
			}
			else if( x.equals(m) ){
				Monomio r=m.add(x);
				it.remove();
				if( r.getCoeff()==0 ){ flag=true; }
				else { contenuto.add(r); flag=true; }
			}
		}
		if( !flag ) contenuto.add(m);
	}//add
}
