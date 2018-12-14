package persistencia;

import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DadesSolapaments extends ExportaImporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static DadesSolapaments instancia = new DadesSolapaments();
	
	/**
	 * Creadora buida
	 */
	private DadesSolapaments() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static DadesSolapaments getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta un solapament
	 * @param s solapament que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació del solapament
	 */
	public void exportaSolapaments(String path, HashMap<String, HashSet<Integer>> solapaments) {
		String str = "Solapaments".concat(endl);
		boolean in = false;
		for (HashMap.Entry<String, HashSet<Integer>> entry : solapaments.entrySet()) {
			in = true;
			str = str.concat(entry.getKey()).concat(" ");
			boolean first = true;
			for (Integer i : entry.getValue()) {
				if (first) first = false;
				else str = str.concat(",");
				str = str.concat(String.valueOf(i));
			}
			str = str.concat(";");
		}
		if (!in) str = str.concat("none");
		str = str.concat(endl);
		str = str.concat("END SOLAP").concat(endl);
		exporta(path, str, false);
	}

	public String importaSolapaments(String nomPE, String nomA, int grup, int subgrup, List<String> entry) {
		try {
			if (!entry.get(0).equals("Solapaments") || !entry.get(entry.size()-1).equals("END SOLAP") ||
					entry.size() != 3)
				return "error al solapaments";
			if (!entry.get(1).equals("none")) {
				HashMap<String, HashSet<Integer>> solapaments = new HashMap<String, HashSet<Integer>>();
				HashSet<Integer> valor = new HashSet<Integer>();
				String[] sa = entry.get(1).split(";");
				for (String ss : sa) {
					valor.clear();
					String[] sa2 = ss.split(" ");
					if (sa2[1] == null) valor.add(null);
					else {
						String[] sa3 = sa2[1].split(",");
						for (String ss3 : sa3) valor.add(Integer.valueOf(ss3));
					}
					solapaments.put(sa2[0], valor);
				}
				String error;
				if ((error = cp.creaSolapament(nomPE, nomA, grup, subgrup, solapaments)) != null) return error;
			}
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
