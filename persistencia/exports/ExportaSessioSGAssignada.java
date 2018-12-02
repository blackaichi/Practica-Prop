package persistencia.exports;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaSessioSGAssignada extends Exporta {
	
	private static ExportaSessioSGAssignada instancia = new ExportaSessioSGAssignada();
	
	private ExportaSessioSGAssignada() {};
	
	public static ExportaSessioSGAssignada getInstancia() {
		return instancia;
	}
	
	public String exportaSessioSGAssignada(SessioSGAssignada ssga, boolean crea) throws Exception {
		String endl = "\n";
		String str = "SessioSGAssignada".concat(endl);
		ExportaSubGrup esg = ExportaSubGrup.getInstancia();
		ExportaSessioSubGrup essg = ExportaSessioSubGrup.getInstancia();
		str = str.concat(esg.exportaSubGrup(ssga.getSubGrup(), false)).concat(endl);
		str = str.concat(essg.exportaSessioSubGrup(ssga.getSessioSubGrup(), false)).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
