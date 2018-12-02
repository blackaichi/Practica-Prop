package persistencia.imports;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ImportaGrup extends Importa {
	
	private static ImportaGrup instancia = new ImportaGrup();
	
	private ImportaGrup() {};
	
	public static ImportaGrup getInstancia() {
		return instancia;
	}

	public String importaGrup(String path, PlaEstudis pe, Assignatura a) {
		
		return null;
	}

}
