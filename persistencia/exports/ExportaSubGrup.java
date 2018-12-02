package persistencia.exports;

import java.util.HashSet;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaSubGrup extends Exporta {
	
	private static ExportaSubGrup instancia = new ExportaSubGrup();
	
	private ExportaSubGrup() {};
	
	public static ExportaSubGrup getInstancia() {
		return instancia;
	}
	
	public String exportaSubGrup(SubGrup sg, boolean crea) throws Exception {
		String endl = "\n";
		String str = "SubGrup".concat(endl);
		ExportaSubGrup esg = ExportaSubGrup.getInstancia();
		ExportaHoresAptes eha = ExportaHoresAptes.getInstancia();
		ExportaSolapaments es = ExportaSolapaments.getInstancia();
		str = str.concat(String.valueOf(sg.getNumero())).concat(endl);
		str = str.concat(String.valueOf(sg.getPlaces())).concat(endl);
		str = str.concat(eha.exportaHoresAptes(sg.getRestriccioHoresAptes(), false)).concat(endl);
		str = str.concat(es.exportaSolapaments(sg.getSolapaments(), false)).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
