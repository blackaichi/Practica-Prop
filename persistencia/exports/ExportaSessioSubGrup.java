package persistencia.exports;

import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaSessioSubGrup extends Exporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ExportaSessioSubGrup instancia = new ExportaSessioSubGrup();
	
	/**
	 * Creadora buida
	 */
	private ExportaSessioSubGrup() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ExportaSessioSubGrup getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta una SessioSubGrup
	 * @param ssg sessió de sub grup que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació de la SessioSubGrup
	 */
	public void exportaSessioSubGrup(String path, HashSet<String> equip, int hores,
			String tipus, int nsessions, int[] nsubgrups, boolean crea) {
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
		if (nsubgrups.length == 0) str = str.concat("none");
		for (int n : nsubgrups) {
			if (first) first = false;
			else str = str.concat(",");
			str = str.concat(String.valueOf(n));
		}
		str = str.concat("END").concat(endl);
		Exporta.exporta(path, str, crea);
	}
}
