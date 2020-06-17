package anno16_17.poo.contatori;
public class ContatoreModulare extends Contatore{
	private int modulo;

	public ContatoreModulare (int modulo){
		super();
		if (modulo <= 0) throw new IllegalArgumentException();
		this.modulo = modulo;
	}

	public ContatoreModulare(int valore, int modulo){
		super(valore);
		if (modulo <= 0 || valore < 0 || valore >= modulo) throw new IllegalArgumentException();
		this.modulo = modulo;
	}

	public ContatoreModulare (ContatoreModulare c){
		super (c);
	}

	public int getModulo(){ return modulo;}

	@Override
	public void incr(){
		valore = (valore +1)%modulo;
	}

	@Override
	public void decr(){
		valore =(valore-1+modulo)%modulo;
	}

	public String toString(){
		return super.toString()+ "; Modulo: "+modulo;
	}

	public static void main(String[] args){
		Contatore c = new Contatore(4);
		ContatoreModulare cm = new ContatoreModulare(8);
		c = cm;
		c.incr();
	}
}
