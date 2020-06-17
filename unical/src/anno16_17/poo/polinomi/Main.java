package anno16_17.poo.polinomi;

public class Main {
	public static void main( String[] args ){

		Polinomio p1=new PolinomioLL();
		p1.add( new Monomio(0,2));
		p1.add( new Monomio(-3,5));
		p1.add( new Monomio(4,5));
		p1.add( new Monomio(6,3));
		p1.add( new Monomio(-1,0));
		System.out.println("questo "+p1);
		
		Polinomio p7=new PolinomioAL();
		p7.add( new Monomio(0,2));
		p7.add( new Monomio(-3,5));
		p7.add( new Monomio(8,5));
		p7.add( new Monomio(6,3));
		p7.add( new Monomio(-1,0));
		System.out.println("questo2 "+p7);
		
		
		Polinomio p5 = new PolinomioLL(p7);
		System.out.println("copia "+p5);
		Polinomio p3 = new PolinomioAL("2X+2X+9X^9");
		System.out.println("regex "+p3);
		Polinomio p2=new PolinomioLL();
		p2.add(new Monomio(-4,2) );
		p2.add(new Monomio(2,2) );
		p2.add(new Monomio(-6,2) );
		p2.add(new Monomio(1,3) );
		System.out.println(p2);
		System.out.println("("+p1+")+"+"("+p2+")="+p1.add(p2));
		System.out.println("("+p1+")x"+"("+p2+")="+p1.mul(p2));
	}
}
