package restriccions;
import java.util.*;

import classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class NoCiclesRequisits {
	/**
	 * HashSet on guardarem totes les assignatures que no poden solapar-se
	 */
	HashSet<Assignatura> requisits;
	/**
	 * Assignatura que estem tractant
	 */
	private Assignatura assignatura;
	
	/**
	 * Constructora de NoCiclesRequisits
	 * @param assig: Assignatura a la que volem lincar la classe NoCiclesRequisits.
	 */
	public NoCiclesRequisits(Assignatura assig) throws Exception {
		ExceptionManager.thrower(this.setAssig(assig));
		this.requisits = new HashSet<Assignatura>();
	}
	
	/**
	 * Afegeix requisits al HashSet de requisits
	 * @param assig: Assignatura que volem afegir als requisits.
	 * @return Execpció en forma d'enter.
	 */
	public int afegeixRequisits(Assignatura assig) {
		requisits.add(assig);
		return 0;
	}
	
	/**
	 * Treu requisits al HashSet de requisits
	 * @param assig: Assignatura que volem treure dels requisits.
	 * @return Execpció en forma d'enter.
	 */
	public int treuRequisit(Assignatura assig) {
		if(this.requisits.contains(assig)) {
			this.requisits.remove(assig);
			return 0;
		}
		return -1; //TODO: posar numero
	}
	
	/**
	 * Assigna la assignatura que volem comprovar
	 * @param assig: Assignatura que volem comprovar.
	 * @return Execpció en forma d'enter.
	 */
	int setAssig(Assignatura assig) {
		if (assig == null) return -1;
		this.assignatura = assig;
		return 0;
	}

	/**
	 * Retorna cert si hi ha un cicle de requisits
	 * @param assig_t: Assignatura que volem comprobar el cicle.
	 * @param assig_t: Assignatura que volem comprobar el cicle.
	 * @return Execpció en forma d'enter.
	 */
	public boolean isReachable(Assignatura assig_t, Assignatura assig_f ) {
		if (assig_t.equals(assig_f)) {
	        return true;
	    }
	    for (Assignatura nxt : requisits) {
	        
		    	if (isReachable(assig_t,nxt)) {
		            return true;
		        }
	    }
	    return false;
	}
	
}
	

