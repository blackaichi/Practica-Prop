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
		exporta(path, str, crea);
		for (Pair<String,Integer> s : sessionsg) {
			cp.getSessionsG(path, nomPE, nomAssig, s.first, s.second);
		}
		for (Pair<String,Integer> s : sessionssg) {
			cp.getSessionsSG(path, nomPE, nomAssig, s.first, s.second);
		}
		for (int g : grups) {
			cp.getGrups(path, nomPE, nomAssig, g);
		}
		DadesHoresAptes.getInstancia().exportaHoresAptes(path, horesAptes);
		DadesSolapaments.getInstancia().exportaSolapaments(path, solapaments);
		exporta(path, "END ASSIG".concat(endl), crea);
	}
	
	public String importaAssignatura(String path, String nomPE, List<String> f) {
		try {
			if (f == null) {
				String s;
				f = new ArrayList<String>();
				File file = new File(path); 
				BufferedReader br = new BufferedReader(new FileReader(file));
				while ((s = br.readLine()) != null) {
					f.add(s);
				}
				br.close();
			}
			int i = 0;
			if (!f.get(i++).equals("Assignatura")) return "no es un aula el fitxer";
			String nomA;
			nomA = f.get(i++);
			if (!f.get(4).equals("END ASSIG")) return "No finalitza correctament";
			cp.creaAssignaturaImportada(nomPE, nomA);
			List<String> aux;
			if (f.contains("SessioGrup") && f.contains("END SESSIOG")) {
				aux = f.subList(f.indexOf("SessioGrup"), f.lastIndexOf("END SESSIOG"));
				DadesSessioGrup.getInstancia().importaSessioGrup(path, nomPE, nomA, aux);
			}
			if (f.contains("SessioSubGrup") && f.contains("END SESSIOSG")) {
				aux = f.subList(f.indexOf("SessioSubGrup"), f.lastIndexOf("END SESSIOSG"));
				DadesSessioSubGrup.getInstancia().importaSessioSubGrup(path, nomPE, nomA, aux);
			}
			if (f.contains("Grup") && f.contains("END GRUP")) {
				aux = f.subList(f.indexOf("Grup"), f.lastIndexOf("END GRUP"));
				DadesGrup.getInstancia().importaGrup(path, nomPE, nomA, aux);
			}
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
