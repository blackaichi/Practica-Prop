package persistencia.exports;

import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaSessioGrup extends Exporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ExportaSessioGrup instancia = new ExportaSessioGrup();
	
	/**
	 * Creadora buida
	 */
	private ExportaSessioGrup() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ExportaSessioGrup getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta una SessioGrup
	 * @param sg sessió de grup que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació de la SessioGrup
	 */
	public String exportaSessioGrup(String path, String nomPE, String nomAssig, HashSet<String> equip,
			int hores, String tipus, int nsessions, int[] ngrups, boolean crea) {
		String endl = "\n";
		String str = "SessioGrup".concat(endl);
		boolean first = true;  
		if (equip.isEmpty()) str = str.concat("noequip");
		for (String s : equip) {
			if (first) first = false;
			else str = str.concat(",");
			str = str.concat(s);
		}
		str = str.concat(endl);
		str = str.concat(String.valueOf(hores)).concat(endl);
		str = str.concat(tipus).concat(endl);
		str = str.concat(String.valueOf(nsessions)).concat(endl);
		first = true;  
		if (ngrups.length == 0) str = str.concat("none");
		for (int n : ngrups) {
			if (first) first = false;
			else str = str.concat(",");
			str = str.concat(String.valueOf(n));
		}
		str = str.concat("END");
		if (crea) Exporta.exporta(path, str);
		return str;
	}
}
