package persistencia.exports;

import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaAula extends Exporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ExportaAula instancia = new ExportaAula();
	
	/**
	 * Creadora buida
	 */
	private ExportaAula() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ExportaAula getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta un Aula
	 * @param a aula que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació de l'aula
	 */
	public void exportaAula(String path, String nomAula, int capacitat, HashSet<String> equip, boolean crea) {
		String str = "Aula".concat(endl);
		str = str.concat(nomAula.concat(endl));
		str = str.concat(String.valueOf(capacitat)).concat(endl);
		boolean first = true;  
		if (equip.isEmpty()) str = str.concat("noequip");
		for (String s : equip) {
			if (first) first = false;
			else str = str.concat(",");
			str = str.concat(s);
		}
		str = str.concat(endl).concat("END AULA").concat(endl);
		Exporta.exporta(path, str, crea);
	}
}
