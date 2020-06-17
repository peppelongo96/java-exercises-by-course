package anno16_17.poo.utils;
public final class Matrici{
	private Matrici(){}

	public static double[][] add (double [][]a, double[][]b){
		if (a.length != b.length) throw new IllegalArgumentException();
		for ( int i = 0; i < a.length; i++)
			if (a[i].length != b[i].length) throw new IllegalArgumentException();
		double [][] c = new double [a.length][];
		for (int i = 0; i < a.length; i++){
			c[i] = new double [a[i].length];
		}

		for ( int i = 0; i < c.length; i++){
			for (int j = 0; j< c[i].length; j++){
				c[i][j] = a[i][j] + b[i][j];
			}
		}

		return c;
	}

	public static double[][] mul (double [][]a, double[][]b){
		if (a[0].length != b[0].length) throw new IllegalArgumentException();
		double [][] ris = new double [a.length][b[0].length];
		
		for ( int i = 0; i < ris.length; i++){
			for ( int j = 0; i < ris[0].length; i++){
				ris[i][j] = 0;
				for (int k = 0; k < a[0].length; k++)
					ris[i][j]= ris[i][j]+a[i][k]*b[k][j];
			}
		}
		return ris;
	}

	public static boolean simmetrica(double[][] a){
		if (a.length != a[0].length) throw new IllegalArgumentException();
		boolean flag = true;
		for (int i = 0; i < a.length; i++){
			for (int j = 0; j < a[0].length; j++){
				if (a[i][j] != a[j][i]){
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	public static double[][] orlata (double[][]a,int i, int j){
		if (i < 0 || j < 0) throw new IllegalArgumentException();
		double[][] ris = new double[a.length-1][a[0].length-1];
		int i3 = -1; int j3 = -1;
		for (int i2 = 0; i2 < a.length; i2++){
			if(i2 != i){
				i3++;
				for (int j2 = 0; j2 < a[0].length; j2++){
					if (j2 != j){
						j3++;
						if (j3%ris[0].length == 0)
							j3=0;
						ris[i3][j3]=a[i2][j2];
					}
				}
			}
		}
		return ris;
	}

	public static double [][] trasposta(double[][] a){
		if (a.length != a[0].length) throw new IllegalArgumentException();
		double[][] ris = new double[a.length][a[0].length];
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a[0].length; j++)
				ris[j][i] = a[i][j];
		return ris;
	}

	public static boolean identity (double[][]a){
		for (int i = 0; i < a.length; i++){
			for (int j = 0; j < a[0].length; j++){
				if (i == j && a[i][j] != 1) return false;
				else if (a[i][j] != 0) return false;
			}
		}
		return true;
	}
	
	public static double[][] copiaMatrice(double[][]a){
		double[][] ris = new double[a.length][a[0].length];
		for (int i = 0; i < ris.length; i++) {
			for (int j = 0; j < ris[0].length; j++) {
				ris[i][j] = a[i][j];
			}
		}
		return ris;
	}
	
	private static int triangonalizza(double[][]a){
		int ris = 0;
		int n = a.length;
		for (int j = 0; j < n; j++) {
			for (int i = j+1; i < n; i++) {
				if (Mat.sufficientementeProssimo(a[i][j], 0)){
					int p = j+1;
					for (; p< n; p++)
						if (!(Mat.sufficientementeProssimo(a[p][j], 0))) break;
					if (p==n) throw new RuntimeException("Sistema singolare");
					double[] tmp = a[j]; a[j] = a[p]; a[p]=tmp; ris++;
				}
				if (!(Mat.sufficientementeProssimo(a[i][j], 0))){
					double coeff = a[i][j]/a[j][j];
					for (int k = j; k <= n; k++) 
						a[i][k] = a[i][k] - a[j][k]*coeff;
				}
			}

		}
		return ris;
	}
	
	public static double determinante(double [][]a) throws IllegalArgumentException{
		if (a.length != a[0].length)
			throw new IllegalArgumentException("Matrice non quadrata");
		double ris = 1;
		double[][] m = copiaMatrice(a);
		int segno = triangonalizza(m);
		for (int i = 0; i < m.length; i++)
			ris*=m[i][i];
		if (segno%2 == 0)
			ris = Math.abs(ris);
		else
			ris = Math.abs(ris)*-1;
		return ris;
	}
	
	public static double[][] eliminaNoti(double[][] a){
		double[][] ris = new double[a.length][a.length-1];
		for (int i = 0; i < ris.length; i++) {
			ris[i][a.length-1] = a[i][a.length-1];
		}
		return ris;
	}
	
	private static double[][] matriceCompAlg(double[][] m){
		double[][] ris = new double[m.length][m.length];
		for (int i = 0; i < ris.length; i++) {
			for (int j = 0; j < ris.length; j++) {
				ris[i][j]=Math.pow(-1, i+j)*determinante(orlata(m,i,j));
			}
		}
		return ris;
	}
	public static double[][] matriceInversa(double[][] a){
		double detA = determinante(a);
		if (a.length != a[0].length || detA == 0) throw new IllegalArgumentException();
		double[][]ris = trasposta(matriceCompAlg(a));
		for (int i = 0; i < ris.length; i++) {
			for (int j = 0; j < ris.length; j++) {
				ris[i][j] = ris[i][j]/detA;
			}
		}
		return ris;
	}
}
	
	





