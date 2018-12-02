package persistencia.imports;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ImportaEstructura extends Importa {
	
	private static ImportaEstructura instancia = new ImportaEstructura();
	
	private ImportaEstructura() {};
	
	public static ImportaEstructura getInstancia() {
		return instancia;
	}

}
