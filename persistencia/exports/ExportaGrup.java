package persistencia.exports;

import java.util.HashSet;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaGrup extends Exporta {
	
	private static ExportaGrup instancia = new ExportaGrup();
	
	private ExportaGrup() {};
	
	public static ExportaGrup getInstancia() {
		return instancia;
	}
	
	public String exportaGrup(Grup g, boolean crea) throws Exception {
		String endl = "\n";
		String str = "Grup".concat(endl);
		ExportaSubGrup esg = ExportaSubGrup.getInstancia();
		ExportaHoresAptes eha = ExportaHoresAptes.getInstancia();
		ExportaSolapaments es = ExportaSolapaments.getInstancia();
		str = str.concat(String.valueOf(g.getNumero())).concat(endl);
		str = str.concat(String.valueOf(g.getPlaces())).concat(endl);
		str = str.concat(g.getFranja()).concat(endl);
		HashSet<SubGrup> subGrups = g.getAllSubGrups();
		str = str.concat(String.valueOf(subGrups.size()).concat(endl).concat("{").concat(endl));
		for (SubGrup sg : subGrups) str = str.concat(esg.exportaSubGrup(sg, false)).concat(endl);
		str = str.concat("}").concat(endl);
		str = str.concat(eha.exportaHoresAptes(g.getRestriccioHoresAptes(), false)).concat(endl);
		str = str.concat(es.exportaSolapaments(g.getSolapaments(), false)).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
