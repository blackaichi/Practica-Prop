package JUNIT;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.*;
import classes.*;

public class JUNITSubGrup {
	
	PlaEstudis pe;
	Assignatura a;
	Grup g;
	SubGrup sg;
	SessioGrup sG;
	SessioSubGrup sSG;
	
	@Before
	public void setUp() throws Exception {
		pe = new PlaEstudis("fib");
		a = new Assignatura(pe, "prop");
		g = new Grup(a, 100);
		sg = new SubGrup(g,11);
		sG = new SessioGrup(a, "PCs");
		sSG = new SessioSubGrup(a, "lab");
		
	}
	
	@Test
	public void constructora1() throws Exception {
		SubGrup sg1 = new SubGrup(g, 12);
	}
	
	@Test
	public void errorConstructora() throws Exception {
		SubGrup sg1 = new SubGrup(g, 14, 300);
	}
	
	@Test
	public void constructora2() throws Exception {
		SubGrup sg2 = new SubGrup(g, 13, 10);
	}
	
	@Test
	public void TestSetGet() throws Exception {
		assertEquals(sg.setNumero(12), 0);
		assertEquals(sg.getNumero(), 12);
		
		Grup g2 = new Grup(a, 20);
		assertEquals(sg.setGrup(g2), 0);
		assertEquals(sg.getGrup().getNumero(), 20);
	}
	
	@Test
	public void testSolapamentsHoresAptes() throws Exception {
		SubGrup sg2 = new SubGrup(g, 14);
		assertEquals(sg.setSolapament(g, sg2, true), 0);
		assertEquals(sg.setHoresAptes(null, true, false), 0);
	}
	
	@Test
	public void testPlaces() {
		assertEquals(sg.setPlaces(20, true), 0);
		assertEquals(sg.getPlaces(), 20);
		assertEquals(sg.obrirPlaces(10), 0);
		assertEquals(sg.getPlaces(), 30);
		assertEquals(sg.tancarPlaces(15), 0);
		assertEquals(sg.getPlaces(), 15);
		
	}
	
	@Test
	public void testSessions() throws Exception {
		SessioSGAssignada sSGA = new SessioSGAssignada(sg, sSG);
		
		assertEquals(sg.quantesSessions(), 0);
		assertEquals(sg.assignaSessio("lab", 1), 0);
		assertEquals(sg.quantesSessions(), 1);
		assertEquals(sg.desassignaSessio(sSGA), 0);
		
		assertEquals(sg.quantesSessions(), 0);
		
		assertEquals(sg.afegeixSessio(sSGA), 0);
		assertEquals(sg.quantesSessions(), 1);
		assertEquals(sg.eliminaSessio(sSGA), 0);
		assertEquals(sg.quantesSessions(), 0);
		
		assertEquals(sg.checkSessio("lab", 1), 0);
	}
}
