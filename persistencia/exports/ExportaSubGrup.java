package persistencia.exports;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaSubGrup extends Exporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ExportaSubGrup instancia = new ExportaSubGrup();
	
	/**
	 * Creadora buida
	 */
	private ExportaSubGrup() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ExportaSubGrup getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta un sub grup
	 * @param sg SubGrup que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació del subgrup
	 */
	public String exportaSubGrup(SubGrup sg, boolean crea) {
		String endl = "\n";
		String str = "SubGrup".concat(endl);
		ExportaHoresAptes eha = ExportaHoresAptes.getInstancia();
		ExportaSolapaments es = ExportaSolapaments.getInstancia();
		str = str.concat(String.valueOf(sg.getNumero())).concat(endl);
		str = str.concat(String.valueOf(sg.getPlaces())).concat(endl);
		str = str.concat(eha.exportaHoresAptes(sg.getRestriccioHoresAptes(), false)).concat(endl);
		str = str.concat(es.exportaSolapaments(sg.getSolapaments(), false)).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
