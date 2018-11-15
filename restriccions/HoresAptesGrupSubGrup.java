package restriccions;

import java.util.*;
import classes.*;
import utils.ExceptionManager;

/**
 * 
 * @author hector.morales.carnice@est.fib.ipc.edu
 *
 */
public class HoresAptesGrupSubGrup {
	/**
	 * En definitiva, conté les hores lectives del pla
	 * d'estudis. Es evident que no es pot ni prohibir ni
	 * assigna hores a un grup fora de les hores lectives.
	 */
	private Map<Integer, boolean[]> mascara;
	/**
	 * Marca amb bool aquelles hores en les que el grup
	 * té "permís" per fer hores.
	 */
	private Map<Integer, boolean[]> horesDisponibles;
	
	/**
	 * Referencia el grup o subGrup al qual es restringeix.
	 * Com és logic, si una linka, l'altre ha de ser null.
	 */
	private Grup grup;
	private SubGrup subGrup;
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////
	/**
	 * Assigna el grup o subgrup del qual es excepció.
	 * @param linker Referencia el Grup o subGrup a linkar.
	 * @return Excepció codificada en forma d'enter.
	 */
	private int linker(Grup grup, SubGrup subGrup) {
		if(grup == null && subGrup == null) return -1; //TODO: S'ha d'assignar a un grup o subgup.
		else if(grup != null && subGrup != null) return -1; //TODO: Només es pot assignar un d'ambdos.
		else if(grup != null) this.grup = grup;
		else this.subGrup = subGrup;
		
		return 0;
	}
	
	/**
	 * Vigila que cap de les hores entradas no sigui incongruent.
	 * @param hores Conjunt d'hores a revisar.
	 * @return True si no violen les hores, false altrament.
	 */
	private int checkDiaIHores(int dia, int[] hores) {
		if(hores == null) return 212;
		else if(dia < 0 && dia > 6) return 213;
		else for(int hora: hores)
			if(hora < 0 || hora > 23) return 214;
		
		return 0;
	}
	
	/**
	 * Permet o nega fer classes per un grup en unes hores i dia concrets.
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
		
		Map<Integer, boolean[]> reboke = clone(this.horesDisponibles);
		for(int hora: hores)
			if(!this.mascara.get(dia)[hora] && !force) {
				this.horesDisponibles = clone(reboke);
				return 215;
			}
			else if(this.mascara.get(dia)[hora]) this.horesDisponibles.get(dia)[hora] = permet;
		
		return 0;
	}
	
	/**
	 * Clona el contingut d'un array de booleans.
	 * @param toClone Array a clonar.
	 * @return Array clonat.
	 */
	static public Map<Integer, boolean[]> clone(Map<Integer, boolean[]> toClone){
		if(toClone == null) return null;
		
		Map<Integer, boolean[]> cloned = new HashMap<>();
		for(int dia: toClone.keySet())
			cloned.put(dia, Arrays.copyOf(toClone.get(dia), toClone.get(dia).length));
		
		return cloned;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/**
	 * Constructora de la restricció:
	 * Hores on no es pot fer classe per grup
	 * @param grup Referencia al Grup al qual es vol restringir les hores lectives.
	 */
	public HoresAptesGrupSubGrup(Grup grup, SubGrup subGrup) throws Exception {
		ExceptionManager.thrower(this.linker(grup, subGrup));
		ExceptionManager.thrower(this.setMascara(true));
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  SETTERS  /////////////////////////////////////
	/**
	 * Assigna la mascara segons el pla d'estudis del grup o subGrup.
	 * @param cascade Indica si, en configurar la mascara, es volen igualar
	 * les horesDisponibles amb la mascara, o bé és vol conservar la configuració
	 * present d'aquestes horesDisponibles.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int setMascara(boolean cascade) {
		if(this.grup == null && this.subGrup == null) return 211;
		
		for(int dia = 0; dia < 7; dia++) {
			if(this.grup != null) this.mascara = clone(this.grup.getAssignatura().getPlaEstudis().getFranjaSetmana());
			else this.mascara = clone(this.subGrup.getGrup().getAssignatura().getPlaEstudis().getFranjaSetmana());
		}
		
		if(cascade) this.horesDisponibles = clone(mascara); //inicialment son iguals;
		return 0;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  GETTERS  /////////////////////////////////////
	/**
	 * Retorna la mascara de la restricció.
	 * @return una matriu de booleans.
	 */
	public Map<Integer, boolean[]> getMascara(){
		return this.mascara;
	}
	
	/**
	 * Retorna la matriu de booleans que descriu les hores disponibles
	 * en aquesta restricció.
	 * @return Una matriu de booleans de 7x24.
	 */
	public Map<Integer, boolean[]> getHoresAptes(){
		return this.horesDisponibles;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  MODIFICADORES  /////////////////////////////////
	/**
	 * Actualitza hores disponibles del grup per tal de restringir
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
	 * Reautoritza el grup a tenir classe a les hores indicades. 
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
	 * Actualitza la franja d'hores disponnibles pel grup novament a
	 * les hores lectives que te assignades el pla d'estudis.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int restore() {
		return setMascara(true);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  CONSULTORES  ///////////////////////////////////
	/**
	 * Retorna 0 si, i només si, en aquella hora per aquell dia el grup te permés
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
		
		if(this.horesDisponibles.get(dia)[hora]) return 0;
		else return 1;
	}
}
