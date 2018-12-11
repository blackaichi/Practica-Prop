package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

import utils.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DadesAssignatura extends ExportaImporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static DadesAssignatura instancia = new DadesAssignatura();
	
	/**
	 * Creadora buida
	 */
	private DadesAssignatura() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static DadesAssignatura getInstancia() {
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
		ExportaImporta.exporta(path, str, crea);
		for (Pair<String,Integer> s : sessionsg) {
			cp.getSessionsG(path, nomPE, nomAssig, s.first, s.second);
		}
		for (Pair<String,Integer> s : sessionssg) {
			cp.getSessionsSG(path, nomPE, nomAssig, s.first, s.second);
		}
		for (int g : grups) {
			cp.getGrups(path, nomPE, nomAssig, g);
		}
		DadesHoresAptes.getInstancia().exportaHoresAptes(horesAptes);
		DadesSolapaments.getInstancia().exportaSolapaments(solapaments);
		ExportaImporta.exporta(path, "END ASSIG".concat(endl), crea);
	}
	
	public String importaAssignatura(String path, String nomPE, String assig) {
		try {
			File file = new File(path); 
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String s;
			br.readLine();
			while (br.readLine() != "Assignatura");
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
