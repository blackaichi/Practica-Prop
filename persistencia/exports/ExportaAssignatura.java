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
	public void exportaAssignatura(String path, String nomPE, String nomAssig, HashSet<Pair<String,Integer>> sessionsg,
			HashSet<Pair<String,Integer>> sessionssg, HashSet<Integer> grups, Map<Integer, boolean[]> horesAptes,
			HashMap<String, HashSet<Integer>> solapaments, boolean crea) {
		String str = "Assignatura".concat(endl);
		str = str.concat(nomAssig.concat(endl));
		Exporta.exporta(path, str, crea);
		for (Pair<String,Integer> s : sessionsg) {
			cp.getSessionsG(path, nomPE, nomAssig, s.first, s.second);
		}
		for (Pair<String,Integer> s : sessionssg) {
			cp.getSessionsSG(path, nomPE, nomAssig, s.first, s.second);
		}
		for (int g : grups) {
			cp.getGrups(path, nomPE, nomAssig, g);
		}
		ExportaHoresAptes.getInstancia().exportaHoresAptes(horesAptes);
		ExportaSolapaments.getInstancia().exportaSolapaments(solapaments);
		Exporta.exporta(path, "END ASSIG".concat(endl), crea);
	}
}
