package persistencia.imports;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ImportaHoresAptes extends Importa {
	
	private static ImportaHoresAptes instancia = new ImportaHoresAptes();
	
	private ImportaHoresAptes() {};
	
	public static ImportaHoresAptes getInstancia() {
		return instancia;
	}

	public String importaHoresAptes(String path, PlaEstudis pe) {
		
		return null;
	}

}
