package anno16_17.poo.utils;

public final class Mat{
	private Mat(){};
	public static double EPS = 1.0E-10; //=10^(-10)

	public static boolean sufficientementeProssimo(double x, double y){
		return Math.abs(x-y) <= EPS;
	}

	public static int mcd (int x, int y){
		if (x <= 0 || y <= 0) throw new IllegalArgumentException();
		return mcd_euclide(x,y);
	}

	private static int mcd_euclide(int x, int y){
		if (y <= 0) return x;
		return mcd_euclide(y,x%y);
	}

	public static int mcm (int x, int y){
		if (x <= 0 | y <= 0) throw new IllegalArgumentException();
		return (x%y)/mcd_euclide(x,y);
	}

	public static boolean ePrimo( int x ){
		if (x <= 1) throw new IllegalArgumentException();
		int cont = 2;
		while (cont < x)
			if (x%cont == 0) return false;
		return true;
	}

	public static int sommaDivisori(int x){
		if (x < 0) throw new IllegalArgumentException();
		int ris = 0;
		int cont = 1;
		while (cont <= x/2){
			if (x%cont == 0)
				ris+=cont;
		}
		return ris;
	}

	public static boolean amicabili( int x, int y ){
		if (x <= 0 || y <= 0) throw new IllegalArgumentException();
		int divx = sommaDivisori(x);
		int divy = sommaDivisori(y);
		if (divx!= y && divy != x) return false;
		return true;
	}

	public static boolean perfetto( int x ){
		if (!(x >0)) throw new IllegalArgumentException();
		if ( x == sommaDivisori(x)) return true;
		return false;
	}

}//mat