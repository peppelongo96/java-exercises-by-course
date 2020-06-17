package anno16_17.poo.polinomi;

import java.util.Iterator;
import java.util.StringTokenizer;

public abstract class PolinomioAstratto implements Polinomio {
	
	private final String REGEX = "[\\+\\-]?(\\d+|([xX]|\\d+[xX])(\\^(0|\\d+))?)"
			+ "([\\+\\-](\\d+|([xX]|\\d+[xX])(\\^(0|\\d+))?))*";
	
	protected Polinomio creaDaString(String p){
		Polinomio ris = null;
		if (p.matches(REGEX)){
			ris = crea();
			if (p.charAt(0)!='+' && p.charAt(0)!='-') p = "+"+p;
			StringTokenizer st1 = new StringTokenizer(p,"+-",true);
			StringTokenizer st2;
			while (st1.hasMoreTokens()){
				String op = st1.nextToken();
				String monomio = st1.nextToken();
				st2 = new StringTokenizer(monomio,"xX^",true);
				String elem= st2.nextToken();
				if (elem.toLowerCase().equals("x")){
					if (!st2.hasMoreTokens()){
						ris.add(new Monomio(1,1)); 
						continue;
					}
					st2.nextToken();
					ris.add(new Monomio(Integer.parseInt(op+"1"),Integer.parseInt(st2.nextToken())));
					continue;
				}
				int coeff = Integer.parseInt(op+elem);
				if (!st2.hasMoreTokens()){
					ris.add(new Monomio(coeff,0)); continue;//costante
				}
				st2.nextToken();
				if (!st2.hasMoreTokens()){
					ris.add(new Monomio(coeff,1));//x
					continue;
				}
				st2.nextToken();
				ris.add(new Monomio(coeff, Integer.parseInt(st2.nextToken())));//x elevato
			}
		}
		if (ris == null) throw new IllegalArgumentException();
		return ris;
	}

	public String toString(){
		StringBuilder sb=new StringBuilder(200);
		boolean first=true;
		for( Monomio m: this ){
			if( !first && m.getCoeff()>0 ) sb.append('+');
			sb.append(m);
			if( first ) first=false;
		}
		return sb.toString();
	}//toString
	
	public boolean equals (Object o ){
		if (!(o instanceof Polinomio)) return false;
		if (o == this) return true;
		Polinomio p = (Polinomio)o;
		Iterator<Monomio> it1 = iterator(), it2 = p.iterator();
		while (it1.hasNext()){
			Monomio m1 = it1.next(), m2 = it2.next();
			if (!(m1.getCoeff() == m2.getCoeff() && m1.getGrado() == m2.getGrado())) return false;
		}
		return true;
	}
	
	public int hashCode(){
		final int M=43;
		int h=0;
		for( Monomio m: this ){
			h=h*M+( String.valueOf(m.getCoeff())+String.valueOf(m.getGrado() )).hashCode();
		}
		return h;
	}//hashCode
	
}//PolinomioAstratto
