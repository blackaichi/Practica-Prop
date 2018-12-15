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

public class DadesSubGrup extends ExportaImporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static DadesSubGrup instancia = new DadesSubGrup();
	
	/**
	 * Creadora buida
	 */
	private DadesSubGrup() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static DadesSubGrup getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta un subgrup
	 * @param path path del fitxer que volem 
	 * @param numero numero del subgrup
	 * @param places places del subgrup
	 * @param horesAptes hores aptes del subgrup
	 * @param solapaments solapaments dels subgrups
	 * @param crea true si es vol truncar el fitxer, false si es vol fer un append
	 */
	public void exportaSubGrup(String path, int numero, int places, Map<Integer, boolean[]> horesAptes,
			HashMap<String, HashSet<Integer>> solapaments, boolean crea) {
		String str = "SubGrup".concat(endl);
		str = str.concat(String.valueOf(numero)).concat(endl);
		str = str.concat(String.valueOf(places)).concat(endl);
		DadesHoresAptes.getInstancia().exportaHoresAptes(path, horesAptes);
		DadesSolapaments.getInstancia().exportaSolapaments(path, solapaments);
		str = str.concat("END SUBGRUP").concat(endl);
		exporta(path, str, crea);
	}

	/**
	 * Importa un subgrup
	 * @param path path del fitxer que volem 
	 * @param nomPE nom del pla d'estudis al qual pertany el subgrup
	 * @param nomA nom de l'assignatura a la qual pertany el subgrup
	 * @param grup nom del grup al qual pertany el grup
	 * @param f llista amb el que hi havia al fitxer
	 * @return null en cas de estar correcte, sinó l'error
	 */
	public String importaSubGrup(String path, String nomPE, String nomA, int grup, List<String> f) {
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
			List<String> aux = new ArrayList<String>();
			int i = 0;
			int numero, places;
			String error;
			if (!f.get(i).equals("SubGrup")) return "no conte un subgrup el fitxer";
			while (i < f.size() && f.get(i++).equals("SubGrup")) {
				if (f.contains("END SUBGRUP") && f.contains("SubGrup")) 
					aux = f.subList(f.indexOf("SubGrup"), f.indexOf("END SUBGRUP"));
				else return "error al grup";
				if (i + 2 > f.size()) return "error llargada del subgrup";
				numero = Integer.parseInt(f.get(i++));
				places = Integer.parseInt(f.get(i++));
				if (!f.get(i++).equals("END SUBGRUP")) return "error en acabar fitxer grup";
				if ((error = cp.creaSubGrupImportat(nomPE, nomA, grup, numero, places)) != null) return error;
				List<String> entry;
				if (aux.contains("Solapaments") && aux.contains("END SOLAP")) {
					entry = aux.subList(aux.indexOf("Solapaments"), aux.lastIndexOf("END SOLAP")+1);
					if ((error = DadesSolapaments.getInstancia().importaSolapaments(nomPE, nomA, grup, numero, entry)) != null) {
						cp.eliminaAssignatura(nomPE, nomA);
						return error;
					}
				}
				else return "error no conte solapaments";
				if (aux.contains("HoresAptes") && aux.contains("END HA")) {
					entry = aux.subList(aux.indexOf("HoresAptes"), aux.lastIndexOf("END HA")+1);
					if ((error = DadesHoresAptes.getInstancia().importaHoresAptes(nomPE, nomA, grup, numero, entry)) != null) {
						cp.eliminaAssignatura(nomPE, nomA);
						return error;
					}
				}
				f = f.subList(aux.indexOf("END ASSIG")+1, f.size()-1);
				i = 0;
			}
			return null;			
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
