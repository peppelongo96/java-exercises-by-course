package anno16_17.poo.esempi;
import anno16_17.poo.geometria.Punto;
public class Clonabile implements Cloneable{
	private int x;
	private Punto p;
	
	public Clonabile( int x, Punto p ){
		this.x=x;
		this.p=p;
	}
	public String toString(){
		return "x="+x+" p="+p;
	}
	
	public Clonabile clone() throws CloneNotSupportedException{
		Clonabile c=(Clonabile)super.clone();
		//correzioni per copia profonda
		c.p=new Punto(p);
		return c;
	}
	
	public static void main( String[] args ) throws CloneNotSupportedException{
		Punto p=new Punto(3,4);
		Clonabile c=new Clonabile(30,p);
		Clonabile q=c.clone();
		System.out.println(c);
		p.sposta( 40, 50 );
		System.out.println(c);
		System.out.println(q);
	}
}
