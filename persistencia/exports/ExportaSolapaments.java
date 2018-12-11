package persistencia.exports;

import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaSolapaments extends Exporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ExportaSolapaments instancia = new ExportaSolapaments();
	
	/**
	 * Creadora buida
	 */
	private ExportaSolapaments() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ExportaSolapaments getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta un solapament
	 * @param s solapament que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació del solapament
	 */
	public void exportaSolapaments(HashMap<String, HashSet<Integer>> solapaments) {
		String str = "Solapaments".concat(endl);
		for (HashMap.Entry<String, HashSet<Integer>> entry : solapaments.entrySet()) {
			str = str.concat(entry.getKey()).concat(" ");
			boolean first = true;
			for (Integer i : entry.getValue()) {
				if (first) first = false;
				else str = str.concat(",").concat(String.valueOf(i));
			}
			str = str.concat(";");
		}
		str = str.concat("END SOLAP").concat(endl);
	}
}
