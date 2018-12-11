package persistencia.exports;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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
	public void exportaSubGrup(String path, int numero, int places, Map<Integer, boolean[]> horesAptes,
			HashMap<String, HashSet<Integer>> solapaments, boolean crea) {
		String endl = "\n";
		String str = "SubGrup".concat(endl);
		str = str.concat(String.valueOf(numero)).concat(endl);
		str = str.concat(String.valueOf(places)).concat(endl);
		str = str.concat(ExportaHoresAptes.getInstancia().exportaHoresAptes(horesAptes)).concat(endl);
		str = str.concat(ExportaSolapaments.getInstancia().exportaSolapaments(solapaments)).concat(endl);
		str = str.concat("END").concat(endl);
		Exporta.exporta(path, str, crea);
	}
}
