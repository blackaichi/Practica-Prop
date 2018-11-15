package restriccions;

import classes.*;
import utils.*;

/**
 * 
 * @author adria.manero@est.fib.upc.edu
 *
 */

public class HoresSenseClasseAssignatura {
	/**
	 * En definitiva, conté les hores lectives del pla
	 * d'estudis. Es evident que no es pot ni prohibir ni
	 * assigna hores a una assignatura fora de les hores lectives.
	 */
	private boolean[][] mascara;
	/**
	 * Marca amb bool aquelles hores en les que la assignatura
	 * té "permís" per fer hores.
	 */
	private boolean[][] horesDisponibles;
	
	/**
	 * Referencia la assignatura al qual es restringeix.
	 */
	private Assignatura assig;
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////
	/**
	 * Assigna la assignatura a la qual s'ha d'aplicar la restricció.
	 * @param assignatura Referencia a la assignatura.
	 * @return Excepció codificada en forma d'enter.
	 */
	private int setAssignatura(Assignatura assig) {
		if(assig == null) return 0;
		
		this.assig = assig;
		return 0;
	}
	
	/**
	 * Assigna la mascara segons el pla d'estudis del assignatura.
	 * @return Excepció codificada en forma d'enter.
	 */
	private int setMascara() {
		if(this.assig == null) return 0; 
		
		for(int d = 0; d < 7; d++)
			this.mascara[d] = this.assig.getPlaEstudis().getFranjaDia(d);
		
		this.horesDisponibles = clone(mascara); //inicialment son iguals;
		return 0;
	}
	
	/**
	 * Vigila que cap de les hores entradas no sigui incongruent.
	 * @param hores Conjunt d'hores a revisar.
	 * @return True si no violen les hores, false altrament.
	 */
	private int checkDiaIHores(int dia, int[] hores) {
		if(hores == null) return 0; 
		else if(dia < 0 && dia > 6) return 0;
		else for(int hora: hores)
			if(hora < 0 || hora > 23) return 0; 
		
		return 0;
	}
	
	/**
	 * Permet o nega fer classes per una assignatura en unes hores i dia concrets.
	 * @param permet Indica si l'ha de permetre, o be negarla.
	 * @param force Permet que, en cas d'entrar una franja parcialment 
	 * incorrecte, la part correcta si que s'apliqui; obviant la resta.
	 * @param dia Indica sobre quin dia aplicar la restricció.
	 * @param hores Conjunt d'hores a les que aplicar la restricció.
	 * @return Execpció en forma d'enter.
	 */
	private int assigna(boolean permet, boolean force, int dia, int ...hores) {
		int checker = this.checkDiaIHores(dia, hores);
		if(checker != 0) return checker;
		
		boolean[][] reboke = clone(this.horesDisponibles);
		for(int hora: hores)
			if(!this.mascara[dia][hora] && !force) {
				this.horesDisponibles = clone(reboke);
				return 0; //TODO: Alguna de les hores viola la franja del pla d'estudis.
			}
			else if(this.mascara[dia][hora]) this.horesDisponibles[dia][hora] = permet;
		
		return 0;
	}
	
	/**
	 * Clona el contingut d'un array de booleans.
	 * @param toClone Array a clonar.
	 * @return Array clonat.
	 */
	static public boolean[][] clone(boolean[][] toClone){
		if(toClone == null) return null;
		
		boolean[][] cloned = new boolean[toClone.length][toClone[0].length];
		for(int i = 0; i < toClone.length; i++)
			for(int j = 0; j < toClone[0].length; j++)
				cloned[i][j] = toClone[i][j];
		
		return cloned;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/**
	 * Constructora de la restricció:
	 * Hores on no es pot fer classe per assignatura
	 * @param assig: Referencia la assignatura a la qual es vol restringir les hores lectives.
	 */
	public HoresSenseClasseAssignatura(Assignatura assig) throws Exception {
		ExceptionManager.thrower(this.setAssignatura(assig));
		
		this.mascara = new boolean[7][24];
		ExceptionManager.thrower(this.setMascara());
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  MODIFICADORES  /////////////////////////////////
	/**
	 * Actualitza hores disponibles de la assignatura per tal de restringir
	 * les hores entrades per parametre.
	 * @param force Permet que, en cas d'entrar una franja parcialment 
	 * incorrecte, la part correcta si que s'apliqui; obviant la resta.
	 * @param dia indica sobre quin dia aplicar la restricció
	 * @param hores Conjunt d'hores que es volen restringir.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int prohibirHores(boolean force, int dia, int ...hores) {
		return this.assigna(false, force, dia, hores);
	}
	
	/**
	 * Reautoritza la assignatura a tenir classe a les hores indicades. 
	 * @param force Permet que, en cas d'entrar una franja parcialment 
	 * incorrecte, la part correcta si que s'apliqui; obviant la resta.
	 * @param dia Indica a quin dia aplicar la franja.
	 * @param hores Conjunt d'hores que es volen restringir.
	 * @return
	 */
	public int permetHores(boolean force, int dia, int ...hores) {
		return this.assigna(true, force, dia, hores);
	}
	
	/**
	 * Actualitza la franja d'hores disponibles per l'assignatura novament a
	 * les hores lectives que te assignades el pla d'estudis.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int restore() {
		return setMascara();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  CONSULTORES  ///////////////////////////////////
	/**
	 * Retorna 0 si, i només si, en aquella hora per aquell dia l'assignatura te permés
	 * fer classe; altramnt, si retorna 1 es que no pot. Finalment si retorna un
	 * altre numero s'ha produit una excepció que cal processar.
	 * @param dia Indica el dia de la classe.
	 * @param hora Indica l'hora de la classe en el dia corresponent.
	 * @return retorna un enter superior o igual a 0.
	 */
	public int checkPotFerClasse(int dia, int hora) {
		int[] hores = new int[1];
		hores[0] = hora;
		
		int checker = this.checkDiaIHores(dia, hores);
		if(checker != 0) return checker;
		
		if(this.horesDisponibles[dia][hora]) return 0;
		else return 1;
	}

	/**
	 * Retorna la matriu de booleans que descriu les hores disponibles
	 * en aquesta restricció.
	 * @return Una matriu de booleans de 7x24.
	 */
	public boolean[][] getHoresAptes(){
		return this.horesDisponibles;
	}
}