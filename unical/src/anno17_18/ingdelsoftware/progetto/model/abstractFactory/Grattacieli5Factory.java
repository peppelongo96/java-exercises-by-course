package anno17_18.ingdelsoftware.progetto.model.abstractFactory;

public class Grattacieli5Factory implements GrattacieliFactory{

	@Override
	public GrigliaGrattacieli creaGriglia() { 
		return GrigliaGrattacieli5.crea();
	}// creaGriglia
	
}// Grattacieli5Factory
