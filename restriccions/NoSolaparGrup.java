package restriccions;

import classes.*;
import java.util.*;

public class NoSolaparGrup extends Unaria {
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
	public int linker(Grup grup, SubGrup subGrup) {
		if(grup == null && subGrup == null) return -1; //TODO: S'ha d'assignar a un grup o subgup.
		else if(grup != null && subGrup != null) return -1; //TODO: Només es pot assignar un d'ambdos.
		else if(grup != null) this.grup = grup;
		else this.subGrups = subGrup;
		
		return 0;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/**
	 * Constructora de la classe NoSolaparGrup.
	 */
	public NoSolaparGrup(Grup grup, SubGrup subgrup) throws Exception {
		
	}
 	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  MODIFICADORES  /////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  CONSULTORES  ///////////////////////////////////
}