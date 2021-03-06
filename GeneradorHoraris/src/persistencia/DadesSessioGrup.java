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

public class DadesSessioGrup extends ExportaImporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static DadesSessioGrup instancia = new DadesSessioGrup();
	
	/**
	 * Creadora buida
	 */
	private DadesSessioGrup() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static DadesSessioGrup getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta una sessio de grup
	 * @param path path del fitxer que volem 
	 * @param equip equip de la sessió
	 * @param hores hores de la sessió
	 * @param tipus tipus de la sessió
	 * @param nsessions nombre de sessions de la sessió
	 * @param ngrups números dels grups que pertanyen juntament amb la sessió formen una sessió assignada
	 * @param crea true si es vol truncar el fitxer, false si es vol fer un append
	 */
	public void exportaSessioGrup(String path, HashSet<String> equip, int hores,
			String tipus, int nsessions, HashSet<Integer> ngrups, boolean crea) {
		String str = "SessioGrup".concat(endl);
		boolean first = true;  
		if (equip.isEmpty()) str = str.concat("noequip");
		for (String s : equip) {
			if (first) first = false;
			else str = str.concat(",");
			str = str.concat(s);
		}
		str = str.concat(endl);
		str = str.concat(String.valueOf(hores)).concat(endl);
		str = str.concat(tipus).concat(endl);
		str = str.concat(String.valueOf(nsessions)).concat(endl);
		first = true;  
		if (ngrups.size() == 0) str = str.concat("none");
		for (int n : ngrups) {
			if (first) first = false;
			else str = str.concat(",");
			str = str.concat(String.valueOf(n));
		}
		str = str.concat(endl).concat("END SESSIOG").concat(endl);
		exporta(path, str, crea);
	}

	/**
	 * Importa una sessió de grup
	 * @param path path del fitxer que volem 
	 * @param nomPE nom del pla d'estudis
	 * @param nomA nom de l'assignatura
	 * @param f llista amb el que hi havia al fitxer
	 * @return null en cas de estar correcte, sinó l'error
	 */
	public String importaSessioGrup(String path, String nomPE, String nomA, List<String> f) {
		try {
			boolean assignada = true;
			if (f == null) {
				assignada = false;
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
			if (!f.get(i).equals("SessioGrup")) return "no conte una sessio de grup el fitxer";
			while (i < f.size() && f.get(i++).equals("SessioGrup")) {
				if (i + 5 > f.size()) return "error llargada de sessio grup";
				HashSet<String> equip = new HashSet<String>();
				int hores;
				String tipus;
				String error;
				int nsessions;
				String[] equipament = f.get(i++).split(",");
				if (equipament.length > 1 || !equipament[0].equals("noequip")) {
					for (int j = 0; j < equipament.length; ++j) {
						equip.add(equipament[j]);
					}
				}
				hores = Integer.parseInt(f.get(i++));
				tipus = f.get(i++);
				nsessions = Integer.parseInt(f.get(i++));
				if (!f.get(i+1).equals("END SESSIOG")) return "error en acabar fitxer sessio grup";
				if ((error = cp.creaSessioGrupImportada(nomPE, nomA, equip, hores, tipus, nsessions)) != null) return error;
				if (assignada && !f.get(i).equals("none")) {
					String[] a = f.get(i).split(",");
					for (String as : a) {
						if ((error = cp.creaSessioGrupAImportada(nomPE, nomA, tipus, hores, Integer.parseInt(as))) != null) {
							cp.eliminaSessioGrup(nomPE, nomA, tipus, hores);
							return error;
						}
					}
				}
			}
			return null;			
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
