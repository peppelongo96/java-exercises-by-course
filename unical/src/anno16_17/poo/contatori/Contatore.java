package anno16_17.poo.contatori;

public class Contatore{
	private int id;
	protected int valore;
	private static int gid = 0;

	public Contatore(){
		this.id = gid++;
		valore = 0;
	}

	public Contatore(int valore){
		this.id = gid++;
		this.valore = valore;
	}

	public Contatore (Contatore c){
		this.valore = c.valore;
		this.id = gid++;
	}

	public int getId(){ return id;}
	public int getValore(){ return valore;}
	public void incr(){ valore++;}
	public void decr(){ valore--;}
	public String toString(){
		return "Contatore -> valore: "+valore+"; id: "+id;
	}
}