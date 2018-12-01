package persistencia.exports;

import java.util.HashSet;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaSessioGrup extends Exporta {
	public static String exportaSessioGrup(SessioGrup sg, boolean crea) throws Exception {
		String endl = "\n";
		String str = "SessioGrup".concat(endl);
		HashSet<String> equip = sg.getMaterial();
		boolean first = true;  
		if (equip.isEmpty()) str = str.concat("noequip");
		for (String s : equip) {
			if (first) first = false;
			else str = str.concat(",");
			str = str.concat(s);
		}
		str = str.concat(endl);
		str = str.concat(String.valueOf(sg.getHores())).concat(endl);
		str = str.concat(sg.getTipus()).concat(endl);
		str = str.concat(String.valueOf(sg.getnsessions())).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
