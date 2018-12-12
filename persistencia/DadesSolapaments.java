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
		for (HashMap.Entry<String, HashSet<Integer>> entry : solapaments.entrySet()) {
			str = str.concat(entry.getKey()).concat(" ");
			boolean first = true;
			for (Integer i : entry.getValue()) {
				if (first) first = false;
				else str = str.concat(",");
				str = str.concat(String.valueOf(i));
			}
			str = str.concat(";");
		}
		str = str.concat(endl).concat("END SOLAP").concat(endl);
		exporta(path, str, false);
	}

	public String importaSolapaments(String nomPE, String nomA, List<String> entry) {
		try {
			if (!entry.get(0).equals("Solapaments") || !entry.get(entry.size()-1).equals("END SOLAP") || entry.size() != 3) 
				return "error al solapaments";
			HashMap<String, HashSet<Integer>> solapaments = new HashMap<String, HashSet<Integer>>();
			HashSet<Integer> valor = new HashSet<Integer>();
			String[] sa = entry.get(1).split(";");
			for (String ss : sa) {
				valor.clear();
				String[] sa2 = ss.split(" ");
				if (sa2.length != 2) return "error al solapaments";
				String[] sa3 = sa2[1].split(",");
				for (String ss3 : sa3) valor.add(Integer.valueOf(ss3));
				solapaments.put(sa2[0], valor);
			}
			cp.creaSolapamentAssig(nomPE, nomA, solapaments);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
