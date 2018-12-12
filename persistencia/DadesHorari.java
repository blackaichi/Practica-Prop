package persistencia;

import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DadesHorari extends ExportaImporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static DadesHorari instancia = new DadesHorari();
	
	/**
	 * Creadora buida
	 */
	private DadesHorari() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static DadesHorari getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta un Horari
	 * @param e conjunt d'horaris que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació dels Horaris
	 */
	public void exportaHorari(String path, HashSet<String> flags, String nomC, String nomPE, int id) {
		String str = "Horari".concat(endl);
		str = str.concat(nomPE).concat(endl);
		str = str.concat(nomC).concat(endl);
		boolean first = true;
		for (String s : flags) {
			if (first) first = false;
			else str = str.concat(",");
			str = str.concat(s);
		}
		str = str.concat(endl);
		exporta(path, str, true);
		str = "";
		for (int dia = 0; dia < 7; ++dia) {
			exporta(path, String.valueOf(dia).concat(endl), false);
			for (int hora = 0; hora < 24; ++hora) {
				exporta(path, String.valueOf(hora).concat(endl), false);
				cp.getSegment(path, dia, hora, nomC, nomPE, id);
			}
		}
		exporta(path, "END HORARI", false);
	}

	public String importaHoraris(String path) {
		
		return null;
	}
}
