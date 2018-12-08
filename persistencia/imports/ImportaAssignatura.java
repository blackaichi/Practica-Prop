package persistencia.imports;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ImportaAssignatura extends Importa {
	
	private static ImportaAssignatura instancia = new ImportaAssignatura();
	
	private ImportaAssignatura() {};
	
	public static ImportaAssignatura getInstancia() {
		return instancia;
	}

	public String importaAssignatura(String path, PlaEstudis pe) {
		
		return null;
	}
	
	public String importaAssignatura(String path, String nomPE, String assig) {
		try {
			File file = new File(path); 
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String s;
			br.readLine();
			while (br.readLine() != "Assignatura");
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}

}
