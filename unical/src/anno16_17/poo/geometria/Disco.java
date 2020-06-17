package anno16_17.poo.geometria;

import anno16_17.poo.figurePiane.*;

public class Disco extends Punto implements FiguraPiana{
	private double raggio;

	public Disco(double raggio){
		super();
		this.raggio = raggio;
	}

	public Disco (double x, double y, double raggio){
		super (x,y);
		this.raggio = raggio;
	}

	public Disco (Disco d){
		super (d.x, d.y);
		raggio = raggio;
	}

	public double getRaggio(){
		return raggio;
	}

	public double perimetro(){
		return 2*Math.PI*raggio;
	}

	public double area(){
		return raggio*raggio*Math.PI;
	}

	public String toString(){
		return "Disco di raggio: "+raggio+" e di centro: "+super.toString();
	}

	public static void main (String[] args){
		Punto p = new Punto(2,3);
		Disco d = new Disco(3,5,2);
		double x = p.distanza(d);
		d.sposta(4,7);
	}
}
