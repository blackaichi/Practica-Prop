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
	 * Exporta unes hores aptes
	 * @param path path del fitxer que volem 
	 * @param franja franja de les hores aptes
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

	/**
	 * Importa unes hores aptes d'assignatura si grup es < 0, de grup si subgrup < 0 o de subgrup altrament
	 * @param nomPE nom del pla d'estudis a la que petanyen
	 * @param nomA nom de l'assignatura a la qual pertanyen
	 * @param grup numero del grup de les hores aptes
	 * @param subgrup numero del subgrup de les hores aptes
	 * @param entry llista amb lo necessari per importar hores aptes
	 * @return null en cas de estar correcte, sinó l'error
	 */
	public String importaHoresAptes(String nomPE, String nomA, int grup, int subgrup, List<String> entry) {
		try {
			if (!entry.get(0).equals("HoresAptes") || !entry.get(entry.size()-1).equals("END HA") || entry.size() != 3) 
				return "error a les hores aptes";
			Map<Integer, boolean[]> ha = new HashMap<Integer, boolean[]>();
			String[] sa = entry.get(1).split("\\x29");
			for (String s : sa) {
				boolean[] b = new boolean[24];
				String[] sa2 = s.split("\\x28");
				if (sa2[1].equals("null")) ha.put(Integer.parseInt(sa2[0]), null); 
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
