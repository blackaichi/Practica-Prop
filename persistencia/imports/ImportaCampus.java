package persistencia.imports;

import java.io.*;
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

	public String importaCampus(String path, String nomC, String autor, HashSet<String> nomaules,
			HashSet<Integer> capacitat, HashSet<HashSet<String>> equip) {
		try {
			String s = importa(path);
			String[] aux = s.split("\n");
			String error;
			Vector<String> entrada = new Vector<String>();
			for (String ss : aux) {
				entrada.add(ss);
			}
			if (entrada.get(0) != "Campus") return s;
			nomC = entrada.get(1);
			autor = entrada.get(2);
			if ((error = ImportaAula.getInstancia().importaAula(, nomaules, capacitat, equip)) != null) return error;
			cp.creaCampusImportat()
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
