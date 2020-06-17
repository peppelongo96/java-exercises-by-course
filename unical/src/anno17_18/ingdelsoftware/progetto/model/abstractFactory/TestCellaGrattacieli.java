package anno17_18.ingdelsoftware.progetto.model.abstractFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCellaGrattacieli {
	
	private CellaGrattacieli cg;
	private int riga = 1;
	private int colonna = 1;
	private int altezza = 1;

	@BeforeEach
	void setUp() throws Exception {
		cg = new CellaGrattacieli( riga, colonna, altezza );
	}// setUp

	@Test
	void testGetRiga() {
		assertEquals( cg.getRiga(), this.riga );
	}// testGetRiga
	
	@Test
	void testGetValore() {
		assertEquals( cg.getValore(), this.altezza );
	}// testGetValore
	
}// TestCellaGrattacieli
