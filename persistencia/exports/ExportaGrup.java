package persistencia.exports;

import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaGrup extends Exporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ExportaGrup instancia = new ExportaGrup();
	
	/**
	 * Creadora buida
	 */
	private ExportaGrup() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ExportaGrup getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta un Grup
	 * @param g grup que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació del Grup
	 */
	public String exportaGrup(String path, String nomPE, String nomAssig, int numero, int places, 
			String franja, HashSet<Integer> numsg, Map<Integer, boolean[]> horesAptes,
			HashMap<String, HashSet<Integer>> solapaments, boolean crea) {
		String endl = "\n";
		String str = "Grup".concat(endl);
		str = str.concat(String.valueOf(numero)).concat(endl);
		str = str.concat(String.valueOf(places)).concat(endl);
		str = str.concat(franja).concat(endl);
		for (int sg : numsg) {
			str = str.concat(cp.getSubGrup(path, nomPE, nomAssig, numero, sg)).concat(endl);
		}
		str = str.concat(ExportaHoresAptes.getInstancia().exportaHoresAptes(horesAptes)).concat(endl);
		str = str.concat(ExportaSolapaments.getInstancia().exportaSolapaments(solapaments)).concat(endl);
		if (crea) {
			str = str.concat("END");
			Exporta.exporta(path, str);
		}
		str = str.concat(endl);
		return str;
	}
}
