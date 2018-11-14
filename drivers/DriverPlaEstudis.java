package drivers;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import classes.*;

public class DriverPlaEstudis {
	
	private PlaEstudis PE1;
	private PlaEstudis PE2;
	private PlaEstudis PE3;
	
	/**
	 * Iniciem alguns plans d'estudis
	 */
	@Before
	public void setUp() throws Exception {
		PE1 = new PlaEstudis("UPC");
		PE2 = new PlaEstudis("UAB");
		PE3 = new PlaEstudis("UDA");
	}
	
	/**
	 * Comprovem que vagi bé la constructora
	 */
	@Test 
	public void testconstructora() throws Exception {
		PlaEstudis PE = new PlaEstudis("fib");
	}
	
	/**
	 * Comprovem que salti error en cas de no passar-li nom
	 */
	@Test (expected = Exception.class)	public void testErrorConstructora() throws Exception {
		PlaEstudis PE = new PlaEstudis("");
	}
	
	/**
	 * Comprovem el correcte funcionament dels sets i gets
	 * @throws Exception 
	 */
	@Test 
	public void testSetGet() throws Exception {
		PE2.setNom("UB");
		assertEquals(PE2.getNom(), "UB");
		int[] rang = new int[] {8,13,15,20};
		PE3.setRangDia(2, rang);
		assertEquals(rang, PE3.getFranjaDia(2));
	}
	
	/**
	 * Comprovem que salti error en cas de intentar violar la clau primaria de PlaEstudis
	 */
	@Test (expected = Exception.class)
	public void testSetNomError() {
		PE1.setNom("UDA");
	}
	
	
	
	
	
}
