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
			File file = new File(path); 
			BufferedReader br = new BufferedReader(new FileReader(file));
			List<String> entrada = new ArrayList<String>();
			while ((s = br.readLine()) != null) {
				entrada.add(s);
			}
			br.close();
			int i = 0;
			if (!entrada.get(i++).equals("Campus")) return "Error al llegir la primera linia del fitxer";
			String nomC = entrada.get(i++);
			String autor = entrada.get(i++);
			if (!entrada.get(entrada.size()-1).equals("END CAMPUS")) return "Error al final del fitxer";
			if (entrada.contains("Aula") && entrada.contains("END AULA"))
				entrada = entrada.subList(entrada.indexOf("Aula"), entrada.lastIndexOf("END AULA")+1);
			if ((error = cp.creaCampusImportat(nomC, autor)) != null) return error;
			if ((error = DadesAula.getInstancia().importaAula(path, nomC, entrada)) != null) {
				cp.eliminaCampus(nomC);
				return error;
			}
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
