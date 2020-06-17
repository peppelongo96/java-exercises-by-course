package anno16_17.poo.sistema;

public abstract class SistemaQuadrato {
	private final int N; //dimensione

	public int getN(){return N;}

	public abstract double [] risolvi();

	public SistemaQuadrato (double[][]a, double[] y){
		for (int i = 0; i < a.length; i++) {
			if (a[i].length != a.length) throw new IllegalArgumentException();
		}
		if (y.length != a.length) throw new IllegalArgumentException();
		this.N = a.length;
	}
}
		
	
