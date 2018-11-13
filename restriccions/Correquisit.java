package restriccions;

import java.util.*;
import classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class Correquisit extends Binaria{
	
	/**
	 * Map on guardarem tots els correquisits
	 */
	private static Map<Assignatura, Assignatura[]> correquisits = new HashMap<Assignatura, Assignatura[]>();
	
	/**
	 * Indica si la assignatura a te com a correquisit l'assignatura b
	 * @param a assignatura que volem saber si te com a correquisit b
	 * @param b assignatura que volem saber si es correquisit de b
	 * @return true si son correquisits, false altrament
	 */
	private static Boolean escorrequisit(Assignatura a, Assignatura b) {
		Assignatura c[] = correquisits.get(a);
		if (c.length != 0)
			for (Assignatura d : c) if (d.getNom().equals(b.getNom())) return true;
		return false;
	}
	
	/**
	 * Indica si la sessió de grup sg1 pot solapar-se amb la sessió de grup sg2
	 * @param sg1 sessió de grup 1
	 * @param sg2 sessió de grup 2
	 * @return true si no es poden solapar, false altrament 
	 */
	public static Boolean checkCorrequisit(SessioGrup sg1, SessioGrup sg2) {
		if (escorrequisit(sg1.getAssignatura(), sg2.getAssignatura())) return true;
		return false;
	}
	
	/**
	 * Indica si la sessió de subgrup ssg1 pot solapar-se amb la sessió de subgrup ssg2
	 * @param ssg1 sessió de subgrup 1
	 * @param ssg2 sessió de subgrup 2
	 * @return true si no es poden solapar, false altrament 
	 */
	public static Boolean checkCorrequisit(SessioSubGrup ssg1, SessioSubGrup ssg2) {
		if (escorrequisit(ssg1.getAssignatura(), ssg2.getAssignatura())) return true;
		return false;
	}
}
