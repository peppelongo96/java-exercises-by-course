package anno17_18.ingdelsoftware.progetto.view.builder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCentroGriglia {
	
	private CentroGriglia cg;
	private int dim = 1;

	@BeforeEach
	void setUp() throws Exception {
		cg = new CentroGriglia(dim);
	}// setUp

	@Test
	void testGrigliaValida() {
		assertTrue( !cg.grigliaValida() );
	}// testGrigliaValida

}// TestCentroGriglia
