package persistencia.exports;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaSessioGAssignada extends Exporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ExportaSessioGAssignada instancia = new ExportaSessioGAssignada();
	
	/**
	 * Creadora buida
	 */
	private ExportaSessioGAssignada() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ExportaSessioGAssignada getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta una SessioGAssignada
	 * @param sga sessió de grup assignada que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació de la SessioGAssignada
	 */
	public String exportaSessioGAssignada(SessioGAssignada sga, boolean crea) {
		String endl = "\n";
		String str = "SessioGAssignada".concat(endl);
		ExportaGrup eg = ExportaGrup.getInstancia();
		ExportaSessioGrup esg = ExportaSessioGrup.getInstancia();
		str = str.concat(eg.exportaGrup(sga.getGrup(), false)).concat(endl);
		str = str.concat(esg.exportaSessioGrup(sga.getSessioGrup(), false)).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
