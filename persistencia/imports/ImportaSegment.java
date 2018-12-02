package persistencia.imports;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ImportaSegment extends Importa {
	
	private static ImportaSegment instancia = new ImportaSegment();
	
	private ImportaSegment() {};
	
	public static ImportaSegment getInstancia() {
		return instancia;
	}

}
