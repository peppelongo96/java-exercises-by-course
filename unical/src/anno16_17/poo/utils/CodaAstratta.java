package anno16_17.poo.utils;

import java.util.Iterator;

public abstract class CodaAstratta<T> implements Coda<T> {
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("TOP ");
		for (T e : this) sb.append(e+"\n");
		return sb.toString();
	}
	
	public boolean equals(Object o){
		if (!(o instanceof Stack)) return false;
		if (o == this) return true;
		Coda<T> s = (Coda)o;
		Iterator<T> it1 = this.iterator(), it2 = s.iterator();
		while ( it1.hasNext() )
			if (!(it1.next().equals(it2.next()))) return false;
		return true;
	}
	
	public int hashCode(){
		final int M = 83;
		int h = 0;
		for (T e : this) h = h*M+e.hashCode();
		return h;
	}
}
