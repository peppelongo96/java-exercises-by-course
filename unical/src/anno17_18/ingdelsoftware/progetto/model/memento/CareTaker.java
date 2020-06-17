package anno17_18.ingdelsoftware.progetto.model.memento;

import java.io.IOException;

public interface CareTaker {
	
	public Memento getMem( int pos ) throws IOException, ClassNotFoundException;
	public void saveMem ( Memento m, int nrSol ) throws IOException;
	public void graficaMemento ( int nrSol );
	
}// CareTaker
