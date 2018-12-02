package persistencia.imports;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ImportaCampus extends Importa {
	
	private static ImportaCampus instancia = new ImportaCampus();
	
	private ImportaCampus() {};
	
	public static ImportaCampus getInstancia() {
		return instancia;
	}

	public String importaCampus(String path) {
		
		return null;
	}

}
