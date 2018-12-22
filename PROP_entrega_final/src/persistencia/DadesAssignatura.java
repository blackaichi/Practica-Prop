package persistencia;

import java.io.*;
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
	 * Exporta una assignatura
	 * @param path path del fitxer que volem 
	 * @param nomPE nom del pla d'estudis
	 * @param nomAssig nom de l'assignatura
	 * @param sessionsg nom de la sessió de grup
	 * @param sessionssg nom de la sessió subgrup
	 * @param grups nom dels grups
	 * @param horesAptes les hores aptes del pla
	 * @param solapaments els solapaments del pla
	 * @param crea true si es vol truncar el fitxer, false si es vol fer un append
	 */
	public void exportaAssignatura(String path, String nomPE, String nomAssig, HashSet<Pair<String,Integer>> sessionsg,
			HashSet<Pair<String,Integer>> sessionssg, HashSet<Integer> grups, Map<Integer, boolean[]> horesAptes,
			HashMap<String, HashSet<Integer>> solapaments, boolean crea) {
		String str = "Assignatura".concat(endl);
		str = str.concat(nomAssig.concat(endl));
		exporta(path, str, crea);
		for (int g : grups) {
			cp.getGrups(path, nomPE, nomAssig, g);
		}
		for (Pair<String,Integer> s : sessionsg) {
			cp.getSessionsG(path, nomPE, nomAssig, s.first, s.second);
		}
		for (Pair<String,Integer> s : sessionssg) {
			cp.getSessionsSG(path, nomPE, nomAssig, s.first, s.second);
		}
		DadesHoresAptes.getInstancia().exportaHoresAptes(path, horesAptes);
		DadesSolapaments.getInstancia().exportaSolapaments(path, solapaments);
		exporta(path, "END ASSIG".concat(endl), false);
	}
	
	/**
	 * Importa una assignatura
	 * @param path path del fitxer que volem 
	 * @param nomPE nom del pla d'estudis
	 * @param f llista amb el que hi havia al fitxer
	 * @return null en cas de estar correcte, sinó l'error
	 */
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
			List<String> aux;
			String error;
			while (i < f.size()) {
				if (f.contains("END ASSIG") && f.contains("Assignatura")) 
					aux = f.subList(f.indexOf("Assignatura"), f.indexOf("END ASSIG")+1);
				else return "Error al fitxer d'assignatura";
				if (i + 2 > f.size()) return "error a la assignatura";
				if (!f.get(i++).equals("Assignatura")) return "no es una assignatura el fitxer";
				String nomA;
				nomA = f.get(i++);
				List<String> entry;
				if ((error = cp.creaAssignaturaImportada(nomPE, nomA)) != null) return error;
				if (aux.contains("Grup") && aux.contains("END GRUP")) {
					if (aux.indexOf("Grup") == -1 || aux.lastIndexOf("END GRUP") == -1) 
						return "Error a la part de grup";
					entry = aux.subList(aux.indexOf("Grup"), aux.lastIndexOf("END GRUP")+1);
					if ((error = DadesGrup.getInstancia().importaGrup(path, nomPE, nomA, entry)) != null) {
						cp.eliminaAssignatura(nomPE, nomA);
						return error;
					}
				}
				if (aux.contains("SessioGrup") && aux.contains("END SESSIOG")) {
					entry = aux.subList(aux.indexOf("SessioGrup"), aux.lastIndexOf("END SESSIOG")+1);
					if (aux.indexOf("SessioGrup") == -1 || aux.lastIndexOf("END SESSIOG") == -1) 
						return "Error a la part de sessio grup";
					if ((error = DadesSessioGrup.getInstancia().importaSessioGrup(path, nomPE, nomA, entry)) != null) {
						cp.eliminaAssignatura(nomPE, nomA);
						return error;
					}
				}
				if (aux.contains("SessioSubGrup") && aux.contains("END SESSIOSG")) {
					if (aux.indexOf("SessioSubGrup") == -1 || aux.lastIndexOf("END SESSIOSG") == -1) 
						return "Error a la part de sessio subgrup";
					entry = aux.subList(aux.indexOf("SessioSubGrup"), aux.lastIndexOf("END SESSIOSG")+1);
					if ((error = DadesSessioSubGrup.getInstancia().importaSessioSubGrup(path, nomPE, nomA, entry)) != null) {
						cp.eliminaAssignatura(nomPE, nomA);
						return error;
					}
				}
				if (aux.contains("Solapaments") && aux.contains("END SOLAP")) {
					entry = aux.subList(aux.indexOf("Solapaments"), aux.indexOf("END SOLAP")+1);
					if ((error = DadesSolapaments.getInstancia().importaSolapaments(nomPE, nomA, -1, -1, entry)) != null) {
						cp.eliminaAssignatura(nomPE, nomA);
						return error;
					}
				}
				else return "error no conte solapaments";
				if (aux.contains("HoresAptes") && aux.contains("END HA")) {
					entry = aux.subList(aux.lastIndexOf("HoresAptes"), aux.lastIndexOf("END HA")+1);
					if ((error = DadesHoresAptes.getInstancia().importaHoresAptes(nomPE, nomA, -1, -1, entry)) != null) {
						cp.eliminaAssignatura(nomPE, nomA);
						return error;
					}
				}
				else return "error no conte HoresAptes";
				f = f.subList(f.indexOf("END ASSIG")+1, f.size());
				i = 0;
			}
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
