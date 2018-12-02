package persistencia.exports;

import java.util.HashSet;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaSessioSubGrup extends Exporta {
	
	private static ExportaSessioSubGrup instancia = new ExportaSessioSubGrup();
	
	private ExportaSessioSubGrup() {};
	
	public static ExportaSessioSubGrup getInstancia() {
		return instancia;
	}
	
	public String exportaSessioSubGrup(SessioSubGrup ssg, boolean crea) throws Exception {
		String endl = "\n";
		String str = "SessioSubGrup".concat(endl);
		HashSet<String> equip = ssg.getMaterial();
		boolean first = true;  
		if (equip.isEmpty()) str = str.concat("noequip");
		for (String s : equip) {
			if (first) first = false;
			else str = str.concat(",");
			str = str.concat(s);
		}
		str = str.concat(endl);
		str = str.concat(String.valueOf(ssg.getHores())).concat(endl);
		str = str.concat(ssg.getTipus()).concat(endl);
		str = str.concat(String.valueOf(ssg.getnsessions())).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
