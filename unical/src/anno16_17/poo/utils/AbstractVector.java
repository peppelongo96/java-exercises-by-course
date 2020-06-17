package anno16_17.poo.utils;

import java.util.Iterator;

public abstract class AbstractVector<T> implements Vector<T> {

	@SuppressWarnings("unchecked")
	public boolean equals( Object x ){
		if( !(x instanceof Vector) ) return false;
		if( x==this ) return true;
		Vector<T> v=(Vector<T>)x;
		if( this.size()!=v.size() ) return false;
		Iterator<T> i1=iterator(), i2=v.iterator();
		while( i1.hasNext() ){
			T e1=i1.next();
			T e2=i2.next();
			if( !e1.equals(e2) ) return false;
		}
		return true;
	}//equals

	public int hashCode(){
		final int M=83;
		int h=0;
		for( T e: this ) h=h*M+e.hashCode();
		return h;
	}//hashCode

	public String toString(){
		StringBuilder sb=new StringBuilder(100);
		sb.append("[");
		for( T e: this ) sb.append(e+", ");
		if( size()>0 ) sb.setLength( sb.length()-2 );
		sb.append("]");
		return sb.toString();
	}//toString

}//AbstractVector
