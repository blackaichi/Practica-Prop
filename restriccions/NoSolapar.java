package restriccions;

import java.util.*;
import classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class NoSolapar extends Binaria{
	/////////////////////////////////////////////////////////////
	//////////////////////// Variables //////////////////////////
	
	/**
	 * Map on guardarem totes les assignatures que no poden solapar-se
	 */
	private static Map<Assignatura, Vector<Assignatura>> nosolapaments = new HashMap<Assignatura, Vector<Assignatura>>();
	
	/////////////////////////////////////////////////////////////
	/////////////////////////  Privats  /////////////////////////
	
	/**
	 * Indica si la assignatura a te com a correquisit l'assignatura b
	 * @param a assignatura que volem saber si te com a correquisit b
	 * @param b assignatura que volem saber si es correquisit de b
	 * @return true si son correquisits, false altrament
	 */
	private static Boolean esPodenSolapar(Assignatura a, Assignatura b) {
		Vector<Assignatura> c = nosolapaments.get(a);
		if (c.size() != 0)
			for (Assignatura d : c) 
				if (d.getNom().equals(b.getNom())) return false;
		return true;
	}
	
	/**
	 * Busca si l'assignatura passada per paràmetre esta dins del vector d'assignatures
	 * @param a assignatura per buscar si esta dins el vector
	 * @param b vector d'assignatures
	 * @return true si l'assignatura esta dins del vector d'assignatures, false altrament
	 */
	private static Boolean cerca(Assignatura a, Vector<Assignatura> b) {
		for (Assignatura d : b) 
			if (d.getNom().equals(a.getNom())) return true;
		return false;
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////// Publics ///////////////////////////
	
	/**
	 * Indica si la sessió de grup sg1 pot solapar-se amb la sessió de grup sg2
	 * @param sg1 sessió de grup 1
	 * @param sg2 sessió de grup 2
	 * @return 1 si no es poden solapar, 0 si es poden solapar, altrament error 
	 */
	public static int checkSolapar(SessioGrup sg1, SessioGrup sg2) {
		if (sg1 == null || sg2 == null) return 192;
		else if (sg1.getTipus().equals(sg2.getTipus()) && sg1.getHores() == sg2.getHores()) return 194;
		else if (esPodenSolapar(sg1.getAssignatura(), sg2.getAssignatura())) return 0;
		return 1;
	}
	
	/**
	 * Indica si la sessió de subgrup ssg1 pot solapar-se amb la sessió de subgrup ssg2
	 * @param ssg1 sessió de subgrup 1
	 * @param ssg2 sessió de subgrup 2
	 * @return 1 si no es poden solapar, 0 si es poden solapar, altrament error
	 */
	public static int checkSolapar(SessioSubGrup ssg1, SessioSubGrup ssg2) {
		if (ssg1 == null || ssg2 == null) return 193;
		else if (ssg1.getTipus().equals(ssg2.getTipus()) && ssg1.getHores() == ssg2.getHores()) return 195;
		else if (esPodenSolapar(ssg1.getAssignatura(), ssg2.getAssignatura())) return 0;
		return 1;
	}
	
	/**
	 * Afegir una restricció de no solapar al Map entre les assignatures a i b
	 * @param a l'assignatura que volem que no es pugui solapar
	 * @param b l'assignatura que volem que no es pugui solapar
	 * @return 0 si no hi ha cap error, altrament error
	 */
	public static int addNoSolapar(Assignatura a, Assignatura b) {
		Vector<Assignatura> c = nosolapaments.get(a);
		Vector<Assignatura> d = nosolapaments.get(b);
		if (cerca(b, c) || cerca(a, d)) return 190;
		c.add(b);
		d.add(a);
		nosolapaments.put(a, c);
		nosolapaments.put(b, d);
		return 0;
	}
	
	/**
	 * Eliminar una restricció de no solapar al Map entre les assignatures a i b
	 * @param a l'assignatura que volem que es torni a poder solapar
	 * @param b l'assignatura que volem que es torni a poder solapar
	 * @return 0 si no hi ha cap error, altrament 0
	 */
	public static int delNoSolapar(Assignatura a, Assignatura b) {
		Vector<Assignatura> c = nosolapaments.get(a);
		Vector<Assignatura> d = nosolapaments.get(b);
		if (!cerca(b, c) || !cerca(a, d)) return 191;
		c.indexOf(b); c.remove(b);
		d.indexOf(a); d.remove(a);
		nosolapaments.put(a, c);
		nosolapaments.put(b, d);
		return 0;
	}
}
