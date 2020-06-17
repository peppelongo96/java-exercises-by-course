package anno16_17.poo.progettoEsame.CollectionTestGUI;

import java.util.Comparator;
import java.util.Random;

class EsempiComp {
	
	static class crescenteComp<T extends Comparable<? super T>> implements Comparator<T> {
		@Override
		public int compare(T arg0, T arg1) {
			return arg0.compareTo(arg1);
		}
	}//ordineCrescente
	
	static class decrescenteComp<T extends Comparable<? super T>> implements Comparator<T> {
		@Override
		public int compare(T arg0, T arg1) {
			return arg1.compareTo(arg0);
		}
	}//ordineDecrescente

	static class shuffleComp implements Comparator<Integer> {
		Random r = new Random();
		@Override
		public int compare(Integer o1, Integer o2) {
			return r.nextInt();
		}
	}//shuffleComp
	
	static class lenStringComp implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			if ( o1.length() > o2.length() ) return -1;
			else if ( o1.length() < o2.length() ) return 1;
			return 0;
		}
	}//lenStringComp

}//EsempiComp
