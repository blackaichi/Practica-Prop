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
	public String exportaHorari(String path, Map<Integer, Map<Integer, HashSet<Segment>>> horari,
			HashSet<String> flags, String nomPE, String nomC, boolean crea) {
		String endl = "\n";
		String str = "Horari ".concat(endl);
		for (int dia = 0; dia < 7; ++dia) {
			str = str.concat(String.valueOf(dia)).concat(endl);
			for (int hora = 0; hora < 24; ++hora) {
				str = str.concat(String.valueOf(hora)).concat(endl);
				s = e.getAllSegments(dia, hora);
				if (s.isEmpty()) str = str.concat("buit").concat(endl);
				else {
					for (Segment se : s) str = str.concat(ExportaSegment.getInstancia().exportaSegment(se, false));
				}
			}
		}
		str = str.concat("END").concat(endl);
		Exporta.exporta(path, str, false);
		return str;
	}
}
