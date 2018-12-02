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
	
	public HashSet<Assignatura> importaAssignatura(String path, int nassig) {
		try {
			File file = new File(path); 
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			HashSet<Assignatura> assignatures = new HashSet<Assignatura>();
			String s;
			br.readLine();
			String nompla = br.readLine();
			while (br.readLine() != "Assignatura");
			for (int i = 0; i < nassig; ++i) {
				
				
				
			}
			Assignatura a;
			
			return assignatures;
		}
		catch (Exception e) {
			return null;
		}
	}

}
