package persistencia.exports;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaSessioGAssignada extends Exporta {
	public static String exportaSessioGAssignada(SessioGAssignada sga, boolean crea) throws Exception {
		String endl = "\n";
		String str = "SessioGAssignada".concat(endl);
		str = str.concat(ExportaGrup.exportaGrup(sga.getGrup(), false)).concat(endl);
		str = str.concat(ExportaSessioGrup.exportaSessioGrup(sga.getSessioGrup(), false)).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
