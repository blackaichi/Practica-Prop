package persistencia.exports;

import java.util.Map;
import domini.restriccions.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaHoresAptes extends Exporta {
	
	private static ExportaHoresAptes instancia = new ExportaHoresAptes();
	
	private ExportaHoresAptes() {};
	
	public static ExportaHoresAptes getInstancia() {
		return instancia;
	}
	
	public String exportaHoresAptes(HoresAptes ha, boolean crea) throws Exception {
		String endl = "\n";
		String str = "HoresAptes".concat(endl);
		Map<Integer, boolean[]> franja = ha.getHoresAptes();
		boolean[] b;
		for (int i = 0; i < 7; ++i) {
			b = franja.get(i);
			str = str.concat(String.valueOf(i)).concat("(");
			if (b == null) str = str.concat("null");
			else {
				for (boolean p : b) {
					if (p) str = str.concat("t");
					else str = str.concat("f");
				}
			}
			str = str.concat(")");
		}
		str = str.concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
