package persistencia.imports;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ImportaSolapaments extends Importa {
	
	private static ImportaSolapaments instancia = new ImportaSolapaments();
	
	private ImportaSolapaments() {};
	
	public static ImportaSolapaments getInstancia() {
		return instancia;
	}

	public String importaSolapaments(String path, PlaEstudis pe, Assignatura a, Grup g, SubGrup sg) {

		return null;
	}

}
