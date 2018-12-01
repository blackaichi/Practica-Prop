package persistencia.exports;

import domini.classes.SessioGAssignada;
import domini.classes.SessioSGAssignada;
import utils.*;

public class ExportaSegment extends Exporta {
	static String exportaSegment(Segment s, boolean crea) throws Exception {
		String endl = "\n";
		String str = "Segment".concat(endl);
		str = str.concat(ExportaData.exportaData(s.getData(), false)).concat(endl);
		str = str.concat(ExportaAula.exportaAula(s.getAula(), false)).concat(endl);
		Pair<SessioGAssignada, SessioSGAssignada> sessio = s.getSessio();
		if (sessio.snull()) {
			str = str.concat("g").concat(endl);
			str = str.concat(ExportaSessioGAssignada.exportaSessioGAssignada(sessio.first, false));
		}
		else {
			str = str.concat("sg").concat(endl);
			str = str.concat(ExportaSessioSGAssignada.exportaSessioSGAssignada(sessio.second, false));
		}
		str = str.concat(endl).concat("END").concat(endl);
		if (crea) Exporta.exporta(str);
		return str;
	}
}
