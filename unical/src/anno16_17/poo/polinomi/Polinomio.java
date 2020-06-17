package anno16_17.poo.polinomi;

import java.util.Iterator;


interface Polinomio extends Iterable<Monomio>{

	Polinomio crea(); //factory method

	void add( Monomio m );//somma,accoda e ordina allo stesso tempo

	default Polinomio add( Polinomio p ){
		Polinomio ris = crea();
		for(Monomio m : this) ris.add(m);
		for (Monomio m : p) ris.add(m);
		return ris;
	}

	default int size(){
		int ris = 0;
		Iterator<Monomio> it = iterator();
		while (it.hasNext()){it.next(); ris++;}
		return ris;
	}

	default Polinomio mul( Polinomio p ){
		Polinomio ris = this.crea();
		for (Monomio m : this){
			for (Monomio n : p) {
				ris.add(m.mul(n));
			}
		}
		return ris;
	}

	default Polinomio mul( Monomio m ){
		Polinomio ris  = this.crea();
		for (Monomio m1 : this)
			ris.add(m1.mul(m1));
		return ris;
	}

	default Polinomio derivata(){
		Polinomio ris  = this.crea();
		for (Monomio m : this) {
			Monomio m1 = m.derivata();
			if (m1!=null) ris.add(m1);
		}
		return ris;
	}

	default double valore( double x ){
		double ris = 0;
		for (Monomio m : this){
			ris+=m.getCoeff()*Math.pow(x, m.getGrado());
		}
		return ris;
	}

}//Polinomio
