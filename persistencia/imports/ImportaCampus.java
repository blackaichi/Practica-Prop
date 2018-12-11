package persistencia.imports;

import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ImportaCampus extends Importa {
	
	private static ImportaCampus instancia = new ImportaCampus();
	
	private ImportaCampus() {};
	
	public static ImportaCampus getInstancia() {
		return instancia;
	}

	public String importaCampus(String path) {
		try {
			String s = importa(path);
			String[] aux = s.split("\n");
			String error;
			Vector<String> entrada = new Vector<String>();
			for (String ss : aux) {
				entrada.add(ss);
			}
			if (entrada.get(0) != "Campus") return "Error al llegir la primera linia del fitxer";
			String nomC = entrada.get(1);
			String autor = entrada.get(2);
			if ((error = ImportaAula.getInstancia().importaAula(path, nomC)) != null) return error;
			cp.creaCampusImportat(nomC, autor);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
