package anno16_17.poo.polinomi;

//import java.util.ArrayList;
import java.util.Iterator;

import anno16_17.poo.progettoEsame.ArrayList;

public class PolinomioAL extends PolinomioAstratto {
	
	private ArrayList<Monomio> contenuto = new ArrayList<>();
	
	public PolinomioAL(){}
	
	public PolinomioAL(Polinomio p){
		for (Monomio m : p)
			contenuto.add(m);
	}
	
	public PolinomioAL(String p){
		contenuto = ((PolinomioAL)creaDaString(p)).contenuto;
	}

	@Override
	public Polinomio crea() {
		return new PolinomioAL();
	}

	@Override
	public void add(Monomio m) {
		for (int  i = 0; i< contenuto.size(); i++){
			Monomio tmp = contenuto.get(i);
			if (tmp.equals(m)){
				int somma = m.getCoeff()+tmp.getCoeff();
				if (somma != 0) contenuto.set(i, new Monomio(somma,m.getGrado())); 
				if (somma == 0) contenuto.remove(i);
				return;
			}
			if (tmp.getGrado() < m.getGrado()){
				contenuto.add(i, m);
				return;
			}
		}
		if (m.getCoeff()!= 0) contenuto.add(m);
	}

	@Override
	public Iterator<Monomio> iterator() {
		return contenuto.iterator();
	}

}
