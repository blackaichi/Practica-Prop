package persistencia.exports;

import domini.restriccions.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaSolapaments extends Exporta {
	public static String exportaSolapaments(Solapaments s, boolean crea) throws Exception {
		String endl = "\n";
		String str = "Solapaments".concat(endl);
		
		
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
