package persistencia.exports;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaSessioSubGrup extends Exporta {
	public static String exportaSessioSubGrup(SessioSubGrup ssg, boolean crea) throws Exception {
		String endl = "\n";
		String str = "SessioSubGrup".concat(endl);
		
		
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
