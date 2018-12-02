package persistencia.imports;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ImportaSubGrup extends Importa {
	
	private static ImportaSubGrup instancia = new ImportaSubGrup();
	
	private ImportaSubGrup() {};
	
	public static ImportaSubGrup getInstancia() {
		return instancia;
	}

	public String importaSubGrup(String path, PlaEstudis pe, Assignatura a, Grup g) {

		return null;
	}

}
