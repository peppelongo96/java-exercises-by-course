
package anno16_17.poo.polinomi;

public class Monomio implements Comparable<Monomio>{
	
	private final int coeff, grado;
	
	public Monomio( int coeff, int grado ){
		if( grado<0 ) 
			throw new RuntimeException("Grado negativo");
		this.coeff=coeff; this.grado=grado;
	}//Monomio
	
	public Monomio( Monomio m ){
		coeff=m.coeff; grado=m.grado;
	}//Monomio
	
	public int getCoeff(){ return coeff; }
	
	public int getGrado(){ return grado; }
	
	public Monomio add( Monomio m ){
		if( !this.equals(m) ) 
			throw new RuntimeException("Monomi non simili");
		return new Monomio( coeff+m.coeff, grado );
	}//add
	
	public Monomio mul( Monomio m ){
		return new Monomio( coeff*m.coeff, grado+m.grado );
	}//mul
	
	public Monomio mul( int s ){
		return new Monomio( coeff*s, grado );
	}//mul
	
	public Monomio derivata(){
		int coeff = this.coeff*this.grado;
		int grado = this.grado-1;
		if (coeff != 0) return new Monomio(coeff,grado);
		return null;
	}
	
	public int compareTo( Monomio m ){
		if( grado>m.grado ) return -1;
		if( grado<m.grado ) return +1;
		return 0;
	}//compareTo
	
	public boolean equals( Object o ){
		//uguaglianza come similitudine
		if( !( o instanceof Monomio ) ) return false;
		if( o==this ) return true;
		Monomio m=(Monomio)o;
		return this.grado==m.grado;
	}//equals
	
	public int hashCode(){
		return grado;
	}//hashCode
	
	public String toString(){
		StringBuilder sb=new StringBuilder();
		if( coeff<0 ) sb.append('-');
		if( Math.abs(coeff)!=1 || grado==0 )
			sb.append( Math.abs(coeff) );
		if( coeff!=0 && grado>0 ) sb.append('x');
		if( coeff!=0 && grado>1 ){
			sb.append('^');
			sb.append( grado );
		}
		return sb.toString();
	}//toString
	
}//Monomio