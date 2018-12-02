package persistencia.imports;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ImportaSessioSubGrup extends Importa {
	
	private static ImportaSessioSubGrup instancia = new ImportaSessioSubGrup();
	
	private ImportaSessioSubGrup() {};
	
	public static ImportaSessioSubGrup getInstancia() {
		return instancia;
	}

	public String importaSessioSubGrup(String path, PlaEstudis pe, Assignatura a) {

		return null;
	}

}
