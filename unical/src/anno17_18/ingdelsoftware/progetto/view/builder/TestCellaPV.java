package anno17_18.ingdelsoftware.progetto.view.builder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCellaPV {
	
	private CellaPV cpv;
	private int riga = 1;
	private int colonna = 1;
	private int altMax = 1;
	
	@BeforeEach
	void setUp() throws Exception {
		cpv = new CellaPV(riga, colonna, altMax);
	}// setUp

	@Test
	void testAmmissibile() { 
		assertFalse( cpv.ammissibile() ); 
	}// testAmmissibile

}// TestCellaPV
