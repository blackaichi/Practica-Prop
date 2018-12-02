package persistencia.imports;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ImportaSessioGrup extends Importa {
	
	private static ImportaSessioGrup instancia = new ImportaSessioGrup();
	
	private ImportaSessioGrup() {};
	
	public static ImportaSessioGrup getInstancia() {
		return instancia;
	}

	public String importaSessioGrup(String path, PlaEstudis pe, Assignatura a) {

		return null;
	}

}
