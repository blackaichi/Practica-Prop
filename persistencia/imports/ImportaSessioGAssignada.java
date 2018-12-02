package persistencia.imports;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ImportaSessioGAssignada extends Importa {
	
	private static ImportaSessioGAssignada instancia = new ImportaSessioGAssignada();
	
	private ImportaSessioGAssignada() {};
	
	public static ImportaSessioGAssignada getInstancia() {
		return instancia;
	}

	public String importaSessioGAssignada(String path, PlaEstudis pe, Assignatura a, Grup g) {

		return null;
	}

}
