package persistencia.imports;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ImportaSessioSGAssignada extends Importa {
	
	private static ImportaSessioSGAssignada instancia = new ImportaSessioSGAssignada();
	
	private ImportaSessioSGAssignada() {};
	
	public static ImportaSessioSGAssignada getInstancia() {
		return instancia;
	}

	public String importaSessioSGAssignada(String path, PlaEstudis pe, Assignatura a, Grup g, SubGrup sg) {
		
		return null;
	}

}
