package anno16_17.poo.agendina;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class AgendinaLL extends AgendinaAstratta{
	private LinkedList<Nominativo> tabella=
		new LinkedList<Nominativo>();

    public void svuota(){ tabella.clear(); }
    
    public int size(){ return tabella.size(); }

	public void aggiungi( Nominativo n ){
		boolean flag=false;
		ListIterator<Nominativo> lit=tabella.listIterator();
		while( lit.hasNext() && !flag ){
			Nominativo x=lit.next();
			if( x.compareTo(n)==0 ){
				lit.set(n); flag=true;
			}
			else if( x.compareTo(n)>0 ){
				lit.previous(); lit.add(n); flag=true;
			}
		}//while
		if( !flag ) lit.add(n);
	}

	public Iterator<Nominativo> iterator(){
		return tabella.iterator();
	}

}//AgendinaLL