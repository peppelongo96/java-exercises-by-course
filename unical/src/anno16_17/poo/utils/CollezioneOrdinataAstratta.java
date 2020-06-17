package anno16_17.poo.utils;

import java.util.Iterator;

public abstract class CollezioneOrdinataAstratta<T extends Comparable<? super T>> implements CollezioneOrdinata<T> {
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (T e : this)
			sb.append(e+", ");
		sb.deleteCharAt(sb.length()-1); 
		sb.deleteCharAt(sb.length()-1); 
		sb.append("]");
		return sb.toString();
	}
	
	public int hashCode(){
		final int M=43;
		int h=0;
		for( T e: this ){
			h=h*M+e.hashCode();
		}
		return h;
	}
	
	public boolean equals(Object o){
		if (!(o instanceof CollezioneOrdinata)) return false;
		if (o == this) return true;
		CollezioneOrdinata co = (CollezioneOrdinata)o;
		Iterator<T> it1 = this.iterator();
		Iterator<T> it2 = co.iterator();
		while ( it1.hasNext() )
			if (!(it1.next().equals(it2.next())))
				return false;
		return true;
	}

}//CollezioneOrdinataAstratta
