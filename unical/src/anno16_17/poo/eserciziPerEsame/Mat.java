package anno16_17.poo.eserciziPerEsame;

public final class Mat {
	private Mat() {}//constructor
	
	public static long fibo(int n) {
		if(n < 1)
			throw new IllegalArgumentException();
		if(n == 1)
			return 1;
		int[][] x = {{1,1},{1,0}};
		int[][] a = fiboRic(x, n-1);
		return a[0][0];
	}

	private static int[][] fiboRic(int[][] x, int n) {
		if(n == 1)
			return x;
		int[][] t = fiboRic(x,n/2);
		int a = t[0][0], b = t[0][1],
			c = t[1][0], d = t[1][1];
		int[][] r = {{a*a+b*c,a*b+b*d},{a*c+c*d,b*c+d*d}};
		if(n % 2 == 1) {
			a = r[0][0]; b = r[0][1];
			c = r[1][0]; d = r[1][1];
			r[0][0] = a * x[0][0] + b * x[1][0];
			r[0][1] = a * x[0][1] + b * x[1][1];
			r[1][0] = c * x[0][0] + d * x[1][0];
			r[1][1] = c * x[1][0] + d * x[1][1];
		}
		return r;
	}//fiboRic
}//Mat