package persistencia.exports;

import java.util.*;
import utils.*;

public class ExportaEstructura {
	static String exportaEstructura(Estructura e, int n, boolean crea) throws Exception {
		String endl = "\n";
		String str = "Horari ".concat(String.valueOf(n)).concat(endl);
		HashSet<Segment> s;
		for (int dia = 0; dia < 7; ++dia) {
			str = str.concat(String.valueOf(dia)).concat(endl);
			str = str.concat("{").concat(endl);
			for (int hora = 0; hora < 24; ++hora) {
				str = str.concat(String.valueOf(hora)).concat(endl);
				str = str.concat("{").concat(endl);
				s = e.getAllSegments(dia, hora);
				if (s.isEmpty()) str = str.concat("buit").concat(endl);
				else {
					for (Segment se : s) str = str.concat(ExportaSegment.exportaSegment(se, false));
				}
				str = str.concat("}").concat(endl);
			}
			str = str.concat("}").concat(endl);
		}
		str = str.concat("END").concat(endl);
		if (crea) Exporta.exporta(str);
		return str;
	}
}
