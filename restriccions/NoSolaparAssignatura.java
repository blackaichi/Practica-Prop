package restriccions;

import java.util.*;
import classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class NoSolaparAssignatura {
	/////////////////////////////////////////////////////////////
	//////////////////////// Variables //////////////////////////
	
	/**
	 * Vector on guardarem tots els no solapar
	 */
	private Vector<Assignatura> nosolapaments;
	
	/**
	 * Assignatura amb els no solapar
	 */
	private Assignatura assignatura;
	
	/////////////////////////////////////////////////////////////
	/////////////////////////  Privats  /////////////////////////

	/**
	 * Busca si l'assignatura passada per paràmetre esta dins del vector de nosolapaments
	 * @param a assignatura per buscar si esta dins el vector
	 * @return true si l'assignatura esta dins del vector de no solapaments, false altrament
	 */
	private Boolean cerca(Assignatura a) {
		for (Assignatura d : nosolapaments) 
			if (d.getNom().equals(a.getNom())) return true;
		return false;
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////// Publics ///////////////////////////

	/**
	 * Indica si l'assignatura passada per paràmetre és correquisit
	 * @return 1 si no es poden solapar, 0 si es poden solapar, altrament error 
	 */
	public int checkSolapar(Assignatura a) {
		if (a == null) return 202;
		else if (a.getNom().equals(assignatura.getNom())) return 203;
		else if (cerca(a)) return 1;
		return 0;
	}
	
	/**
	 * Afegir un correquisit al Vector de correqusiits
	 * @param a l'assignatura que volem que sigui correquisit
	 * @return 0 si no hi ha cap error, altrament error
	 */
	public int addNoSolapar(Assignatura a) {
		if (a == null) return 202;
		if (cerca(a)) return 200;
		if (!a.getSolapaments().afegirSolapament(assignatura)) return 203;
		nosolapaments.addElement(a);
		return 0;
	}
	
	/**
	 * Eliminar un no solapament del vector de no solapaments
	 * @param a l'assignatura que volem eliminar de no solapaments
	 * @return 0 si no hi ha cap error, altrament error
	 */
	public int delNoSolapar(Assignatura a) {
		if (a == null) return 202; 
		if (!cerca(a)) return 201;
		nosolapaments.remove(a);
		return 0;
	}
	
	
	private Boolean afegirSolapament(Assignatura a) {
		if (cerca(a)) return false;
		nosolapaments.addElement(a);
		return true;
	}
}
