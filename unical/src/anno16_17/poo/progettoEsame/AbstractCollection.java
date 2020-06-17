package anno16_17.poo.progettoEsame;

import java.util.Iterator;

public abstract class AbstractCollection<T> implements Collection<T> {
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if ( this.size()!=0 ){
			for (T e : this){
				sb.append(" "+e+",");
			}
			sb.deleteCharAt(sb.length()-1); 
		}
		sb.append(" ]");
		return sb.toString();
	}//toString
	
	public boolean equals(Object o){
		if ( !(o instanceof Collection) ) return false;
		if ( o==this ) return true;
		@SuppressWarnings("unchecked")
		Collection<T> c = (Collection<T>)o;
		Iterator<T> it = c.iterator();
		for (T e : this){
			if ( it.hasNext() ){
				if ( !(e.equals(it.next())) ) return false;
			}
			else return false;	
		}
		return true;
	}//equals
	
	public int hashCode(){
		int M = 83;
		int h = 0;
		for ( T e : this) h = h+M*e.hashCode();
		return h;
	}//hashCode
	
}//AbstractCollection
