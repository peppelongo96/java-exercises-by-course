package anno16_17.poo.sistema;
import anno16_17.poo.utils.Matrici;
import java.util.*;

public class Cramer extends SistemaQuadrato{
	private double [][] a;

	public Cramer(double[][] a, double[] y){
		super(a,y);
		this.a = new double[a.length][a.length+1];
		for (int i = 0; i < a.length; i++) {
			System.arraycopy(a[i],0,this.a[i],0,a.length);
			this.a[i][a.length] = y[i];
		}
	}
	
	private void matriceAi(int numColonna) throws IllegalArgumentException{
		double temp;
		for (int i = 0; i < a.length; i++) {
			temp = a[i][numColonna];
			a[i][numColonna]= a[i][a.length-1];
			a[i][a.length-1]= temp;
		}
	}
	
	@Override
	public double[] risolvi(){
		double[] coeffAlg = new double [getN()];
		for (int col = 0; col < getN(); col++) {
			matriceAi(col);
			coeffAlg[col] = Matrici.determinante(Matrici.eliminaNoti(a));
			matriceAi(col);
		}
		return calcolaSoluzione(coeffAlg);
	}

	public double[] calcolaSoluzione(double[] v){
		double detA = Matrici.determinante(Matrici.eliminaNoti(a));
		if (detA == 0) throw new RuntimeException("Sistema singolare");
		for (int i = 0; i < v.length; i++) {
			v[i]=v[i]/detA;
		}
		return v;
	}
	
	public String tostring(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < getN(); i++){
			for (int j = 0; j <= getN(); j++) 
				sb.append(String.format("%7.2 ",a[i][j]));
			sb.append('\n');
		}
		return sb.toString();
	}
}
