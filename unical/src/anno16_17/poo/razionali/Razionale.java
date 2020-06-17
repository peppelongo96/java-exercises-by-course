package anno16_17.poo.razionali;
import anno16_17.poo.utils.*;
public class Razionale implements Comparable{

	private final int NUM, DEN;
	public static int contatore = 0;

	public Razionale( int num, int den ){
		if( den==0 ){
			System.out.println("Denominatore Nullo!");
			System.exit(-1);
		}
		int n=Math.abs(num);
		int d=Math.abs(den);
		int cd=Mat.mcd( n, d );
		if( den<0 ){
			NUM = (-1)*num/cd;
			DEN = (-1)*den/cd;
		}
		else{
			NUM = num/cd;
			DEN = den/cd;
		}
		contatore++;
	}
	public Razionale( Razionale r ){
		this.NUM = r.NUM;
		this.DEN = r.DEN;
	}

	public int getNUM(){ return this.NUM; }
	public int getDEN(){ return this.DEN; }
	public String toString(){
		return ""+NUM+"/"+DEN;
	}//toString

	public Razionale add( Razionale r ){
		int mcm=(this.DEN*r.DEN)/Mat.mcd(this.DEN,r.DEN);
		return new Razionale( (mcm/this.DEN)*this.NUM+(mcm/r.DEN)*r.NUM, mcm );
	}//add
	public Razionale mul( int s ){
		return new Razionale( this.NUM*s, this.DEN );
	}//mul
	public Razionale mul( Razionale r ){
		return new Razionale( this.NUM*r.NUM, this.DEN*r.DEN );
	}//mul
	public Razionale div( Razionale r ){
		return new Razionale( this.NUM*r.DEN, this.DEN*r.NUM );
	}//div

	public int compareTo(Object o){
		Razionale r = (Razionale)o;
		int mcm = Mat.mcm(this.DEN,r.DEN);
		int N1 = (mcm/this.DEN)*this.NUM;
		int N2 = (mcm/r.DEN)*r.NUM;
		if (N1 < N2) return -1;
		if (N1 == N2) return 0;
		return 1;
	}

	public static void main( String[] args ){
		Razionale r=new Razionale(4,8);
		Razionale t=new Razionale(8,24);
		System.out.println(r+"+"+t+"="+r.add(t));
		System.out.println(r+":"+t+"="+r.div(t));
	}//main
}//Razionali