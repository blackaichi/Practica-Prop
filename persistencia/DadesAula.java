package persistencia;

import java.io.*;
import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DadesAula extends ExportaImporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static DadesAula instancia = new DadesAula();
	
	/**
	 * Creadora buida
	 */
	private DadesAula() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static DadesAula getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta un Aula
	 * @param a aula que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació de l'aula
	 */
	public void exportaAula(String path, String nomAula, int capacitat, HashSet<String> equip, boolean crea) {
		String str = "Aula".concat(endl);
		str = str.concat(nomAula.concat(endl));
		str = str.concat(String.valueOf(capacitat)).concat(endl);
		boolean first = true;  
		if (equip.isEmpty()) str = str.concat("noequip");
		for (String s : equip) {
			if (first) first = false;
			else str = str.concat(",");
			str = str.concat(s);
		}
		str = str.concat(endl).concat("END AULA").concat(endl);
		exporta(path, str, crea);
	}
	
	public String importaAula(String path, String nomC, List<String> f) {
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
			if (!f.get(i).equals("Aula")) return "no conte un aula el fitxer";
			while (i < f.size() && f.get(i++).equals("Aula")) {
				if (i + 3 > f.size()) return "error llargada de aula";
				String nomA;
				String error;
				int capacitat;
				HashSet<String> equip = new HashSet<String>();
				nomA = f.get(i++);
				capacitat = Integer.parseInt(f.get(i++));
				String[] equipament = f.get(i++).split(",");
				for (String ss : equipament) {
					equip.add(ss);
				}
				if (!f.get(i++).equals("END AULA")) return "error en acabar fitxer aula";
				if ((error = cp.creaAulaImportada(nomC, nomA, capacitat, equip)) != null) return error;
			}
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
