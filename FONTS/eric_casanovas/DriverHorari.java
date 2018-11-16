package drivers;

import java.util.*;
import classes.*;
import utils.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DriverHorari {

	/**
	 * Funció main del driver, introdueixes un número i et comprova unes quantes funcions
	 * @throws Exception
	 */
	public static void main (String [ ] args) throws Exception {
		System.out.println("Benvingut a l'eina de comprovació de la classe Horari, agarrense que vienen curvas");
		Horari h = new Horari();
		PlaEstudis pe = new PlaEstudis("fib");
		Assignatura a = new Assignatura(pe, "prop");
		SessioGrup sG = new SessioGrup(a, "teoria");
		SessioSubGrup sSG= new SessioSubGrup(a, "laboratori");
		Grup g = new Grup(a, 10);
		g.setPlaces(20);
		SubGrup sg = new SubGrup(g, 11);
		sg.setPlaces(20, false);
		SessioGAssignada sGA = new SessioGAssignada(g, sG);
		SessioSGAssignada sSGA = new SessioSGAssignada(sg, sSG);
		Campus c = new Campus("campus nord");
		Aula aula = new Aula(c, "A5001", 20);
		HashSet<Map<Integer, Map<Integer, HashSet<Segment>>>> horari = new HashSet<Map<Integer, Map<Integer, HashSet<Segment>>>>();
		Horari.printHorari(h.GENERADOR(pe, c, 2).iterator().next());
		System.out.println("Madre mia willy compañero ha funcionat");
	}
}
