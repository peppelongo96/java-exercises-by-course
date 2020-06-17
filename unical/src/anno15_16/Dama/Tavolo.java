package anno15_16.Dama;

class Tavolo {
	private Casella[][] tavolo;
	
	public Tavolo(){
		tavolo = new Casella[8][8];
	}
	
	public Casella[][] getMatrice(){
		return this.tavolo;
	}
}
