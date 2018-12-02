package persistencia.exports;

import domini.classes.SessioGAssignada;
import domini.classes.SessioSGAssignada;
import utils.*;

public class ExportaSegment extends Exporta {
	
	private static ExportaSegment instancia = new ExportaSegment();
	
	private ExportaSegment() {};
	
	public static ExportaSegment getInstancia() {
		return instancia;
	}
	
	public String exportaSegment(Segment s, boolean crea) throws Exception {
		String endl = "\n";
		String str = "Segment".concat(endl);
		ExportaData ed = ExportaData.getInstancia();
		ExportaAula ea = ExportaAula.getInstancia();
		ExportaSessioGAssignada esga = ExportaSessioGAssignada.getInstancia();
		ExportaSessioSGAssignada essga = ExportaSessioSGAssignada.getInstancia();
		str = str.concat(ed.exportaData(s.getData(), false)).concat(endl);
		str = str.concat(ea.exportaAula(s.getAula(), false)).concat(endl);
		Pair<SessioGAssignada, SessioSGAssignada> sessio = s.getSessio();
		if (sessio.snull()) {
			str = str.concat("g").concat(endl);
			str = str.concat(esga.exportaSessioGAssignada(sessio.first, false));
		}
		else {
			str = str.concat("sg").concat(endl);
			str = str.concat(essga.exportaSessioSGAssignada(sessio.second, false));
		}
		str = str.concat(endl).concat("END").concat(endl);
		if (crea) Exporta.exporta(str);
		return str;
	}
}
