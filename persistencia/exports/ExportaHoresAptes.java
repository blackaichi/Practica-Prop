package persistencia.exports;

import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaHoresAptes extends Exporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ExportaHoresAptes instancia = new ExportaHoresAptes();
	
	/**
	 * Creadora buida
	 */
	private ExportaHoresAptes() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ExportaHoresAptes getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta unes HoresAptes
	 * @param ha hores aptes que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació de les HoresAptes
	 */
	public String exportaHoresAptes(String path, Map<Integer, boolean[]> franja, boolean crea) {
		String endl = "\n";
		String str = "HoresAptes".concat(endl);
		boolean[] b;
		for (int i = 0; i < 7; ++i) {
			b = franja.get(i);
			str = str.concat(String.valueOf(i)).concat("(");
			if (b == null) str = str.concat("null");
			else {
				for (boolean p : b) {
					if (p) str = str.concat("t");
					else str = str.concat("f");
				}
			}
			str = str.concat(")");
		}
		str = str.concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(path, str);
		return str;
	}
}
