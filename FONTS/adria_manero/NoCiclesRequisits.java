package restriccions;
import java.util.*;

import classes.*;
import utils.ExceptionManager;

/**
 * 
 * @author adria.manero@est.fib.upc.edu
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
	public boolean treuRequisit(Assignatura assig) {
		if(this.requisits.contains(assig)) {
			this.requisits.remove(assig);
			return true;
		}
		return false; //TODO: posar numero
	}
	
	/**
	 * Assigna la assignatura que volem comprovar
	 * @param assig: Assignatura que volem comprovar.
	 * @return Execpció en forma d'enter.
	 */
	public int setAssig(Assignatura assig) {
		if (assig == null) return -1;
		this.assignatura = assig;
		return 0;
	}

	/**
	 * Retorna cert si hi ha un cicle de requisits
	 * @param assig_t: Assignatura que volem comprobar el cicle.
	 * @param assig_t: Assignatura que volem comprobar el cicle.
	 * @return Execpció en forma d'enter.
	 * @throws Exception 
	 */
	public boolean isReachable(Assignatura assig_t, Assignatura assig_f ) throws Exception {
		if (assig_t.esIgual(assig_f)) {
	        return true;
	    }
	    for (Assignatura nxt : requisits) {
	        
		    	if (isReachable(assig_t,nxt)) {
		            return true;
		        }
	    }
	    return false;
	}
	
	public int quantsRequisits() {
		return this.requisits.size();
	}
	
	public Assignatura getAssignatura() {
		return this.assignatura;
	}
	
}
	

