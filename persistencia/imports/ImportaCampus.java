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
			File file = new File(path); 
			BufferedReader br = new BufferedReader(new FileReader(file));
			nomC = br.readLine();
			autor = br.readLine();
			String error;
			if ((error = ImportaAula.getInstancia().importaAula(path, nomaules, capacitat, equip)) != null) {
				br.close();
				return error;
			}
			br.close();
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
