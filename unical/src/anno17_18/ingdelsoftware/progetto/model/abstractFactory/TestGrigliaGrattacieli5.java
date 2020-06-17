package anno17_18.ingdelsoftware.progetto.model.abstractFactory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestGrigliaGrattacieli5 {
	
	GrigliaGrattacieli5 gg5;

	@BeforeEach
	void setUp() throws Exception {
		gg5 = GrigliaGrattacieli5.crea();
	}// setUp
	
	@Test
	void testGrigliaValida() {
		assertTrue( !gg5.grigliaValida() );
	}// testGrigliaValida
	
	@Test
	void testVerificaAllPV() {
		assertTrue( gg5.verificaAllPV() );
	}// testVerificaAllPV

}// TestGrigliaGrattacieli5
