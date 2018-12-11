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
	
	public String importaAula(String path, String nomC, String f) {
		try {
			String nomA;
			int capacitat;
			HashSet<String> equip = new HashSet<String>();
			if (f != null) {
				String[] s = f.split("\n");
				if (s[0] != "Aula")	return "no es un aula el fitxer";
				nomA = s[1];
				capacitat = Integer.parseInt(s[2]);
				String[] equipament = s[3].split(",");
				for (int j = 0; j < equipament.length; ++j) {
					equip.add(equipament[j]);
				}
				if (s[4] != "END") return "No finalitza correctament";
			}
			else {
				File file = new File(path); 
				BufferedReader br = new BufferedReader(new FileReader(file)); 
			    String s;
				if (br.readLine() != "Aula") {
					br.close();
					return "no es un aula el fitxer";
				}
				nomA = br.readLine();
				capacitat = Integer.parseInt(br.readLine());
				s = br.readLine();
				String[] equipament = s.split(",");
				for (int j = 0; j < equipament.length; ++j) {
					equip.add(equipament[j]);
				}
				if (s != "END") {
					br.close();
					return "No finalitza correctament";
				}
				br.close();
			}
			cp.creaAulaImportada(nomC, nomA, capacitat, equip);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
