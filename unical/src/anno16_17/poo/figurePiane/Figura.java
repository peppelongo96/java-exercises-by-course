package anno16_17.poo.figurePiane;

public abstract class Figura{
	protected double dimensione;
	
	public Figura(double dim){
		dim = dimensione;
	}

	protected double getDim(){ return dimensione;}
	public abstract double perimetro();
	public abstract double area();
}