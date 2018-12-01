package persistencia.exports;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaSessioSGAssignada extends Exporta {
	public static String exportaSessioSGAssignada(SessioSGAssignada ssga, boolean crea) throws Exception {
		String endl = "\n";
		String str = "SessioSGAssignada".concat(endl);
		str = str.concat(ExportaSubGrup.exportaSubGrup(ssga.getSubGrup(), false)).concat(endl);
		str = str.concat(ExportaSessioSubGrup.exportaSessioSubGrup(ssga.getSessioSubGrup(), false)).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
