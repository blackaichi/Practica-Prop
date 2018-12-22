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
	 * Exporta un campus
	 * @param path path del fitxer que volem 
	 * @param nom nom del campus
	 * @param autor autor del campus
	 * @param aules aules que conté el campus
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

	/**
	 * Importa un campus
	 * @param path llista amb el que hi havia al fitxer
	 * @return null en cas de estar correcte, sinó l'error
	 */
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
			if (entrada.size() != 4 && (error = DadesAula.getInstancia().importaAula(path, nomC, entrada)) != null) {
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
