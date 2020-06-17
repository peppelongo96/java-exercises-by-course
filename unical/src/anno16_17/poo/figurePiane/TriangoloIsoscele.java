package anno16_17.poo.figurePiane;

public class TriangoloIsoscele extends Figura implements FiguraPiana{
	private double altezza;

	public TriangoloIsoscele (double base, double altezza){
		super(base);
		if (base <= 0 || altezza <= 0) throw new IllegalArgumentException();
		this.altezza = altezza;
	}

	public double getBase(){ return dimensione;}

	public double getAltezza(){ return altezza;}

	public double perimetro(){
		double mezza_base = dimensione/2;
		double lato = Math.sqrt(mezza_base*mezza_base+altezza*altezza);
		return lato*3;
	}

	public double area(){
		return (dimensione * altezza) /2;
	}
}