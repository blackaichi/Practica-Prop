package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DadesGrup extends ExportaImporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static DadesGrup instancia = new DadesGrup();
	
	/**
	 * Creadora buida
	 */
	private DadesGrup() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static DadesGrup getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta un Grup
	 * @param g grup que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació del Grup
	 */
	public void exportaGrup(String path, String nomPE, String nomAssig, int numero, int places, 
			String franja, HashSet<Integer> numsg, Map<Integer, boolean[]> horesAptes,
			HashMap<String, HashSet<Integer>> solapaments, boolean crea) {
		String str = "Grup".concat(endl);
		str = str.concat(String.valueOf(numero)).concat(endl);
		str = str.concat(String.valueOf(places)).concat(endl);
		str = str.concat(franja).concat(endl);
		exporta(path, str, crea);
		for (int sg : numsg) {
			cp.getSubGrup(path, nomPE, nomAssig, numero, sg);
		}
		DadesHoresAptes.getInstancia().exportaHoresAptes(path, horesAptes);
		DadesSolapaments.getInstancia().exportaSolapaments(path, solapaments);
		exporta(path, "END GRUP".concat(endl), false);
	}

	public String importaGrup(String path, String nomPE, String nomA, List<String> f) {
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
			if (!f.get(i).equals("Grup")) return "no conte un grup el fitxer";
			while (!f.isEmpty() && f.get(i++).equals("Grup")) {
				if (f.contains("END GRUP") && f.contains("Grup")) 
					aux = f.subList(f.indexOf("Grup"), f.indexOf("END GRUP")+1);
				else return "error al grup";
				if (i + 3 > f.size()) return "error llargada del grup";
				int numero, places;
				String franja;
				String error;
				numero = Integer.parseInt(f.get(i++));
				places = Integer.parseInt(f.get(i++));
				franja = f.get(i++);
				List<String> entry;
				if (aux.contains("HoresAptes") && aux.contains("END HA")) {
					entry = aux.subList(aux.indexOf("HoresAptes"), aux.indexOf("END HA")+1);
					if ((error = DadesHoresAptes.getInstancia().importaHoresAptes(nomPE, nomA, numero, -1, entry)) != null) {
						cp.eliminaAssignatura(nomPE, nomA);
						return error;
					}
				}
				else return "no conte hores aptes";
				if (aux.contains("Solapaments") && aux.contains("END SOLAP")) {
					entry = aux.subList(aux.indexOf("Solapaments"), aux.lastIndexOf("END SOLAP")+1);
					if ((error = DadesSolapaments.getInstancia().importaSolapaments(nomPE, nomA, numero, -1, entry)) != null) {
						cp.eliminaAssignatura(nomPE, nomA);
						return error;
					}
				}
				else return "error no conte solapaments";
				if (!aux.get(aux.size()-1).equals("END GRUP")) return "error en acabar fitxer grup";
				if ((error = cp.creaGrupImportat(nomPE, nomA, numero, places, franja)) != null) return error;
				if (aux.contains("SubGrup") && aux.contains("END SUBGRUP")) {
					if (aux.indexOf("SubGrup") == -1 || aux.lastIndexOf("END SUBGRUP") == -1) 
						return "Error a la part de grup";
					entry = aux.subList(aux.indexOf("Grup"), aux.lastIndexOf("END GRUP")+1);
					if ((error = DadesSubGrup.getInstancia().importaSubGrup(path, nomPE, nomA, numero, entry)) != null) {
						cp.eliminaGrup(nomPE, nomA, numero);
						return error;
					}
				}
				f = f.subList(aux.indexOf("END GRUP")+1, f.size());
				i = 0;
			}
			return null;			
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
