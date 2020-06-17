package anno16_17.poo.geometria;

public class Punto{
   protected double x, y;

   public Punto(){ x=0; y=0; }//costruttore di default

   public Punto( double x, double y ){//costruttore normale
      this.x=x; this.y=y;
   }

   public Punto( Punto p ){//costruttore di copia
      x=p.x; y=p.y;
   }

   public double getX(){ return x; }
   public double getY(){ return y; }

   public void sposta( double x, double y ){
      this.x=x; this.y=y;
   }//sposta

   public double distanza( Punto p ){
      return Math.sqrt((p.x-this.x)*(p.x-this.x)+(p.y-this.y)*(p.y-this.y));
   }//distanza

   public String toString(){
      return "Punto<"+x+","+y+">";
   }//toString

   public static void main( String[] args ){
	   Punto p = new Punto(2,9);
	   System.out.print(p);
	   System.out.print(p.distanza(new Punto()));
   }//test class
}//Punto