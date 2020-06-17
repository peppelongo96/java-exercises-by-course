package anno16_17.poo.geometria;

public class Triangolo{

   private Punto p1, p2, p3;
   private double a, b, c;

   public Triangolo( Punto p1, Punto p2, Punto p3 ){
	   a=p1.distanza(p2);
	   b=p2.distanza(p3);
	   c=p3.distanza(p1);
	   //verifica esistenza triangolo
	   if( a>b+c || a<b-c || b>a+c || b<a-c || c>a+b || c<a-b ){
         System.out.println("Triangolo inesistente");
         System.exit(-1);
	   }
       this.p1=new Punto( p1 );
       this.p2=new Punto( p2 );
       this.p3=new Punto( p3 );

   }
   public Triangolo( Triangolo t ){
	   p1=new Punto( t.p1 );
	   p2=new Punto( t.p2 );
	   p3=new Punto( t.p3 );
	   this.a=t.a;
	   this.b=t.b;
	   this.c=t.c;
   }
   public double getA(){ return a; }
   public double getB(){ return b; }
   public double getC(){ return c; }
   public double perimetro(){
	   return a+b+c;
   }//perimetro

   public double area(){
	   double s=this.perimetro()/2;
	   return Math.sqrt(s*(s-a)*(s-b)*(s-c));
   }//area

   public String tipo(){
		if (a == b || b == c || a == c ){
			if (a * 3 == a+b+c)
				return " Triangolo equilatero";
			return " Triangolo isoscele";
		}
		return " Triangolo scaleno";
   }//tipo

   public Punto[] vertici(){
	   Punto[] ris = {p1,p2,p3};
	   return ris;
   }//array vertici

   public String toString(){
	   return "Triangolo con vertici: "+p1+" "+p2+" "+p3+"; lati: "+a+" "+b+" "+c+"-->"+tipo();
   }//toString

   public static void main( String[] args ){
   		Punto p=new Punto(2,3);
   		Punto q=new Punto(4,5);
   		Punto r=new Punto(8,10);
		Triangolo t=new Triangolo(p,q,r);
   }
}//Triangolo