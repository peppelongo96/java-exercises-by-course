package anno16_17.poo.geometria;

public class Sfera extends Disco implements FiguraSolida{

	public Sfera(double raggio){
		super(raggio);
	}

	public Sfera(double x, double y, double raggio){
		super (x,y,raggio);
	}

	public Sfera(Sfera s){
		super (s.getY(), s.getX(), s.getRaggio());
	}

	public double perimetro(){
		throw new UnsupportedOperationException();
	}

	public double area(){
		double r = getRaggio();
		return (4*Math.PI*r*r);
	}


	public double volume(){
		double r = getRaggio();
		return (4*Math.PI*r*r)/3;
	}

	public double areaLaterale(){
		return area();
	}

	public double areaBase(){
		return super.area();
	}

	@Override
	public String toString(){
		return "Sfera--> "+super.toString();
	}
}

