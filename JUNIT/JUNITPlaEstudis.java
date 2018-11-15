package JUNIT;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.*;
import classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class JUNITPlaEstudis {
	
	private PlaEstudis PE1;
	private PlaEstudis PE2;
	private PlaEstudis PE3;
	private Assignatura a;
	private Assignatura b;
	
	
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
	 * Comprovem el correcte funcionament de setRangDia i els gets del rang
	 * @throws Exception 
	 */
	@Test 
	public void testSetGetRangDia() throws Exception {
		int[] rang = new int[] {8,13,15,20};
		PE3.setRangDia(rang);
		assertArrayEquals(new int[] {8,13}, PE3.getRangMati());
		assertArrayEquals(new int[] {15,20}, PE3.getRangTarda());
	}
	
	/**
	 * Comprovem el correcte funcionament de setNom i getNom
	 */
	@Test
	public void getSetNom() {
		PE2.setNom("UB");
		assertEquals(PE2.getNom(), "UB");
	}
	
	/**
	 * Comprovem que salti error en cas de intentar violar la clau primaria de PlaEstudis
	 */
	@Test (expected = Exception.class)
	public void testSetNomError() {
		PE1.setNom("UDA");
	}	
	
	/**
	 * Comprova que funcioni donar d'alta i donar de baixa assignatures
	 * @throws Exception
	 */
	@Test
	public void checkAssignatures() throws Exception {
		assertEquals(PE1.altaAssignatura("PROP"), 0);
		assertEquals(PE3.altaAssignatura("IES"), 0);
		assertEquals(PE1.quantesAssignatures(), 1);
		assertEquals(PE2.quantesAssignatures(), 0);
		assertEquals(PE1.baixaAssignatura("PROP"), 0);
		assertEquals(PE1.quantesAssignatures(), 0);
	}
	
	@Test
	public void getFranja() {
		
	}
}
