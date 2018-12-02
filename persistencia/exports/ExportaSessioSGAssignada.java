package persistencia.exports;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaSessioSGAssignada extends Exporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ExportaSessioSGAssignada instancia = new ExportaSessioSGAssignada();
	
	/**
	 * Creadora buida
	 */
	private ExportaSessioSGAssignada() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ExportaSessioSGAssignada getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta una SessioSGAssignada
	 * @param ssga sessió de subgrup assignada que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació de la SessioSGAssignada
	 */
	public String exportaSessioSGAssignada(SessioSGAssignada ssga, boolean crea) {
		String endl = "\n";
		String str = "SessioSGAssignada".concat(endl);
		ExportaSubGrup esg = ExportaSubGrup.getInstancia();
		ExportaSessioSubGrup essg = ExportaSessioSubGrup.getInstancia();
		str = str.concat(esg.exportaSubGrup(ssga.getSubGrup(), false)).concat(endl);
		str = str.concat(essg.exportaSessioSubGrup(ssga.getSessioSubGrup(), false)).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
