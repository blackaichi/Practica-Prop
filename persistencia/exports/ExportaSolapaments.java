package persistencia.exports;

import domini.restriccions.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaSolapaments extends Exporta {
	
	private static ExportaSolapaments instancia = new ExportaSolapaments();
	
	private ExportaSolapaments() {};
	
	public static ExportaSolapaments getInstancia() {
		return instancia;
	}
	public String exportaSolapaments(Solapaments s, boolean crea) throws Exception {
		String endl = "\n";
		String str = "Solapaments".concat(endl);
		
		
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
