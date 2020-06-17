package anno17_18.ingdelsoftware.progetto.model.abstractFactory;

class GrigliaGrattacieli5 extends GrigliaGrattacieli {
	
	private static GrigliaGrattacieli5 instance; // SINGLETON
			
	private GrigliaGrattacieli5() {
		super();
	}// COSTRUTTORE
	
	static GrigliaGrattacieli5 crea() {
		if ( instance == null )
			instance = new GrigliaGrattacieli5();
		return instance;
	}// crea

	@Override
	public int getDim() {
		return 5;
	}// getDim

	@Override
	public int getValMax() {
		return 5;
	}// getValMax

	@Override
	public int getValMin() {
		return 1;
	}// getValMin

}// GrigliaGrattacieli5
