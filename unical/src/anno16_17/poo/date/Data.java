package anno16_17.poo.date;

import java.util.*;


final class Data implements Comparable<Data>{
	private final int G,M,A;
	public static final int GIORNO = 0;
	public static final int MESE = 1;
	public static final int ANNO = 2;
	// o mglio
	public enum Tipo {GIORNO,MESE,ANNO};

	public Data(){
		GregorianCalendar gc = new GregorianCalendar();
		G = gc.get(GregorianCalendar.DAY_OF_MONTH);
		M = gc.get(GregorianCalendar.MONTH)+1;
		A = gc.get(GregorianCalendar.YEAR);
	}//costrutore di default

	public Data (int g, int m, int a){
		int d = durata(m,a);
		if (g < 1 || g > d || m < 1 || m > 12 || a < 0)
			throw new IllegalArgumentException();
		G = g; M = m; A = a;
	}//1 costruttore

	public Data (Data d){
		G = d.G; M = d.M; A = d.A;
	}//costruttore per copia

	public static int durata(int m,int a){
		if (m<=1 || m > 12 || a < 0) throw new IllegalArgumentException();
		int d = 0;
		switch (m){
			case 1: case 3: case 7: case 8: case 10: case 12: d = 31;
			break;
			case 2: d = bisestile(a)? 29:28;
			break;
			default: d = 30;
		}
		return d;
	}// durata

	public static boolean bisestile(int a){
		if (a < 0) throw new IllegalArgumentException();
		if (a % 4 != 0)
			return false;
		if (a % 100 == 0 && a % 400 != 0)
			return false;
		return true;
	}// vedi se bisestile

	public int get(int cosa){
		switch (cosa){
			case GIORNO : return G;
			case MESE: return M;
			case ANNO: return A;
			default: return -1;
		}
	}// get generale

	//O MEGLIO

	public int get(Tipo cosa){
		switch(cosa){
			case GIORNO: return G;
			case MESE: return M;
			default: return A;
		}
	}

	public Data next(){
		int g = G; int m = M; int a = A;
		if (g == durata(m,a)){
			if (m == 12){
				g = 1;
				m = 1;
				a++;
			}
			else{
				g = 1;
				m++;
			}
		}
		else g++;
		return new Data(g,m,a);
	}

	public Data before(){
		int g = G; int m = M; int a = A;
		if (G == 0 && M == 0 && A == 0)
			throw new IllegalArgumentException();
		else if (G == 1 && M == 1){
			g = 31;
			m = 12;
			a--;
		}
		else if (G == 1){
			g = durata(M-1,A);
			m--;
		}
		else
			g--;
		return new Data(g,m,a);
	}// data prima

	public int distanza(Data d){
		Data d1 = new Data(this);
		int cont = 0;
		if (confronto(d,this) == 1){
			while(d1.G != d.G && d1.M != d.M && d1.A != d.A){
				d1.next();
				cont++;
			}
		}
		else if (confronto(d,this) == -1){
			while(d1.G != d.G && d1.M != d.M && d1.A != d.A){
				d1.before();
				cont++;
			}
		}
		else
			throw new IllegalArgumentException();
		return cont;
	}

	public int distanza2(Data d){
		Data d1 = this;
		Data d2 = d;
		if (this.compareTo(d) >= 0) d1 = d; d2 = this;
		int c = 0;
		while (d1.compareTo(d2) < 0){
			c++; d1 = d1.next();
		}
		return c;
	}

	private static int confronto(Data d1, Data d2){
		if (d1.A > d2.A)
			return 1;
		else if (d1.A < d2.A)
			return -1;
		else{
			if (d1.M > d2.M)
				return 1;
			else if (d1.M < d2.M)
				return -1;
			else {
				if (d1.G > d2.G)
					return 1;
				else if (d1.G < d2.G)
					return -1;
				else return 0;
			}
		}
	}

	public String toString(){
		if (this.G < 9 && this.M < 9)
			return " 0"+this.G+"/"+"0"+this.M+"/"+this.A;
		else if (this.G < 9)
			return " 0"+this.G+"/"+this.M+"/"+this.A;
		else if (this.M < 9)
			return " "+this.G+"/"+"0"+this.M+"/"+this.A;
		else
			return " "+this.G+"/"+this.M+"/"+this.A;
	}

	@Override
	public boolean equals(Object x){
		if (!(x instanceof Data)){
			return false;
		}
		if (x == this) return true;
		Data d = (Data)x;
		return this.G == d.G && this.M == d.M && this.A == d.A;
	}

	@Override
	public int hashCode(){
		final int MUL = 83;
		int h = G;
		h = h * MUL + M; h = h * MUL + A; return h;
	}

	public int compareTo(Data d){
		if (A < d.A || A == d.A && M < d.M || A == d.A && M == d.M && G < d.G) return -1;
		if (this.equals(d))
			return 0;
		return 1;
	}



	public static void main( String[] args ){
		Data d = new Data();
		System.out.print(d.next());
		System.out.print(d.before());
		Data d3 = new Data(12,11,2016);
		System.out.print(d.distanza(d3));
	}
}
