package persistencia.exports;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaSessioGAssignada extends Exporta {
	
	private static ExportaSessioGAssignada instancia = new ExportaSessioGAssignada();
	
	private ExportaSessioGAssignada() {};
	
	public static ExportaSessioGAssignada getInstancia() {
		return instancia;
	}
	
	public String exportaSessioGAssignada(SessioGAssignada sga, boolean crea) throws Exception {
		String endl = "\n";
		String str = "SessioGAssignada".concat(endl);
		ExportaGrup eg = ExportaGrup.getInstancia();
		ExportaSessioGrup esg = ExportaSessioGrup.getInstancia();
		str = str.concat(eg.exportaGrup(sga.getGrup(), false)).concat(endl);
		str = str.concat(esg.exportaSessioGrup(sga.getSessioGrup(), false)).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
