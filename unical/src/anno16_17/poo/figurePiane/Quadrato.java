package anno16_17.poo.figurePiane;

public class Quadrato extends Figura{

	public Quadrato (double lato){
		super(lato);
	}

	public double getLato(){return dimensione;}

	public double perimetro(){
		return 4*dimensione;
	}

	public double area(){
		double l = dimensione;
		return l*l;
	}

	public String toString(){
		return "Quadrato di lato: "+dimensione;
	}

	public static void main (String[] args){
		Figura f = new Quadrato(3);
		System.out.println(f);
	}
}