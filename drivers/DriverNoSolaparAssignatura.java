package drivers;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import classes.*;
import restriccions.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DriverNoSolaparAssignatura {
	
	private Assignatura a, b;
	
	private PlaEstudis p;
	
	private NoSolaparAssignatura nsa;
	
	@Before
	public void setUp() throws Exception {
		p = new PlaEstudis("fib");
		a = new Assignatura(p, "prop");
		b = new Assignatura(p, "IES");
		nsa = new NoSolaparAssignatura(a);
	}
	
	/**
	 * Comprova la constructora
	 */
	@Test
	public void constructora() {
		NoSolaparAssignatura nsa = new NoSolaparAssignatura(a);
	}
	
	/**
	 * Comprova que vagi bé el checkSolapar
	 */
	@Test
	public void check1() {
		int n = nsa.checkSolapar(b);
		assertEquals(n, 0);
	}
	
	/**
	 * Comprova que es pot afegir i eliminar solapaments
	 */
	@Test
	public void adddel() {
		assertEquals(nsa.addNoSolapar(b), 0); // 0 no error
		assertEquals(nsa.checkSolapar(b), 1); // 1 perque no es poden solapar
		assertEquals(nsa.delNoSolapar(b), 0); // 0 no error
		assertEquals(nsa.checkSolapar(b), 0); // 0 es poden solapar
	}
}
