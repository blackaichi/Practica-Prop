package persistencia.exports;

import java.util.*;
import utils.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaHorari extends Exporta {
	public static String exportaHoraris(HashSet<Estructura> e, boolean crea) throws Exception {
		String endl = "\n";
		String str = "Horaris".concat(endl);
		str = str.concat(String.valueOf(e.size()).concat(endl).concat("{").concat(endl));
		int n = 1;
		for (Estructura a : e) {
			str = str.concat(ExportaEstructura.exportaEstructura(a, n, false));
			++n;
		}
		str = str.concat("}").concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
