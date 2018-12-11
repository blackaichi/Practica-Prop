package persistencia.exports;

import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaCampus extends Exporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ExportaCampus instancia = new ExportaCampus();
	
	/**
	 * Creadora buida
	 */
	private ExportaCampus() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ExportaCampus getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta un Campus
	 * @param c campus que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació del Campus
	 */
	public void exportaCampus(String path, String nom, String autor, HashSet<String> aules) {
		String str = "Campus".concat(endl);
		str = str.concat(nom.concat(endl));
		str = str.concat(autor.concat(endl));
		Exporta.exporta(path, str, true);
		for (String s : aules) {
			cp.getAula(path, nom, s);
		}
		Exporta.exporta(path, "END CAMPUS".concat(endl), false);
	}
}
