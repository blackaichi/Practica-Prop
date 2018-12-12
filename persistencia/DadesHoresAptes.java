package persistencia;

import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DadesHoresAptes extends ExportaImporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static DadesHoresAptes instancia = new DadesHoresAptes();
	
	/**
	 * Creadora buida
	 */
	private DadesHoresAptes() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static DadesHoresAptes getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta unes HoresAptes
	 * @param ha hores aptes que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació de les HoresAptes
	 */
	public void exportaHoresAptes(String path, Map<Integer, boolean[]> franja) {
		String str = "HoresAptes".concat(endl);
		boolean[] b;
		for (int i = 0; i < 7; ++i) {
			b = franja.get(i);
			str = str.concat(String.valueOf(i)).concat("(");
			if (b == null) str = str.concat("null");
			else {
				for (boolean p : b) {
					if (p) str = str.concat("t");
					else str = str.concat("f");
				}
			}
			str = str.concat(")");
		}
		str = str.concat(endl).concat("END HA").concat(endl);
		exporta(path, str, false);
	}

	public String importaHoresAptes(String nomPE, String nomA, List<String> entry) {
		try {
			if (!entry.get(0).equals("HoresAptes") || !entry.get(entry.size()-1).equals("END HA") || entry.size() != 3) 
				return "error a les hores aptes";
			Map<Integer, boolean[]> ha = new HashMap<Integer, boolean[]>();
			String[] sa = entry.get(1).split(")");
			for (String s : sa) {
				boolean[] b = new boolean[24];
				String[] sa2 = s.split("(");
				if (sa2[1] == "null") ha.put(Integer.parseInt(sa[0]), null);
				else {
					char[] ca = sa2[1].toCharArray();
					for (int i = 0; i < ca.length; ++i) {
						if (ca[i] == 'f') b[i] = false;
						else if (ca[i] == 't') b[i] = true;
						else return "error a les hores aptes";
					}
					ha.put(Integer.parseInt(sa2[0]), b);
				}
			}
			String error;
			if ((error = cp.creaHoresAptes(nomPE, nomA, 0, 0, ha)) != null)	return error;
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
