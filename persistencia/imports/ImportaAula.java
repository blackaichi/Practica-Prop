package persistencia.imports;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ImportaAula extends Importa {
	
	private static ImportaAula instancia = new ImportaAula();
	
	private ImportaAula() {};
	
	public static ImportaAula getInstancia() {
		return instancia;
	}

	public String importaAula(String path, Campus c) {
		
		return null;
	}

}
