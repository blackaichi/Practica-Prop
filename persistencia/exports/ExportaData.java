package persistencia.exports;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaData extends Exporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ExportaData instancia = new ExportaData();
	
	/**
	 * Creadora buida
	 */
	private ExportaData() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ExportaData getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta una Data
	 * @param d data que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació de la Data
	 */
	public String exportaData(int dia, int hora) {
		String endl = "\n";
		String str = "Data".concat(endl);
		str = str.concat(String.valueOf(dia)).concat(endl);
		str = str.concat(String.valueOf(hora)).concat(endl);
		str = str.concat("END").concat(endl);
		return str;
	}
}
