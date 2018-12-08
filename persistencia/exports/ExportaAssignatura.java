package persistencia.exports;

import java.util.*;
import utils.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaAssignatura extends Exporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ExportaAssignatura instancia = new ExportaAssignatura();
	
	/**
	 * Creadora buida
	 */
	private ExportaAssignatura() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ExportaAssignatura getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta una Assignatura
	 * @param a assignatura que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació de l'Assignatura
	 */
	public String exportaAssignatura(String path, String nomPE, String nomAssig, HashSet<Pair<String,Integer>> sessionsg,
			HashSet<Pair<String,Integer>> sessionssg, int[] grups, boolean crea) {
		String endl = "\n";
		String str = "Assignatura".concat(endl);
		str = str.concat(nomAssig.concat(endl));
		for (Pair<String,Integer> s : sessionsg) {
			str = str.concat(cp.getSessionsG(path, nomPE, nomAssig, s.first, s.second)).concat(endl);
		}
		for (Pair<String,Integer> s : sessionssg) {
			str = str.concat(cp.getSessionsSG(path, nomPE, nomAssig, s.first, s.second)).concat(endl);
		}
		for (int g : grups) {
			str = str.concat(cp.getGrups(path, nomPE, nomAssig, g)).concat(endl);
		}
		str = str.concat(cp.getHoresAptes(path, nomPE, nomAssig)).concat(endl);
		str = str.concat(cp.getSolapaments(path, nomPE, nomAssig)).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(path, str);
		return str;
	}
	
}
