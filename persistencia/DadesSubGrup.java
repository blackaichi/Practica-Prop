package persistencia;

import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DadesSubGrup extends ExportaImporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static DadesSubGrup instancia = new DadesSubGrup();
	
	/**
	 * Creadora buida
	 */
	private DadesSubGrup() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static DadesSubGrup getInstancia() {
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
		String str = "SubGrup".concat(endl);
		str = str.concat(String.valueOf(numero)).concat(endl);
		str = str.concat(String.valueOf(places)).concat(endl);
		DadesHoresAptes.getInstancia().exportaHoresAptes(path, horesAptes);
		DadesSolapaments.getInstancia().exportaSolapaments(path, solapaments);
		str = str.concat("END SUBGRUP").concat(endl);
		exporta(path, str, crea);
	}

	public String importaSubGrup(String path) {

		return null;
	}
}
