package persistencia.exports;

import java.util.HashSet;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaGrup extends Exporta {
	public static String exportaGrup(Grup g, boolean crea) throws Exception {
		String endl = "\n";
		String str = "Grup".concat(endl);
		str = str.concat(String.valueOf(g.getNumero())).concat(endl);
		str = str.concat(String.valueOf(g.getPlaces())).concat(endl);
		str = str.concat(g.getFranja()).concat(endl);
		HashSet<SubGrup> subGrups = g.getAllSubGrups();
		str = str.concat(String.valueOf(subGrups.size()).concat(endl).concat("{").concat(endl));
		for (SubGrup sg : subGrups) str = str.concat(ExportaSubGrup.exportaSubGrup(sg, false)).concat(endl);
		str = str.concat("}").concat(endl);
		str = str.concat(ExportaHoresAptes.exportaHoresAptes(g.getRestriccioHoresAptes(), false)).concat(endl);
		str = str.concat(ExportaSolapaments.exportaSolapaments(g.getSolapaments(), false)).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
