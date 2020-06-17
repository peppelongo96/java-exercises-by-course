package anno16_17.poo.agendina;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class AgendinaSet extends AgendinaAstratta{
	private Set<Nominativo> tabella=new TreeSet<Nominativo>();

	@Override
    public Nominativo cerca( Nominativo n ){
		if( tabella.contains(n) ){
			for( Nominativo x: tabella )
				if( x.equals(n) ) return x;
		}
		return null;
	}//cerca

	@Override
	public void rimuovi( Nominativo n ){
		tabella.remove(n);
	}//rimuovi
	
	public int size(){ return tabella.size(); }
	
	public void aggiungi( Nominativo n ){
		tabella.remove(n);
		tabella.add(n);
	}

	public Iterator<Nominativo> iterator(){
		return tabella.iterator();
	}

}//AgendinaSet