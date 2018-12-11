package persistencia;

import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DadesCampus extends ExportaImporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static DadesCampus instancia = new DadesCampus();
	
	/**
	 * Creadora buida
	 */
	private DadesCampus() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static DadesCampus getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta un Campus
	 * @param c campus que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació del Campus
	 */
	public void exportaCampus(String path, String nom, String autor, HashSet<String> aules) {
		String str = "Campus".concat(endl);
		str = str.concat(nom.concat(endl));
		str = str.concat(autor.concat(endl));
		exporta(path, str, true);
		for (String s : aules) {
			cp.getAula(path, nom, s);
		}
		exporta(path, "END CAMPUS".concat(endl), false);
	}

	public String importaCampus(String path) {
		try {
			String s = "";
			String error;
			if ((error = importa(path, s)) != null) return error;
			String[] aux = s.split("\n");
			Vector<String> entrada = new Vector<String>();
			for (String ss : aux) {
				entrada.add(ss);
			}
			if (entrada.get(0) != "Campus") return "Error al llegir la primera linia del fitxer";
			String nomC = entrada.get(1);
			String autor = entrada.get(2);
			if (entrada.get(entrada.size()-1) != "END") return "Error al final del fitxer";
			if ((error = DadesAula.getInstancia().importaAula(path, nomC, s)) != null) return error;
			cp.creaCampusImportat(nomC, autor);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
