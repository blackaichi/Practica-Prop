package persistencia.exports;

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
	public String exportaSubGrup(String path, String nomPE, String nomAssig, int numGrup, int numero,
			int places, boolean crea) {
		String endl = "\n";
		String str = "SubGrup".concat(endl);
		str = str.concat(String.valueOf(numero)).concat(endl);
		str = str.concat(String.valueOf(places)).concat(endl);
		str = str.concat(cp.getHoresAptes(path, nomPE, nomAssig, numGrup, numero)).concat(endl);
		str = str.concat(cp.getSolapaments(path, nomPE, nomAssig, numGrup, numero)).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(path, str);
		return str;
	}
}
