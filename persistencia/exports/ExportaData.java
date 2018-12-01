package persistencia.exports;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaData extends Exporta {
	public static String exportaData(Data d, boolean crea) throws Exception {
		String endl = "\n";
		String str = "Data".concat(endl);
		str = str.concat(String.valueOf(d.getDia())).concat(endl);
		str = str.concat(String.valueOf(d.getHora())).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
