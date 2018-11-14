package restriccions;

import java.util.*;
import classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class Correquisit {
	/////////////////////////////////////////////////////////////
	//////////////////////// Variables //////////////////////////
	
	/**
	 * Map on guardarem tots els correquisits
	 */
	private Map<Assignatura, Vector<Assignatura>> correquisits = new HashMap<Assignatura, Vector<Assignatura>>();
	
	/////////////////////////////////////////////////////////////
	/////////////////////////  Privats  /////////////////////////
	
	/**
	 * Indica si la assignatura a te com a correquisit l'assignatura b
	 * @param a assignatura que volem saber si te com a correquisit b
	 * @param b assignatura que volem saber si es correquisit de b
	 * @return true si son correquisits, false altrament
	 */
	private Boolean esCorrequisit(Assignatura a, Assignatura b) {
		Vector<Assignatura> c = correquisits.get(a);
		if (c.size() != 0)
			for (Assignatura d : c) if (d.getNom().equals(b.getNom())) return true;
		return false;
	}

	/**
	 * Busca si l'assignatura passada per paràmetre esta dins del vector d'assignatures
	 * @param a assignatura per buscar si esta dins el vector
	 * @param b vector d'assignatures
	 * @return true si l'assignatura esta dins del vector d'assignatures, false altrament
	 */
	private Boolean cerca(Assignatura a, Vector<Assignatura> b) {
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
	public int checkCorrequisit(Assignatura a, Assignatura b) {
		if (a == null || b == null) return 202;
		else if (a.getNom().equals(b.getNom())) return 203;
		else if (esCorrequisit(a, b)) return 1;
		return 0;
	}
	
	/**
	 * Afegir un correquisit al Map entre les assignatures a i b
	 * @param a l'assignatura que volem que sigui correquisit
	 * @param b l'assignatura que volem que sigui correquisit
	 * @return 0 si no hi ha cap error, altrament error
	 */
	public int addNoSolapar(Assignatura a, Assignatura b) {
		if (a == null || b == null) return 202;
		Vector<Assignatura> c = correquisits.get(a);
		Vector<Assignatura> d = correquisits.get(b);
		if (cerca(b, c) || cerca(a, d)) return 200;
		c.add(b);
		d.add(a);
		correquisits.put(a, c);
		correquisits.put(b, d);
		return 0;
	}
	
	/**
	 * Eliminar un correquisit al Map entre les assignatures a i b
	 * @param a l'assignatura que volem eliminar de correquisit
	 * @param b l'assignatura que volem eliminar de correquisit
	 * @return 0 si no hi ha cap error, altrament error
	 */
	public int delNoSolapar(Assignatura a, Assignatura b) {
		if (a == null || b == null) return 202; 
		Vector<Assignatura> c = correquisits.get(a);
		Vector<Assignatura> d = correquisits.get(b);
		if (!cerca(b, c) || !cerca(a, d)) return 201;
		c.indexOf(b); c.remove(b);
		d.indexOf(a); d.remove(a);
		correquisits.put(a, c);
		correquisits.put(b, d);
		return 0;
	}
}
