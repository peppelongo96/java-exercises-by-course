package anno16_17.poo.polinomi;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class PolinomioMap extends PolinomioAstratto{
	
	private TreeMap<Integer,Integer> contenuto = new TreeMap<>();
	
	public PolinomioMap(){}
	
	public PolinomioMap(Polinomio p){
		for (Monomio m : p){
			contenuto.put(-m.getGrado(), m.getCoeff());
		}
	}
	
	public PolinomioMap(String p){
		contenuto = ((PolinomioMap) creaDaString(p)).contenuto;
	}
	
	@Override
	public Polinomio crea() {
		return new PolinomioMap();
	}

	@Override
	public void add(Monomio m) {
		if (contenuto.containsKey(-m.getGrado())){
			int somma = contenuto.get(-m.getGrado())+m.getCoeff();
			if (somma != 0)	contenuto.put(-m.getGrado(), somma); 
			if (somma == 0) contenuto.remove(-m.getGrado());
			return;
		}
		if (m.getCoeff() != 0) contenuto.put(-m.getGrado(), m.getCoeff());
	}

	@Override
	public Iterator<Monomio> iterator() {
		return new PolinomioMapIterator();
	}
	
	private class PolinomioMapIterator implements Iterator<Monomio>{
		
		int cur = -1;
		Set<Integer> s = contenuto.keySet();
		Iterator<Integer> it = s.iterator();

		@Override
		public boolean hasNext(){
			if( cur==-1 ) return contenuto.size()>0;
			return cur<contenuto.size()-1;
		}

		@Override
		public Monomio next() {
			cur++;
			int grado = it.next();
			int coeff = contenuto.get(grado);
			return new Monomio(coeff,-grado);
		}
	}
}
