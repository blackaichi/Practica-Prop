package persistencia.exports;

import java.util.*;
import utils.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaHorari extends Exporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ExportaHorari instancia = new ExportaHorari();
	
	/**
	 * Creadora buida
	 */
	private ExportaHorari() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ExportaHorari getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta un Horari
	 * @param e conjunt d'horaris que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació dels Horaris
	 */
	public String exportaHoraris(String path, Map<Integer, Map<Integer, HashSet<Segment>>> horari,
			HashSet<String> flags, String nomPE, String nomC, boolean crea) {
		String endl = "\n";
		String str = "Horaris".concat(endl);
		
		str = str.concat("END");
		if (crea) Exporta.exporta(path, str);
		return str;
	}
}
