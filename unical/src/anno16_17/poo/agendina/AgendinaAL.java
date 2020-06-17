package anno16_17.poo.agendina;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AgendinaAL extends AgendinaAstratta{
	private List<Nominativo> tabella;
	public AgendinaAL(){
		this(100);
	}
	public AgendinaAL( int n ){
		if( n<=0 ) throw new IllegalArgumentException();
		tabella=new ArrayList<>(n);
	}

	public int size(){ return tabella.size(); }
	
    public Nominativo cerca( Nominativo n){
    	int i=Collections.binarySearch(tabella,n);
    	if( i>=0 ) return tabella.get(i);
    	return null;
    }//cerca

    public void aggiungi( Nominativo n ){
		int i=0;
		boolean flag=false;
		while( i<tabella.size() ){
			if( tabella.get(i).compareTo(n)==0 ){
				//update
				tabella.set(i, n); flag=true; break;
			}
			if( tabella.get(i).compareTo(n)>0 ){
				tabella.add(i,n); flag=true; break;
			}
			i++;
		}//while
		if( !flag ) tabella.add(n);
	}//aggiungi

	public Iterator<Nominativo> iterator(){
		return tabella.iterator();
	}//iterator

}//AgendinaAL