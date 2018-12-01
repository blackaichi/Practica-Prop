package persistencia.exports;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaSubGrup extends Exporta {
	public static String exportaSubGrup(SubGrup sg, boolean crea) throws Exception {
		String endl = "\n";
		String str = "SubGrup".concat(endl);
		
		
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
