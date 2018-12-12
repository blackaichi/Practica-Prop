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
			if (!f.get(i).equals("Grup")) return "no conte un grup el fitxer";
			while (i < f.size() && f.get(i++).equals("Grup")) {
				if (i + 3 > f.size()) return "error llargada del grup";
				int numero, places;
				String franja;
				String error;
				numero = Integer.parseInt(f.get(i++));
				places = Integer.parseInt(f.get(i++));
				franja = f.get(i++);
				if (!f.get(i++).equals("END GRUP")) return "error en acabar fitxer grup";
				if ((error = cp.creaGrupImportat(nomPE, nomA, numero, places, franja)) != null) return error;
			}
			return null;			
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
