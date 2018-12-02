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
	public String exportaHoraris(HashSet<Estructura> e, boolean crea) {
		String endl = "\n";
		String str = "Horaris".concat(endl);
		str = str.concat(String.valueOf(e.size()).concat(endl).concat("{").concat(endl));
		int n = 1;
		for (Estructura a : e) {
			str = str.concat(ExportaEstructura.exportaEstructura(a, n, false));
			++n;
		}
		str = str.concat("}").concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
