package restriccions;

import java.util.*;
import classes.*;
import utils.*;

/**
 * 
 * @author hector.morales.carnice@est.fib.upc.edu
 *
 */
public class SolapamentsGrupSubGrup {
	/**
	 * Enmagatzem per cadascuna de les assignatures amb
	 * quins grups/subgrups no és pot solapar.
	 */
	private Map<String, HashSet<Integer>> disjuntesGlobals;
	
	/**
	 * Linka la classe amb el grup o subgrup corresponent.
	 * Com és obvi, si un linka, l'altre a de ser null;
	 */
	private Grup grup;
	private SubGrup subGrups;
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////
	/**
	 * Assigna el grup o subgrup del qual es excepció.
	 * @param linker Referencia el Grup o subGrup a linkar.
	 * @return Excepció codificada en forma d'enter.
	 */
	private int linker(Grup grup, SubGrup subGrup) {
		if(grup == null && subGrup == null) return 253; //S'ha d'assignar a un grup o subgup.
		else if(grup != null && subGrup != null) return 254; //Només es pot assignar un d'ambdos.
		else if(grup != null) this.grup = grup;
		else this.subGrups = subGrup;
		
		return 0;
	}
	
	/**
	 * Comprova l'estat dels parametres.
	 * @param grup Referencia a un Grup
	 * @param subGrup Referencia a un SubGrup
	 * @return Excepció codificada en forma d'enter.
	 */
	private int checkStatus(Grup grup, SubGrup subGrup) {
		if(grup == null && subGrup == null) return 253;
		else if(grup != null && this.grup != null && this.grup.equals(grup)) return 251;
		else if(subGrup != null && this.subGrups != null && this.subGrups.equals(subGrup)) return 252;
		
		return 0;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/**
	 * Constructora de la classe NoSolaparGrup.
	 */
	public SolapamentsGrupSubGrup(Grup grup, SubGrup subGrup) throws Exception {
		ExceptionManager.thrower(this.linker(grup, subGrup));
		this.disjuntesGlobals = new HashMap<>();
	}
 	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  MODIFICADORES  /////////////////////////////////
	/**
	 * Impedeix o permet, segons l'estat d'apte, que el grup o subGrup corresponent
	 * es pugui solapar amb el passat per parametre. I viceversa.
	 * @param grup Referencia al grup amb el qual no es pot solapar.
	 * @param subGrup Referencia al subGrup al qual no es pot solapar.
	 * @param permet Indica si s'ha de restringir o permetre.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int setSolapament(Grup grup, SubGrup subGrup, boolean permet) {
		int checker;
		if((checker = this.checkStatus(grup, subGrup)) != 0) return checker;
		
		HashSet<Integer> disjuntesActuals = new HashSet<>();
		if(grup != null) { //En cas d'haver entrat un GRUP:
			//Afegir un element que ja conté es com no fer res; al igual que eliminar-ne un que no hi es:
			boolean contains = this.disjuntesGlobals.get(grup.getAssignatura().getNom()).contains(grup.getNumero());
			if((!permet && contains) || (permet && !contains)) return 0;
			
			disjuntesActuals.addAll(this.disjuntesGlobals.get(grup.getAssignatura().getNom()));
			if(!permet) disjuntesActuals.add(grup.getNumero());
			else disjuntesActuals.removeIf(item -> item == grup.getNumero());

			//Si A no pot solapar amb B, es evident que B no pot solapar amb A
			if((checker = grup.setSolapament(grup, subGrup, permet)) != 0) return checker;
			else this.disjuntesGlobals.put(grup.getAssignatura().getNom(), disjuntesActuals);
		}
	
		if(subGrup != null) { //En cas d'haver entrat un SUBGRUP:
			//Afegir un element que ja conté es com no fer res; al igual que eliminar-ne un que no hi es:
			boolean contains = this.disjuntesGlobals.get(subGrup.getGrup().getAssignatura().getNom()).contains(subGrup.getNumero());
			if((!permet && contains) || (permet && !contains)) return 0;
			
			disjuntesActuals.addAll(this.disjuntesGlobals.get(subGrup.getGrup().getAssignatura().getNom()));
			if(!permet) disjuntesActuals.add(subGrup.getNumero());
			else disjuntesActuals.removeIf(item -> item == subGrup.getNumero());
			
			//Si A no pot solapar amb B, es evident que B no pot solapar amb A
			if((checker = subGrup.setSolapament(grup, subGrup, permet)) != 0) return checker;
			else this.disjuntesGlobals.put(subGrup.getGrup().getAssignatura().getNom(), disjuntesActuals);
		}
		
		return 0;
	}

	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  CONSULTORES  ///////////////////////////////////
	/**
	 * Retorna 0 si, i només si, el grup o subgrup corresponent es pot
	 * solapar amb el grup o subGrup passats per parametre; Altrament
	 * retorna 1. No obstant, qualsevol altre enter representa una excepció. 
	 * @param grup Referencia al grup a comprovar.
	 * @param subGrup Referencia al subGrup a comprovar.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int checkPotSolapar(Grup grup, SubGrup subGrup) {
		if(grup == null && subGrup == null) return 253; //S'ha d'assignar un Grup o Subgrup.
		else if(grup != null && subGrup != null) return 255; //Només es pot comprovar d'un en un.
		
		//Comprovem si estan restringits:
		if((grup != null && this.disjuntesGlobals.get(grup.getAssignatura().getNom()).contains(grup.getNumero())) ||
		   (subGrup != null && this.disjuntesGlobals.get(subGrup.getGrup().getAssignatura().getNom()).contains(subGrup.getNumero())))
			return 1;
		
		return 0;
	}
}