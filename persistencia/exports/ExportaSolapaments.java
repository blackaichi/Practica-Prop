package persistencia.exports;

import domini.restriccions.*;

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
	public String exportaSolapaments(Solapaments s, boolean crea) {
		String endl = "\n";
		String str = "Solapaments".concat(endl);
		
		
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
