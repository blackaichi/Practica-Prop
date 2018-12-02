package persistencia.imports;

import domini.classes.Campus;
import domini.classes.PlaEstudis;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ImportaHorari extends Importa {
	
	private static ImportaHorari instancia = new ImportaHorari();
	
	private ImportaHorari() {};
	
	public static ImportaHorari getInstancia() {
		return instancia;
	}

	public String importaHoraris(String path, PlaEstudis pe, Campus c) {
		
		return null;
	}

}
