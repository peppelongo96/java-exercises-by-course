package anno16_17.poo.sistema;
import java.util.Arrays;
import java.util.Scanner;

import anno16_17.poo.utils.*;

public class Gauss extends SistemaQuadrato{
	private double [][] a;

	public Gauss(double [][] a, double[] y){
		super(a,y);
		this.a = new double[a.length][a.length+1];
		for (int i = 0; i < a.length; i++) {
			System.arraycopy(a[i],0,this.a[i],0,a.length);
			this.a[i][a.length] = y[i];
		}
	}

	public double[][] getMatrice(){ return a;}

	public double[] risolvi(){
		triangonalizza();
		return calcolaSoluzione();
	}

	private void triangonalizza(){
		int n = getN();
		for (int j = 0; j < n; j++) {
			for (int i = j+1; i < n; i++) {
				if (Mat.sufficientementeProssimo(a[i][j], 0)){
					int p = j+1;
					for (; p< n; p++)
						if (!(Mat.sufficientementeProssimo(a[p][j], 0))) break;
					if (p==n) throw new RuntimeException("Sistema singolare");
					double[] tmp = a[j]; a[j] = a[p]; a[p]=tmp;
				}
				if (!(Mat.sufficientementeProssimo(a[i][j], 0))){
					double coeff = a[i][j]/a[j][j];
					for (int k = j; k <= n; k++)
						a[i][k] = a[i][k] - a[j][k]*coeff;
				}
			}

		}
	}

	private double [] calcolaSoluzione(){
		int n = getN();
		double [] x = new double [n];
		for (int i = n-1; i >=0; i--) {
			double secondoMembro = a[i][n];
			for (int j = i+1; j < n; j++)
				secondoMembro = secondoMembro - a[i][j]*x[j];
			x[i] = secondoMembro / a[i][i];
		}
		return x;
	}

	public static Gauss catturaMatrice(){
		Scanner input = new Scanner(System.in);
		System.out.print("Inserisci dimensione sistema (n° incognite): "); int dim = input.nextInt(); input.nextLine();
		double[][] mCoeff = new double [dim][dim];
		double[] tNoti = new double[dim];
		System.out.println("INSERISCI SISTEMA");
		for (int i = 0; i < dim; i++) {
			System.out.print("Equazione "+(i+1)+"--> "); String s = input.nextLine();
			double[] rigaCoeff = ricavaCoeff(s,dim);
			double noto = ricavaNoto(s,dim);
			System.arraycopy(rigaCoeff, 0, mCoeff[i], 0, mCoeff.length);
			tNoti[i] = noto;
		}
		input.close();
		return new Gauss(mCoeff,tNoti);
	}

	private static double[] ricavaCoeff(String s,int dim){
		double[] ris = new double[dim];
		int pos = dim;
		String sCoeff ="";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '=')
				sCoeff = s.substring(0,i);
		}
		for (int i = sCoeff.length()-1; i >= 0; i--) {
			if ((int)sCoeff.charAt(i) >= 97 && (int)sCoeff.charAt(i) <= 122){
				if (i==0) ris[pos-1] = Double.parseDouble("+1");
				else{
					int j = i-1;
					while(j>=0 && !((int)sCoeff.charAt(j) >= 97 && (int)sCoeff.charAt(j) <= 122))
						j--;
					pos--;
					String coeff = sCoeff.substring(j+1,i);
					if (coeff.length()==1){
						if (coeff.charAt(0) == '-')
							coeff="-1";
						else if(coeff.charAt(0) == '+')
							coeff="+1";
					}
					else if(coeff.length()==0)
						coeff="+1";

					ris[pos] = Double.parseDouble(coeff);
					i=j+1;
				}
			}
		}
		return ris;
	}

	private static double ricavaNoto(String s,int dim){
		double ris = -1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '='){
				ris = Double.parseDouble(s.substring(i+1,s.length()));
				break;
			}
		}
		return ris;
	}


	public static void main(String[] args) {
		Gauss m = catturaMatrice();
		for (int i = 0; i < m.getMatrice().length; i++) {
			double[] d = m.getMatrice()[i];
			System.out.println(Arrays.toString(d));
		}
	}

	public String toString(){
		String s = "";
		for (int i = 0; i < getN(); i++){
			for (int j = 0; j <= getN(); j++){
				s+= s+ String.format("%7.2f ",a[i][j]);
			}
			s+= s+"\n";
		}
		return s;
	}




}
